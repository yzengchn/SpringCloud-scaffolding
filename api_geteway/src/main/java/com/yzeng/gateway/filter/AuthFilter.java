package com.yzeng.gateway.filter;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.yzeng.gateway.utils.CookieUtils;
import com.yzeng.gateway.vo.ResultVO;

@SuppressWarnings("unused")
@Component
public class AuthFilter extends ZuulFilter{
	private static Logger LOG = LoggerFactory.getLogger(AuthFilter.class);
	
	//排除过滤的 uri 地址
    private static final String LOGIN_URI = "/user/login";
    private static final String REGISTER_URI = "/user/register";
    private static final String GET_ROLES_URI = "/user/get-roles";
    private static final String GET_EXPERIENCES_URI = "/user/get-experiences";

    private static final String[] IGNORE_URIS = {
    		LOGIN_URI,
    		REGISTER_URI,
    		GET_ROLES_URI,
    		GET_EXPERIENCES_URI
    };
	
    //无权限时的提示语
    private static final String INVALID_TOKEN = "invalid token";
    private static final String INVALID_USERID = "invalid userId";
    
	/**
	 * shouldFilter：这里可以写逻辑判断，是否要过滤
	 */
	@Override
	public boolean shouldFilter() {
		RequestContext requestContext = RequestContext.getCurrentContext();
		HttpServletRequest request = requestContext.getRequest();
		LOG.info("uri:{}",request.getRequestURI());
		
		//IGNORE_URIS中的链接不拦截
		List<String> list = Arrays.asList(IGNORE_URIS);
		for (String uri : list) {
			if(uri.equals(request.getRequestURI())) {
				return false;
			}
		}
		return true;
	}

	
	@Override
	public Object run() throws ZuulException {
		RequestContext context = RequestContext.getCurrentContext();
		HttpServletRequest request = context.getRequest();
		LOG.info(String.format("%s >>> %s", request.getMethod(), request.getRequestURL().toString()));
		Cookie cookieToken = CookieUtils.getCookieByName(request, "token");
		if(cookieToken == null || StringUtils.isEmpty(cookieToken.getValue())) {
			readTokenFromHeader(context, request);
		}else {
			
		}
		
		LOG.info("ok");
		return null;
	}

	/**
	 * 生命周期过滤类型有以下几种
	 * pre：路由之前
	 * routing：路由之时
	 * post： 路由之后
	 * error：发送错误调用
	 */
	@Override
	public String filterType() {
		return "pre";
	}

	/**
	 * filterOrder：过滤的顺序
	 */
	@Override
	public int filterOrder() {
		return 0;
	}
	
	/**
	 * 在redis中验证Token
	 * @author yzblog.xyz
	 * @date 2018年12月3日 下午1:33:35
	 * @title verifyToken
	 * @param requestContext
	 * @param request
	 * @param token
	 * @return void
	 */
	private void verifyToken(RequestContext requestContext,HttpServletRequest request,String token) {
		//在Redis中token键格式为：TOKEN_userId，每个用户对应一个token
		Cookie cookieUserId = CookieUtils.getCookieByName(request, "userId");
		
		//判断userId在cookie中是否为空
		if(cookieUserId == null || StringUtils.isEmpty(cookieUserId.getValue())) {
			//从header中取token
			String userId = request.getHeader("userId");
			if(StringUtils.isEmpty(userId)) {
				setUnauthorizedResponse(requestContext, INVALID_USERID);
			}else {
				//根据键从redis取出值
				
			}
		//键不为空直接去和redis中的值比较
		}else {
			
		}
	}
	
	/**
	 * 从Header中获取Token
	 * @author yzblog.xyz
	 * @date 2018年12月3日 下午12:14:55
	 * @title readTokenFromHeader
	 * @param requestContext
	 * @param request
	 * @return void
	 */
	private void readTokenFromHeader(RequestContext requestContext,HttpServletRequest request) {
		//获得Token
		String headerToken = request.getHeader("token");
		//如果为空就返回401
		if(StringUtils.isEmpty(headerToken)) {
			setUnauthorizedResponse(requestContext, INVALID_TOKEN);
		}else {
			//不为空就去跟redis中的比较是否一致 
			verifyToken(requestContext, request, headerToken);
		}
	}
	
	/**
	 * 设置 401 无权限状态
	 * @author yzblog.xyz
	 * @date 2018年12月3日 上午11:58:21
	 * @title setUnauthorizedResponse
	 * @param requestContext
	 * @param msg
	 * @see [类、类#方法、类#成员]
	 */
	private void setUnauthorizedResponse(RequestContext requestContext,String msg) {
		requestContext.setSendZuulResponse(false);
        requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
        
        ResultVO result = new ResultVO();
        result.setMsg(msg);
        result.setCode(401);
        
        String jsonString = JSON.toJSONString(result);
        requestContext.setResponseBody(jsonString);
	}
	
}

package com.yzeng.userserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

/**
 * 对其他服务提供数据的Controller
 * @author  <a href="http://www.yzblog.xyz">yzblog</a>
 * @version  [1.0, 2018年12月7日]
 * @Email yzengchn@163.com
 * @since  [产品/模块版本]
 */

@RestController
public class ClientController {
	@GetMapping("/login")
	@ApiOperation(value="userId",notes="用户ID")
    public String getAvatarByUserId(@RequestParam("userId") String userId) {
        return "success";
    }
}

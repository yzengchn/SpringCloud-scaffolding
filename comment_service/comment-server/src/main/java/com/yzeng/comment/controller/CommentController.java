package com.yzeng.comment.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yzeng.comment.DTO.CommentsInfoDTO;
import com.yzeng.comment.VO.ResultVO;
import com.yzeng.comment.enums.ResultEnum;
import com.yzeng.comment.form.CommentsInfoForm;
import com.yzeng.comment.service.CommentService;
import com.yzeng.comment.utils.ResultVOUtils;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	@PostMapping("save")
	@ApiOperation(value="保存评论",notes="公用评论保存方法（资源评论，人评论/回复）")
	@ApiImplicitParam(name="CommentsInfoForm",value="评论实体",dataType="Json")
	public ResultVO saveComment(@Valid CommentsInfoForm form, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return ResultVOUtils.error(ResultEnum.PARAMS_ERROR);
		}
		
		CommentsInfoForm comment = commentService.saveComment(form);
		if(StringUtils.isEmpty(comment)) {
			return ResultVOUtils.error(ResultEnum.SYSTEM_EXCEPTION);
		}
		
		return ResultVOUtils.success(comment);
	}
	
	
	@GetMapping("get/{ownerId}")
	@ApiOperation(value="查询评论",notes="根据资源ID获取旗下的所有评论列表")
	@ApiImplicitParam(name="ownerId",value="资源ID",dataType="String")
	public ResultVO getCommentsByOwnerId(@PathVariable String ownerId) {
		
		List<CommentsInfoDTO> info = commentService.getCommentsByOwnerId(ownerId);
		
		return ResultVOUtils.success(info);
	}
}

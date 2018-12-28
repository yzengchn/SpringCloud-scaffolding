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

@RestController
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	@PostMapping("save")
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
	public ResultVO getCommentsByOwnerId(@PathVariable String ownerId) {
		
		List<CommentsInfoDTO> info = commentService.getCommentsByOwnerId(ownerId);
		
		return ResultVOUtils.success(info);
	}
}

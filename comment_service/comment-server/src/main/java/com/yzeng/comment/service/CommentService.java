package com.yzeng.comment.service;

import java.util.List;

import com.yzeng.comment.DTO.CommentsInfoDTO;
import com.yzeng.comment.form.CommentsInfoForm;

public interface CommentService {
	/**
	 * 保存评论 
	 * @author <a href="http://www.yzblog.xyz">yzblog</a>
	 * @date 2018年12月28日 下午4:13:47
	 * @title saveComment
	 * @param form
	 * @return
	 * @return CommentsInfoForm
	 */
	CommentsInfoForm saveComment(CommentsInfoForm form);
	
	/**
	 * 根据资源ID获取所属评论列表 
	 * @author <a href="http://www.yzblog.xyz">yzblog</a>
	 * @date 2018年12月28日 下午4:42:11
	 * @title getCommentsByOwnerId
	 * @param ownerId
	 * @return
	 * @return List<CommentsInfoDTO>
	 */
	List<CommentsInfoDTO> getCommentsByOwnerId(String ownerId);
}

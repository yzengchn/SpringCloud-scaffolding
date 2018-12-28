package com.yzeng.comment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yzeng.comment.DO.CommentInfo;

public interface CommentRepository extends JpaRepository<CommentInfo, String>{
	List<CommentInfo> getCommentInfoByOwnerId(String ownerId);
}

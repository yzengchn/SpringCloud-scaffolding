package com.yzeng.comment.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.yzeng.comment.DO.CommentInfo;
import com.yzeng.comment.DTO.CommentsInfoDTO;
import com.yzeng.comment.form.CommentsInfoForm;
import com.yzeng.comment.repository.CommentRepository;
import com.yzeng.comment.service.CommentService;
import com.yzeng.comment.utils.IdKeyUtils;

@Service
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	private CommentRepository commentRepository;

	@Override
	@CacheEvict(value="comments", key="#form.ownerId")
	public CommentsInfoForm saveComment(CommentsInfoForm form) {
		CommentInfo info = new CommentInfo();
		
		BeanUtils.copyProperties(form, info);
		
		info.setId(IdKeyUtils.genUniqueUUId());
		CommentInfo commentInfo = commentRepository.save(info);
		
		BeanUtils.copyProperties(commentInfo, form);
		
		return form;
	}

	@Override
	public List<CommentsInfoDTO> getCommentsByOwnerId(String ownerId) {
		
		List<CommentInfo> list = commentRepository.getCommentInfoByOwnerId(ownerId);
		List<CommentsInfoDTO> dtoList = new ArrayList<>();
		List<CommentsInfoDTO> reDTOList = new ArrayList<>();
		
		for (CommentInfo commentInfo : list) {
			CommentsInfoDTO dto = new CommentsInfoDTO();
			BeanUtils.copyProperties(commentInfo, dto);
			dtoList.add(dto);
		}
		
		//组装
		for (CommentsInfoDTO dto : dtoList) {
			List<CommentsInfoDTO> children = new ArrayList<>();
			for (CommentsInfoDTO dto1 : dtoList) {
				if(	dto1.getPid() != null 
						&& !StringUtils.isEmpty(dto1.getPid()) 
						&& dto.getId().equals(dto1.getPid())) {
					children.add(dto1);
				}
			}
			
			dto.setChildren(children);
			
			if(dto.getPid() == null || StringUtils.isEmpty(dto.getPid())) {
				reDTOList.add(dto);
			}
		}
		
		return reDTOList;
	}
	
}

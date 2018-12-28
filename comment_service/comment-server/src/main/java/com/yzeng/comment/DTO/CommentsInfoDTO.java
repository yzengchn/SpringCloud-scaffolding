package com.yzeng.comment.DTO;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class CommentsInfoDTO implements Serializable {

    private static final long serialVersionUID = -6788130126931979110L;

    //评论主键id
    private String id;

    //该条评论的父评论id
    private String pid;

    //评论的资源id。标记这条评论是属于哪个资源的。资源可以是人、项目、设计资源
    private String ownerId;

    //评论类型。1用户评论，2项目评论，3资源评论
    private Integer type;

    //评论者id
    private String fromId;

    //评论者名字
    private String fromName;

    //评论者头像
    private String fromAvatar;

    //被评论者id
    private String toId;

    //被评论者名字
    private String toName;

    //被评论者头像
    private String toAvatar;

    //获得点赞的数量
    private Integer likeNum;

    //评论内容
    private String content;

    //创建时间
    private Date createTime;

    //更新时间
    private Date updateTime;
    
    //子评论
    private List<CommentsInfoDTO> children;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getFromId() {
		return fromId;
	}

	public void setFromId(String fromId) {
		this.fromId = fromId;
	}

	public String getFromName() {
		return fromName;
	}

	public void setFromName(String fromName) {
		this.fromName = fromName;
	}

	public String getFromAvatar() {
		return fromAvatar;
	}

	public void setFromAvatar(String fromAvatar) {
		this.fromAvatar = fromAvatar;
	}

	public String getToId() {
		return toId;
	}

	public void setToId(String toId) {
		this.toId = toId;
	}

	public String getToName() {
		return toName;
	}

	public void setToName(String toName) {
		this.toName = toName;
	}

	public String getToAvatar() {
		return toAvatar;
	}

	public void setToAvatar(String toAvatar) {
		this.toAvatar = toAvatar;
	}

	public Integer getLikeNum() {
		return likeNum;
	}

	public void setLikeNum(Integer likeNum) {
		this.likeNum = likeNum;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public List<CommentsInfoDTO> getChildren() {
		return children;
	}

	public void setChildren(List<CommentsInfoDTO> children) {
		this.children = children;
	}

    
    
}

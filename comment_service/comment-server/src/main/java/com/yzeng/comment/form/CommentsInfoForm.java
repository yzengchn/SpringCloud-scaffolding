package com.yzeng.comment.form;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class CommentsInfoForm implements Serializable{

    private static final long serialVersionUID = 1690164381030501261L;

    //评论类型。1用户评论，2项目评论，3资源评论
    @NotNull(message = "评论类型不能为空")
    @ApiModelProperty("评论类型。1用户评论，2项目评论，3资源评论")
    private Integer type;

    //被评论者的id
    @NotEmpty(message = "被评论资源id不能为空")
    @ApiModelProperty("被评论资源的id")
    private String ownerId;

    @ApiModelProperty("父评论的id")
    private String pid;

    //评论者id
    @NotEmpty(message = "评论者id不能为空")
    @ApiModelProperty("评论者的id")
    private String fromId;

    //评论者名字
    @NotEmpty(message = "评论者名字不能为空")
    @ApiModelProperty("评论者名字")
    private String fromName;

    //评论者id
    @ApiModelProperty("被评论者的id，回复评论时传")
    private String toId;

    //评论者名字
    @ApiModelProperty("被评论者名字，回复评论时传")
    private String toName;

    //获得点赞的数量
    @ApiModelProperty("获得点赞的数量。可为空")
    private Integer likeNum = 0;

    //评论内容
    @NotEmpty(message = "评论内容不能为空")
    @ApiModelProperty("评论内容")
    private String content;

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
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
    
    

}

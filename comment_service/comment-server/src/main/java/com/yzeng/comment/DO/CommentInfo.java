package com.yzeng.comment.DO;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * 评论表
 * @author  <a href="http://www.yzblog.xyz">yzblog</a>
 * @version  [1.0, 2018年12月28日]
 * @Email yzengchn@163.com
 * @since  [产品/模块版本]
 */
@Entity
@Table(name="t_comments")
public class CommentInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    /**
    * 评论主键id
    */
    private String id;

    /**
    * 父评论id
    */
    private String pid;

    /**
    * 该评论所属资源id（人，博客，留言板）
    */
    private String ownerId;

    /**
    * 评论类型：1-人 2-博客 3-留言板
    */
    private int type;

    /**
    * 评论者id
    */
    private String fromId;

    /**
    * 评论者名字
    */
    private String fromName;

    /**
    * 被评论者id
    */
    private String toId;

    /**
    * 被评论者名字
    */
    private String toName;

    /**
    * 点赞的数量
    */
    private int likeNum;

    /**
    * 评论内容
    */
    private String content;

    /**
    * 创建时间
    */
    private Date createTime;

    /**
    * 修改时间
    */
    private Date updateTime;

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

	public int getType() {
		return type;
	}

	public void setType(int type) {
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

	public int getLikeNum() {
		return likeNum;
	}

	public void setLikeNum(int likeNum) {
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

    
}
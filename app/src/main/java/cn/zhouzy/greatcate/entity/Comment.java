package cn.zhouzy.greatcate.entity;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;

/**
 * Created by 正义 on 2017/5/5.
 * 评论表
 */

public class Comment extends BmobObject
{
	private String time;//评论时间
	private String content;//评论内容
	private String author;//作者

	public String getTime()
	{
		return time;
	}

	public void setTime(String time)
	{
		this.time = time;
	}

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	public String getAuthor()
	{
		return author;
	}

	public void setAuthor(String author)
	{
		this.author = author;
	}
}

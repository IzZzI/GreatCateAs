package cn.zhouzy.greatcate.entity;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;

/**
 * Created by 正义 on 2017/5/5.
 * 点赞表
 */

public class Like extends BmobObject
{
	private String time;//点赞时间
	private String author;//点赞者

	public String getTime()
	{
		return time;
	}

	public void setTime(String time)
	{
		this.time = time;
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

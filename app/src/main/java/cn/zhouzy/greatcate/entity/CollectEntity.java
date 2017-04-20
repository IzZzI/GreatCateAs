package cn.zhouzy.greatcate.entity;

import cn.bmob.v3.BmobObject;

public class CollectEntity extends BmobObject
{

	private static final long serialVersionUID = 1L;
	/**
	 * 图片地址
	 */
	private String albums;
	/**
	 * 菜谱名称
	 */
	private String title;
	/**
	 * 当前用户
	 */
	private String username;
	/**
	 * 介绍
	 */
	private String intro;
	private String id;
	/**
	 *  收藏时间
	 */
	private String collectTime;

	public String getCollectTime()
	{
		return collectTime;
	}

	public void setCollectTime(String collectTime)
	{
		this.collectTime = collectTime;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getAlbums()
	{
		return albums;
	}

	public void setAlbums(String albums)
	{
		this.albums = albums;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getIntro()
	{
		return intro;
	}

	public void setIntro(String intro)
	{
		this.intro = intro;
	}

}

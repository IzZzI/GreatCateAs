package cn.zhouzy.greatcate.entity;

import java.io.Serializable;

import cn.bmob.v3.BmobUser;

public class User extends BmobUser implements Serializable
{
	private static final long serialVersionUID = 1L;
	/**
	 * 昵称
	 */
	private String nickName;
	/**
	 * 头像url
	 */
	private String icon;
	/**
	 * 个人简介
	 */
	private String profile;
	

	public String getProfile()
	{
		return profile;
	}

	public void setProfile(String profile)
	{
		this.profile = profile;
	}

	public String getNickName()
	{
		return nickName;
	}

	public void setNickName(String nickName)
	{
		this.nickName = nickName;
	}

	public String getIcon()
	{
		return icon;
	}

	public void setIcon(String icon)
	{
		this.icon = icon;
	}

}

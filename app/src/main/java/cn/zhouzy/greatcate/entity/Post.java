package cn.zhouzy.greatcate.entity;

import java.io.Serializable;
import java.util.List;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobUser;

/**
 * Created by 正义 on 2017/5/5.
 * 心情帖子表
 */

public class Post extends BmobObject
{
	private String content;//文字内容
	private List<String> imgUrls;//图片内容
	private String author;//发送者
	private String time;//时间
	private String authorHeadPortrait;//头像url
	private List<Like> likes;//点赞
	private List<Comment> comments; //评论

	public String getAuthorHeadPortrait()
	{
		return authorHeadPortrait;
	}

	public void setAuthorHeadPortrait(String authorHeadPortrait)
	{
		this.authorHeadPortrait = authorHeadPortrait;
	}

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	public List<String> getImgUrls()
	{
		return imgUrls;
	}

	public void setImgUrls(List<String> imgUrls)
	{
		this.imgUrls = imgUrls;
	}

	public String getAuthor()
	{
		return author;
	}

	public void setAuthor(String author)
	{
		this.author = author;
	}

	public String getTime()
	{
		return time;
	}

	public void setTime(String time)
	{
		this.time = time;
	}

	public List<Like> getLikes()
	{
		return likes;
	}

	public void setLikes(List<Like> likes)
	{
		this.likes = likes;
	}

	public List<Comment> getComments()
	{
		return comments;
	}

	public void setComments(List<Comment> comments)
	{
		this.comments = comments;
	}
}

package cn.zhouzy.greatcate.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by gdxw on 2017/3/8.
 */

public class Data implements Serializable
{
	private String id;

	private String title;

	private String tags;

	private String imtro;

	private String ingredients;

	private String burden;

	private List<String> albums;

	private List<Steps> steps;

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getTags()
	{
		return tags;
	}

	public void setTags(String tags)
	{
		this.tags = tags;
	}

	public String getIngredients()
	{
		return ingredients;
	}

	public void setIngredients(String ingredients)
	{
		this.ingredients = ingredients;
	}

	public String getImtro()
	{
		return imtro;
	}

	public void setImtro(String imtro)
	{
		this.imtro = imtro;
	}

	public String getBurden()
	{
		return burden;
	}

	public void setBurden(String burden)
	{
		this.burden = burden;
	}

	public List<String> getAlbums()
	{
		return albums;
	}

	public void setAlbums(List<String> albums)
	{
		this.albums = albums;
	}

	public List<Steps> getSteps()
	{
		return steps;
	}

	public void setSteps(List<Steps> steps)
	{
		this.steps = steps;
	}

	@Override
	public String toString()
	{
		return "Data{" + "id='" + id + '\'' + ", title='" + title + '\'' + ", tags='" + tags + '\''
				+ ", imtro='" + imtro + '\'' + ", ingredients='" + ingredients + '\'' + ", burden='"
				+ burden + '\'' + ", albums=" + albums + ", steps=" + steps + '}';
	}
}

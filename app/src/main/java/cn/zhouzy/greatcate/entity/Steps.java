package cn.zhouzy.greatcate.entity;

import java.io.Serializable;

/**
 * Created by gdxw on 2017/3/8.
 */

public class Steps implements Serializable
{
	private String img;

	private String step;

	public String getImg()
	{
		return img;
	}

	public void setImg(String img)
	{
		this.img = img;
	}

	public String getStep()
	{
		return step;
	}

	public void setStep(String step)
	{
		this.step = step;
	}

	@Override
	public String toString()
	{
		return "Steps{" + "img='" + img + '\'' + ", step='" + step + '\'' + '}';
	}
}

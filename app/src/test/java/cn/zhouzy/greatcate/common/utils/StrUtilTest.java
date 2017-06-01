package cn.zhouzy.greatcate.common.utils;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by 正义 on 2017/3/29.
 */
public class StrUtilTest
{
	@Test
	public void strIsNullOrEmpty() throws Exception
	{
		StrUtil strUtil = new StrUtil();
		System.out.println( strUtil.strIsNullOrEmpty("a"));

	}

	@Test
	public void isEmail() throws Exception
	{
		StrUtil strUtil = new StrUtil();
		System.out.println(strUtil.isEmail("18@.com"));
	}

}
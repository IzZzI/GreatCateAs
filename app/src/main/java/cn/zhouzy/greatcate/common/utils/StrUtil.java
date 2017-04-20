package cn.zhouzy.greatcate.common.utils;

/**
 * Created by gdxw on 2017/3/9.
 */

public class StrUtil
{
	/**
	 * 判定字符是否为null或者空
	 * 
	 * @param str
	 * @return true null或空 false 不为null或者空
	 */
	public static boolean strIsNullOrEmpty(String str)
	{
		boolean flag = false;
		if (str == null || "".equals(str))
		{
			flag = true;
		}
		return flag;
	}

	public static boolean isEmail(String email)
	{
		Boolean isEmail = false;
		String expr = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		if (email.matches(expr))
		{
			isEmail = true;
		}
		return isEmail;

	}

}

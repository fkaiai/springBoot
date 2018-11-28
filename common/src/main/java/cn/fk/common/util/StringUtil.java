package cn.fk.common.util;

import java.util.Random;

public class StringUtil {

	public static boolean isEmpty(String str){
		return !isNotEmpty(str);
	}
	public static boolean isNotEmpty(String str){
		if(str != null && !("").equals(str.trim())){
			return true;
		}
		return false;
	}


	/**
	 *
	 * 方法描述 替换指定字符串方法
	 *
	 * @param text--字符串
	 * @param searchString--被替换的字符串
	 * @param replacement--替换字符串
	 * @param max 替换字符串searchString的最大个数
	 * @param ignoreCase 是否忽略大小写
	 * @return
	 *
	 * @author yaomy
	 * @date 2018年2月6日 下午5:50:28
	 */
	public static String replace(String text, String searchString, String replacement, int max, boolean ignoreCase)
	{
		if ((isEmpty(text)) || (isEmpty(searchString)) || (replacement == null) || (max == 0)) {
			return text;
		}
		String searchText = text;
		//如果忽略大小写text、searchString都转换为小写
		if (ignoreCase) {
			searchText = text.toLowerCase();
			searchString = searchString.toLowerCase();
		}
		int start = 0;
		//searchString 的起始索引
		int end = searchText.indexOf(searchString, start);
		if (end == -1) {
			return text;
		}
		//搜索字符串的长度
		int replLength = searchString.length();
		//搜索字符串和替换字符串的差值，如果小于0，字符串增量就为0
		int increase = replacement.length() - replLength;
		increase = increase < 0 ? 0 : increase;
		increase *= (max > 64 ? 64 : max < 0 ? 16 : max);
		StringBuilder buf = new StringBuilder(text.length() + increase);
		while (end != -1) {
			buf.append(text, start, end).append(replacement);
			start = end + replLength;
			max--; if (max == 0) {
				break;
			}
			end = searchText.indexOf(searchString, start);
		}
		buf.append(text, start, text.length());
		return buf.toString();
	}

	/**
	 * 将ASCII转成字符串的java方法
	 * @param value
	 * @return
	 */
	public static String asciiToString(String value)
	{
		StringBuffer sbu = new StringBuffer();
		String[] chars = value.split(",");
		for (int i = 0; i < chars.length; i++) {
			sbu.append((char) Integer.parseInt(chars[i]));
		}
		return sbu.toString();
	}

	/**
	 * 将字符串转成ASCII的java方法
	 * @param value
	 * @return
	 */
	public static String stringToAscii(String value)
	{
		StringBuffer sbu = new StringBuffer();
		char[] chars = value.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			if(i != chars.length - 1)
			{
				sbu.append((int)chars[i]).append(",");
			}
			else {
				sbu.append((int)chars[i]);
			}
		}
		return sbu.toString();
	}

	//手机号4567位和891011位换位置
	public static String mobileChangeCode(String mobile){
		// 可以用 StringBuilder 这个类，里面有一个接口replace，如下
		StringBuilder sb = new StringBuilder(mobile);
		sb.replace(3, 7, mobile.substring(7,11)).replace(7,11,mobile.substring(3,7));
		return sb.toString();
	}

	/**
	 * 获取一定长度的随机字符串
	 *
	 * @param length
	 *            指定字符串长度
	 * @return 一定长度的字符串
	 */
	public static String getRandomStringByLength(int length) {
		String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

}

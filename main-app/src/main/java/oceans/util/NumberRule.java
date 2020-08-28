package oceans.util;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class NumberRule implements Serializable {

	private static final long serialVersionUID = -4253317414152434379L;

	/**
	 * 生成18位编码
	 * 
	 * @param
	 * @return
	 * @throws Exception
	 */
	public static String createNum(){
		Date now = new Date();
		DateFormat df = new SimpleDateFormat("yyMMddHHmmss");
		String dateStr = df.format(now);// 年月日时分秒
		String random = RandomFive();// 五位随机数

		return  dateStr + random;
	}

	/**
	 * 生成5位随机数
	 * 
	 * @return
	 */
	public static String RandomFive() {
		int num = 0;
		int intFlag = (int) (Math.random() * 10000);

		String flag = String.valueOf(intFlag);
		if (flag.length() == 4 && flag.substring(0, 1).equals("9")) {
			num = intFlag;
		} else {
			intFlag = intFlag + 1000;
			num = intFlag;
		}
		return String.valueOf(num);
	}
}

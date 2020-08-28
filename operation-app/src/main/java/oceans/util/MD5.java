package oceans.util;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.security.MessageDigest;


public class MD5 {
	private static final Log logger = LogFactory.getLog(MD5.class);
	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d",
			"e", "f" };

	public static String encode(final String salt, String sourceStr) {
		String result = null;
		try {
			String source = "";
			MessageDigest md = MessageDigest.getInstance("MD5");
			// 混合SALT
			if (StringUtils.isNotBlank(salt)) {
				source = sourceStr + "{" + salt + "}";
			}else{
				source = sourceStr;
			}
			// 加密后的字符串
			result = byteArrayToHexString(md.digest(source.getBytes("UTF-8")));
		} catch (Exception ex) {
			logger.info(ex);
		}
		return result;
	}

	/**
	 * 转换字节数组为16进制字串
	 * 
	 * @param b
	 *            字节数组
	 * @return 16进制字串
	 */
	private static String byteArrayToHexString(final byte[] b) {
		StringBuilder resultSb = new StringBuilder();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}

	private static String byteToHexString(final byte b) {
		int n = b;
		if (n < 0)
			n = 256 + n;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

}

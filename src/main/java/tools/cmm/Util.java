package tools.cmm;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Util {

	/**
	 * ToStringBuilder 이용하여 객체 string 문자열 생성
	 * @param obj
	 * @return
	 */
	public static String reflectionToString(Object obj) {
		return ToStringBuilder.reflectionToString(obj);
	}

}

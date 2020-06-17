package tools.cmm;

import java.io.File;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Util {

	/**
	 * URL에 접속해서 JSoup Document 생성
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public static Document getJsoupDocumentByUrl(String url) throws Exception {
		
		Document doc = null;
		try {
			doc = Jsoup.connect(url).get();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return doc;
	}
	
	/**
	 * 파일을 읽어서 JSoup Document 생성
	 * @param input
	 * @return
	 * @throws Exception
	 */
	public static Document getJsoupDocumentByFile(String filePath) throws Exception {
		File input = new File(filePath);
		
		Document doc = null;
		try {
			doc = Jsoup.parse(input, "UTF-8");
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return doc;
	}
	
	/**
	 * ToStringBuilder 이용하여 객체 string 문자열 생성
	 * @param obj
	 * @return
	 */
	public static String reflectionToString(Object obj) {
		return ToStringBuilder.reflectionToString(obj);
	}

}

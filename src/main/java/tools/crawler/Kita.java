package tools.crawler;

import java.io.File;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Kita {

	/**
	 * KITA 공지사항 목록 페이지 파싱 수행
	 * https://www.kita.net/asocGuidance/notice/noticeList.do
	 * 
	 * @param doc
	 * @throws Exception
	 */
	void parseKitaNoticeList(Document doc) throws Exception {
		// 공지사항 목록
		Elements dataList = doc.select("div.boardList > ul > li");
		Iterator dataIter = dataList.iterator();
		while (dataIter.hasNext()) {
			Element data = (Element)dataIter.next();
			Element date = data.selectFirst("div.infoView > p.info > span.date");
			Element link = data.selectFirst("a");
			System.out.println(date.text()+"\t"+link.outerHtml());
			
			// 공지사항 상세 페이지로 이동
			Pattern pattern = Pattern.compile("fn_detail\\((\\d)\\, (\\d*)\\)");
			Matcher matcher = pattern.matcher(link.outerHtml());
			while (matcher.find()) {
				System.out.println(matcher.group());
				System.out.println(matcher.groupCount());
				System.out.println(matcher.group(1)+", "+matcher.group(2));
				String pageIdx = matcher.group(1);
				String detailKey = matcher.group(2);
				
				String detailUrl = "https://www.kita.net/asocGuidance/notice/noticeDetail.do?pageIndex="+pageIdx+"&nIndex="+detailKey;
				kitaNoticeDetailUrl(detailUrl);
			}
			
			break; // 테스트는 하나만
		}
	}

	/**
	 * KITA 공지사항 상세 페이지 파싱 수행
	 * https://www.kita.net/asocGuidance/notice/noticeDetail.do?pageIndex=1&nIndex=1796667
	 * 
	 * @param doc
	 * @throws Exception
	 */
	void parseKitaNoticeDetail(Document doc) throws Exception {
		Elements title = doc.select(".boardArea .sbjBox .sbj");
		System.out.println("title="+title.text());
		
		Element hit = doc.selectFirst(".boardArea .sbjBox .infoView .info .hit");
		System.out.println("hit="+hit.text());
		
		Element content = doc.selectFirst(".txtArea");
		System.out.println("content="+content.html());
	}
	
	public void kitaNoticeDetailUrl(String url) throws Exception {
		System.out.println("\n\n-------------------------------");
		System.out.println("[KITA 공지사항] "+url);
		System.out.println("-------------------------------");
		Document doc = null;
		try {
			doc = Jsoup.connect(url).get();
		} catch(Exception e) {
			e.printStackTrace();
		}

		parseKitaNoticeDetail(doc);
	}

	/**
	 * 미리 다운로드한 페이지 html 파일 읽어서 수행 (공지사항 목록)
	 * @param filePath
	 * @throws Exception
	 */
	public void kitaNoticeListFile(String filePath) throws Exception {
		File input = new File(filePath);
		Document doc = null;
		try {
			doc = Jsoup.parse(input, "UTF-8");
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		parseKitaNoticeList(doc);
	}
	
	/**
	 * KITA 공지사항 목록
	 * ex) https://www.kita.net/asocGuidance/notice/noticeList.do?pageIndex=3
	 * 
	 * @param filePath
	 * @throws Exception
	 */
	public void kitaNoticeListUrl(String url) throws Exception {
		Document doc = null;
		try {
			doc = Jsoup.connect(url).get();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		parseKitaNoticeList(doc);
	}
	
	/**
	 * 미리 다운로드한 페이지 html 파일 읽어서 수행 (공지사항 상세)
	 * @param filePath
	 * @throws Exception
	 */
	public void kitaNoticeDetailFile(String filePath) throws Exception {
		File input = new File(filePath);
		Document doc = null;
		try {
			doc = Jsoup.parse(input, "UTF-8");
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		parseKitaNoticeDetail(doc);
	}
	
}

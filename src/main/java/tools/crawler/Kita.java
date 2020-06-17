package tools.crawler;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tools.cmm.DateUtil;
import tools.cmm.Util;
import tools.data.DataInfo;
import tools.data.DataManage;

public class Kita {
	static Logger logger = LoggerFactory.getLogger(Kita.class);

	/**
	 * KITA 공지사항 목록 페이지 파싱 수행
	 * https://www.kita.net/asocGuidance/notice/noticeList.do
	 * 
	 * @param doc
	 * @throws Exception
	 */
	List<DataInfo> parseKitaNoticeList(Document doc) throws Exception {
		List<DataInfo> dataInfoList = new ArrayList<DataInfo>();
		
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
				DataInfo dataInfo = kitaNoticeDetailUrl(detailUrl);
				dataInfo.setUrl(detailUrl);
				dataInfoList.add(dataInfo);
			}
			
			break; // 테스트는 하나만
		}
		
		return dataInfoList;
	}

	/**
	 * KITA 공지사항 상세 페이지 파싱 수행
	 * https://www.kita.net/asocGuidance/notice/noticeDetail.do?pageIndex=1&nIndex=1796667
	 * 
	 * @param doc
	 * @throws Exception
	 */
	DataInfo parseKitaNoticeDetail(Document doc) throws Exception {
		Elements title = doc.select(".boardArea .sbjBox .sbj");
		System.out.println("title="+title.text());
		
		Element hit = doc.selectFirst(".boardArea .sbjBox .infoView .info .hit");
		System.out.println("hit="+hit.text());

		Element pubDate = doc.selectFirst(".boardArea .sbjBox .infoView .info .date");
		System.out.println("pubDate="+pubDate.text());

		Element content = doc.selectFirst(".txtArea");
		System.out.println("content="+content.html());
		
		DataInfo data = new DataInfo();
		data.setTitle(title.text());
//		data.setUrl("");
		data.setDataType("");
		data.setSource("KITA");
		data.setContent(content.html());
		data.setHitCount(NumberUtils.toInt(StringUtils.replaceAll(hit.text(), "\\,", "")));
		data.setPubDate(DateUtil.str2cal(StringUtils.strip(pubDate.text()), DateUtil.DATE_FORMAT).getTime());
		data.setColDate(DateUtil.getCurrentDate());
		data.setUseYn("Y");
		logger.debug("parseKitaNoticeDetail() data={}", data);
		
		return data;
	}
	
	public DataInfo kitaNoticeDetailUrl(String url) throws Exception {
		System.out.println("\n\n-------------------------------");
		System.out.println("[KITA 공지사항] "+url);
		System.out.println("-------------------------------");
		
		Document doc = Util.getJsoupDocumentByUrl(url);

		DataInfo data = parseKitaNoticeDetail(doc);
		return data;
	}

	/**
	 * 미리 다운로드한 페이지 html 파일 읽어서 수행 (공지사항 목록)
	 * @param filePath
	 * @throws Exception
	 */
	public void kitaNoticeListFile(String filePath) throws Exception {
		Document doc = Util.getJsoupDocumentByFile(filePath);
		
		List<DataInfo> list = parseKitaNoticeList(doc);
		
		// DB 등록
		for(DataInfo dataInfo : list) {
			DataManage.insertData(dataInfo);
		}
	}
	
	/**
	 * KITA 공지사항 목록
	 * ex) https://www.kita.net/asocGuidance/notice/noticeList.do?pageIndex=3
	 * 
	 * @param filePath
	 * @throws Exception
	 */
	public void kitaNoticeListUrl(String url) throws Exception {
		Document doc = Util.getJsoupDocumentByUrl(url);
		
		List<DataInfo> list = parseKitaNoticeList(doc);
		
		// DB 등록
		for(DataInfo dataInfo : list) {
			DataManage.insertData(dataInfo);
		}
	}
	
	/**
	 * 미리 다운로드한 페이지 html 파일 읽어서 수행 (공지사항 상세)
	 * @param filePath
	 * @throws Exception
	 */
	public void kitaNoticeDetailFile(String filePath) throws Exception {
		Document doc = Util.getJsoupDocumentByFile(filePath);
		
		DataInfo data = parseKitaNoticeDetail(doc);
		
		// DB 저장
		DataManage.insertData(data);
	}
	
}

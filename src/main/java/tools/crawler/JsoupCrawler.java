package tools.crawler;

import java.util.Iterator;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tools.cmm.Util;

public class JsoupCrawler {
	
	static Logger logger = LoggerFactory.getLogger(JsoupCrawler.class);

	/**
	 * 위키피디아 페이지 파싱 수행
	 * 
	 * @param doc
	 * @throws Exception
	 */
	void parseWiki(Document doc) throws Exception {
		Elements tocs = doc.select("#toc");
		logger.info("tocs={}", tocs.text());
		
		Elements links = doc.select("a[href]");
		Iterator linksIter = links.iterator();
		while (linksIter.hasNext()) {
			Element link = (Element)linksIter.next();
			logger.info("text={}, outerHtml={}", link.text(), link.outerHtml());
		}

	}

	/**
	 * 미리 다운로드한 위키피디아 페이지 html 파일 읽어서 수행
	 * @param filePath
	 * @throws Exception
	 */
	public void wikiFile(String filePath) throws Exception {
		Document doc = Util.getJsoupDocumentByFile(filePath);
		
		parseWiki(doc);
	}
	
	/**
	 * 위키피디아 페이지 직접 접속하여 수행
	 * ex) https://ko.wikipedia.org/wiki/%EC%BD%98%ED%85%90%EC%B8%A0_(%EB%AF%B8%EB%94%94%EC%96%B4)
	 * 
	 * @param url
	 * @throws Exception
	 */
	public void wikiUrl(String url) throws Exception {
		logger.info("wikiByUrl() start... url={}", url);
		
		Document doc = Util.getJsoupDocumentByUrl(url);
		
		parseWiki(doc);
	}
	
}

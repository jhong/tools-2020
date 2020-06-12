package tools.crawler;

import org.junit.Test;

import tools.cmm.TestConstant;

public class JsoupCrawlerTest {
	
	
	@Test
	public void wikiFile() throws Exception {
		String filePath = TestConstant.TEST_DATA_DIR + "/ko.wikipedia.org_wiki_콘텐츠_(미디어).html";
		
		JsoupCrawler crawler = new JsoupCrawler();
		crawler.wikiFile(filePath);
	}

	@Test
	public void wikiUrl() throws Exception {
		String url = "https://ko.wikipedia.org/wiki/%EC%BD%98%ED%85%90%EC%B8%A0_(%EB%AF%B8%EB%94%94%EC%96%B4)";
		
		JsoupCrawler crawler = new JsoupCrawler();
		crawler.wikiUrl(url);
	}
}

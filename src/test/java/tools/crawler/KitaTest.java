package tools.crawler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import tools.cmm.TestConstant;

public class KitaTest {

	@Test
	public void kitaNoticeListFile() throws Exception {
		String filePath = TestConstant.TEST_DATA_DIR + "/kita.net_asocGuidance_notice_noticeList.do.html";
		
		Kita crawler = new Kita();
		crawler.kitaNoticeListFile(filePath);
	}
	
	@Test
	public void kitaNoticeDetailtFile() throws Exception {
		String filePath = TestConstant.TEST_DATA_DIR + "/kita.net_asocGuidance_notice_noticeDetail.do_nIndex=1796667.html";
		
		Kita crawler = new Kita();
		crawler.kitaNoticeDetailFile(filePath);
	}
	
	@Test
	public void kitaNoticeListUrl() throws Exception {
		String url = "https://www.kita.net/asocGuidance/notice/noticeList.do?pageIndex=3";
		
		Kita crawler = new Kita();
		crawler.kitaNoticeListUrl(url);
	}

	
	@Test
	public void regexTest() throws Exception {
		Pattern pattern = Pattern.compile("fn_detail\\((\\d)\\, (\\d*)\\)");
//		String txt = "fn_detail(1, 1796667)";
		String txt = "<a href=\"javascript:fn_detail(2, 1798749)\" class=\"sbj\"> [무역아카데미] 외환딜러가 일러주는 환율예측 노하우(6/29) 개최</a>";

		Matcher matcher = pattern.matcher(txt);
		while (matcher.find()) {
			System.out.println(matcher.group());
			System.out.println(matcher.groupCount());
			System.out.println(matcher.group(1)+", "+matcher.group(2));
		}
	}

}

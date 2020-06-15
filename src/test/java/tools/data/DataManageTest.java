package tools.data;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.Test;

import tools.cmm.DateUtil;

public class DataManageTest {

	@Test
	public void getData() throws Exception {
		Integer id = 1;
		
//		DataManage mng = new DataManage();
		DataInfo result = DataManage.getData(id);
		System.out.println("getData() result="+result);
	}
	
	@Test
	public void insertData() throws Exception {
		DataInfo data = new DataInfo();
		data.setTitle("테스트");
		data.setContent("내용\n123");
//		data.setHitCount(10);
		data.setHitCount(NumberUtils.toInt(StringUtils.replaceAll("12,002", "\\,", "")));
		data.setPubDate(DateUtil.str2cal("2020-06-15", DateUtil.DATE_FORMAT).getTime());
		data.setColDate(DateUtil.getCurrentDate());
		data.setUseYn("N");
		System.out.println("insertData() data="+data);
		
//		DataManage mng = new DataManage();
//		DataManage.insertData(data);
	}
	
}

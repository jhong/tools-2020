package tools.data;

import org.junit.Test;

import tools.cmm.DateUtil;

public class DataManageTest {

	@Test
	public void getData() throws Exception {
		Integer id = 1;
		
		DataManage mng = new DataManage();
		DataInfo result = mng.getData(id);
		System.out.println("getData() result="+result);
	}
	
	@Test
	public void insertData() throws Exception {
		DataInfo data = new DataInfo();
		data.setTitle("테스트");
		data.setContent("내용\n123");
		data.setHitCount(10);
		data.setPubDate(DateUtil.getCurrentDate());
		data.setUseYn("Y");
		
		DataManage mng = new DataManage();
		mng.insertData(data);
	}
	
}

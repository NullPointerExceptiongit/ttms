package team.dao;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import cn.tedu.ttms.common.web.PageObject;
import cn.tedu.ttms.team.dao.TeamDao;
import cn.tedu.ttms.team.entity.Team;
import common.dao.TestBaseDao;

public class TestTeamDao extends TestBaseDao {
	TeamDao dao;
	
	
	@Test
	public void testInserObject(){
		dao=ctx.getBean("teamDao",TeamDao.class);
		
		Team team =new Team();
		team.setName("华山两日游");
		team.setProjectId(3);
		team.setNote("华山两日游");
		team.setValid(1);
		team.setCreatedUser("admin");
		team.setModifiedUser("admin");
		dao.insertObject(team);
		System.out.println(team);
		
		
	}
	
	//将查询结果显示的页面
	@Test
	public void testFindObject(){
		dao=ctx.getBean("teamDao",TeamDao.class);
		
		String projectName=null;
		Integer valid=1;
		int startIndex=0;
		int pageSize=2;
		
		List<Map<String, Object>> list=dao.findPageObjects(null, 1, 0, 2);
		
		Assert.assertNotEquals(null, list);
		System.out.println(list);
		
		
		
		 int rowCount=dao.getRowCount(projectName, valid);
		 PageObject  pageObject=new PageObject();
		 pageObject.setRowCount(rowCount);
		 pageObject.setPageSize(pageSize);
	}
	
	
	
	
	 
	
	
	
}

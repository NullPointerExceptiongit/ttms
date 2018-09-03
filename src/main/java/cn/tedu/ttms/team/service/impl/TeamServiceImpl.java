package cn.tedu.ttms.team.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.tedu.ttms.common.exception.SaveRuntimeException;
import cn.tedu.ttms.common.web.PageObject;
import cn.tedu.ttms.project.dao.ProjectDao;
import cn.tedu.ttms.team.dao.TeamDao;
import cn.tedu.ttms.team.entity.Team;
import cn.tedu.ttms.team.service.TeamService;
@Service
public class TeamServiceImpl implements TeamService {
	@Resource
	private TeamDao teamDao;
	
	private Team team;
	@Resource
	ProjectDao  projectDao;

	public int insertObject(Team team) {
		// TODO Auto-generated method stub
		return 0;
	}

	public Map<String, Object> findPageObjects(			
			String projectName, Integer valid, Integer pageCurrent) {
		
				PageObject pageObject=new 	PageObject();
				pageObject.setPageCurrent(pageCurrent);			
				
				//1.获得页面表格中要显示的数据
				/*根据stratindex  以及参数获得当前页的数据*/
				List<Map<String, Object>> list=teamDao.findPageObjects(
								projectName, valid,
								pageObject.getStartIndex(),
								pageObject.getPageSize());
				
						
				//2.获得总页数
				int rowCount=teamDao.getRowCount(projectName,valid);
						
				
				//3.构建map对象封装从dao层获得的数据
				Map<String,Object> map=	new HashMap<String,Object>();
				map.put("list", list);//记录信息
				map.put("pageObject", pageObject);//分页数据
				
				
				return map;
		
	}

	public int getRowCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void saveObject(Team team) {
		if(team==null){
			throw new SaveRuntimeException("保存信息不能");
		}		
		int rows=teamDao.insertObject(team);
		if(rows==-1){
			throw new SaveRuntimeException("保存失败");
		}
		
		
	}

	public List<Map<String, Object>> findProjectIdAndNames() {
		
		return projectDao.findIdAndNames();
	}

	
}

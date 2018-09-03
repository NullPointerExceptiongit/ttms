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
				
				//1.���ҳ������Ҫ��ʾ������
				/*����stratindex  �Լ�������õ�ǰҳ������*/
				List<Map<String, Object>> list=teamDao.findPageObjects(
								projectName, valid,
								pageObject.getStartIndex(),
								pageObject.getPageSize());
				
						
				//2.�����ҳ��
				int rowCount=teamDao.getRowCount(projectName,valid);
						
				
				//3.����map�����װ��dao���õ�����
				Map<String,Object> map=	new HashMap<String,Object>();
				map.put("list", list);//��¼��Ϣ
				map.put("pageObject", pageObject);//��ҳ����
				
				
				return map;
		
	}

	public int getRowCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void saveObject(Team team) {
		if(team==null){
			throw new SaveRuntimeException("������Ϣ����");
		}		
		int rows=teamDao.insertObject(team);
		if(rows==-1){
			throw new SaveRuntimeException("����ʧ��");
		}
		
		
	}

	public List<Map<String, Object>> findProjectIdAndNames() {
		
		return projectDao.findIdAndNames();
	}

	
}

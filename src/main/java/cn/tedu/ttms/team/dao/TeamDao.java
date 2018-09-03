package cn.tedu.ttms.team.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.tedu.ttms.common.dao.BaseDao;
import cn.tedu.ttms.project.dao.ProjectDao;
import cn.tedu.ttms.team.entity.Team;

public interface TeamDao  extends BaseDao<Team>{
	
	public int insertObject(Team team);
	/**
	 * һ����¼��Ӧһ��Map(keyΪ�е�����,valueΪ�е�ֵ)
	 * ������¼�Ƕ��map����,Ȼ����map�ŵ�list����
	  */
	public List< Map<String,Object>> findPageObjects(
			@Param("projectName")String projectName,
			@Param("valid")Integer valid,
			@Param("startIndex")int startIndex,
			@Param("pageSize")int pageSize
			);
	
	/**ͳ���ж�������¼*/
	public int getRowCount(
			@Param("projectName")String projectName,
			@Param("valid")Integer valid);
	
	/**�����team��Ϣ*/
	public void saveObject(Team team);
	

}

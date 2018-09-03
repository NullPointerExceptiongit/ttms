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
	 * 一条记录对应一个Map(key为列的名字,value为列的值)
	 * 多条记录是多个map对象,然后多个map放到list集合
	  */
	public List< Map<String,Object>> findPageObjects(
			@Param("projectName")String projectName,
			@Param("valid")Integer valid,
			@Param("startIndex")int startIndex,
			@Param("pageSize")int pageSize
			);
	
	/**统计有多少条记录*/
	public int getRowCount(
			@Param("projectName")String projectName,
			@Param("valid")Integer valid);
	
	/**保存表单team信息*/
	public void saveObject(Team team);
	

}

package cn.tedu.ttms.team.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import cn.tedu.ttms.team.entity.Team;
@Service
public interface TeamService {
	public int insertObject (Team team);
	public Map<String,Object> 
	findPageObjects(String projectName,
			Integer valid,
			Integer pageCurrent);
	public int getRowCount();
	public void saveObject(Team team);
	
	public List<Map<String,Object>> findProjectIdAndNames();
	

}

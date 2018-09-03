package cn.tedu.ttms.team.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.ttms.common.web.JsonResult;
import cn.tedu.ttms.project.dao.ProjectDao;
import cn.tedu.ttms.team.entity.Team;
import cn.tedu.ttms.team.service.TeamService;

@Controller
@RequestMapping("/team")
public class TeamController {
	@Resource
	private TeamService teamService;
	@Resource
	private ProjectDao project;
	
	@RequestMapping("/listUI")
	public String listUi(){		
		return "team/team_list";
		
	}
	
	
	@RequestMapping("/editUI")
	public String editUI(){
		return "team/team_edit";
	}
	
	@RequestMapping("/dofindPageObjects")
	@ResponseBody
	public JsonResult doFindPageObjects(
			String projectName,Integer valid,
			//当前页（传过来的）
			Integer pageCurrent){//pageCurrent		
		Map<String,Object> map=
				teamService.findPageObjects(projectName, valid, pageCurrent);
	    return new JsonResult(map);//state=1,message=ok,data=map
	}
	
	@RequestMapping("/doSaveObject")
	@ResponseBody
	public JsonResult doSaveObject(Team team){
		teamService.saveObject(team);
		return new JsonResult();
	}
	
	@RequestMapping("/dofindProjIdAndNames")
	@ResponseBody
	public JsonResult  dofindProjectIdAndNames() {	
		List<Map<String,Object>>  map=teamService.findProjectIdAndNames();
		System.out.println("dofindProjIdAndNames()");
		return new JsonResult(map);
		
	}

}

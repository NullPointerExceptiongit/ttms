package cn.tedu.ttms.attachement.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.tedu.ttms.attachement.entity.Attachement;
import cn.tedu.ttms.attachement.service.AttachementService;
import cn.tedu.ttms.common.web.JsonResult;
@Controller
@RequestMapping("/attach")
public class AttachementController {
	@Resource
	private AttachementService attachementService;
	@RequestMapping("/uploadUI")
	public String uploadUI(){
		return "attachement/attachement_list";
	}

	@RequestMapping("/doSaveObject")
	@ResponseBody
	public JsonResult dosaveObject(
			String title,
			Integer athType,
			Integer belongId,
			MultipartFile mFile,
			HttpServletRequest request){
		/*System.out.println("title:"+title+"     athType:"+athType
				       +"   belongId:"+belongId+ "   mFile :  "+mFile);*/
		//获得路径
		String serverPath=request.getServletContext().getRealPath("/");
		/*System.out.println("serverPath="+serverPath);*/
		attachementService.saveObject(
				title, athType, belongId, mFile,serverPath);
		return new JsonResult();
	}
	
	
	
	@RequestMapping("/doFindObjects")
	@ResponseBody
	public JsonResult doFindObject(){
		List<Attachement>  list=attachementService.findObject();
		return new JsonResult(list);
	}
	
	
	@RequestMapping("/doDownload")	
	@ResponseBody
	public byte[]  doDownload(Integer id,HttpServletResponse response) throws IOException{
		/*1.根据id查找记录*/
		File file=attachementService.findObjectById(id);
		
		/*设置消息头（固定格式）*/
		response.setContentType("appliction/octet-stream");
		response.setHeader("Content-disposition","attachment;filename="+file.getName());
		
		/*2.获得记录的真时路径*/
		//String realPath=file.getPath();
		/*3.构建文件对象关联的真是路径*/
		/*4.检查文件是否存在，存在则下载*/
		
		Path path=Paths.get(file.getPath());
		/*将文件的字节返回  （spring  mvc 会自动将这个字节写入）*/
		return Files.readAllBytes(path);
	}
	
	
	
}

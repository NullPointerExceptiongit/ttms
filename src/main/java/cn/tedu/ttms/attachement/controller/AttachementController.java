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
		//���·��
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
		/*1.����id���Ҽ�¼*/
		File file=attachementService.findObjectById(id);
		
		/*������Ϣͷ���̶���ʽ��*/
		response.setContentType("appliction/octet-stream");
		response.setHeader("Content-disposition","attachment;filename="+file.getName());
		
		/*2.��ü�¼����ʱ·��*/
		//String realPath=file.getPath();
		/*3.�����ļ��������������·��*/
		/*4.����ļ��Ƿ���ڣ�����������*/
		
		Path path=Paths.get(file.getPath());
		/*���ļ����ֽڷ���  ��spring  mvc ���Զ�������ֽ�д�룩*/
		return Files.readAllBytes(path);
	}
	
	
	
}

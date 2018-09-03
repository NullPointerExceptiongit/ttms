package cn.tedu.ttms.attachement.service.impl;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import cn.tedu.ttms.attachement.dao.AttachementDao;
import cn.tedu.ttms.attachement.entity.Attachement;
import cn.tedu.ttms.attachement.service.AttachementService;

@Service
public class AttachementServiceImpl implements AttachementService{

	@Resource
	private AttachementDao attachementDao;
	
	public void saveObject(String title, 
			Integer athType,Integer belongId,
			MultipartFile mFile,
			String serverPath) {
		//1.上传文件
		//1.1获得文件名
		String oFileName=mFile.getOriginalFilename();
		System.out.println("oFileName="+oFileName);
		byte[] fileBytes;
		File dest;//目标文件(上传以后存储的文件)
		String fileDigest;//文件摘要　
		try{
		//1.2获得文件字节
		fileBytes=mFile.getBytes();
		fileDigest=DigestUtils.md5Hex(fileBytes);
		//根据文件摘要判定文件是否已经有了
		int count=
		attachementDao.findObjectByDisgest(fileDigest);
		if(count>0)throw new RuntimeException("文件已经存在");
		//1.3执行上传动作
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
		String dateStr=sdf.format(new Date());
		//                              随机生成字符串
		String newFileName=UUID.randomUUID().toString()+"."
				//获得扩展名
				+FilenameUtils.getExtension(oFileName);
		
		
		String realPath=serverPath+dateStr+File.separator+oFileName;
	    dest=new File(realPath);	
		File parent=dest.getParentFile();
		if(!parent.exists()){
			parent.mkdirs();
		}
		mFile.transferTo(dest);//上传
		}catch(IOException e){
		e.printStackTrace();
		throw new RuntimeException("upload error");
		}
		//2.保存文件信息到数据库
		Attachement t=new Attachement();
		t.setTitle(title);
		t.setFileName(oFileName);
		t.setFilePath(dest.getPath());
		t.setAthType(athType);//1
		t.setBelongId(belongId);//1
		t.setContentType(mFile.getContentType());
		t.setFileDisgest(fileDigest);
		t.setCreatedUser("admin");
		t.setModifiedUser("admin");
		attachementDao.insertObject(t);
	}

	@Override
	public List<Attachement> findObject() {
		
		return attachementDao.findObjects();
	}

	
	
	
	/*2.获得记录的真时路径*/
	
	/*3.构建文件对象关联的真是路径*/
	
	/*4.检查文件是否存在，存在则下载*/
	
	
	
	public File findObjectById(Integer id) {
		if(id==null)
			throw new RuntimeException("id can be null");
		/*1.根据id查找记录*/
		Attachement a=attachementDao.findObjectById(id);
		
		if(a==null)
			throw new RuntimeException("数据库没有对应的记录");
		/*2.获得记录的真时路径，并构建文件对象关联的真是路径*/
		File file=new File(a.getFilePath());
		
		
		if(!file.exists())
			throw new RuntimeException("文件已经不存在");
		
		return file;
	}
}

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
		//1.�ϴ��ļ�
		//1.1����ļ���
		String oFileName=mFile.getOriginalFilename();
		System.out.println("oFileName="+oFileName);
		byte[] fileBytes;
		File dest;//Ŀ���ļ�(�ϴ��Ժ�洢���ļ�)
		String fileDigest;//�ļ�ժҪ��
		try{
		//1.2����ļ��ֽ�
		fileBytes=mFile.getBytes();
		fileDigest=DigestUtils.md5Hex(fileBytes);
		//�����ļ�ժҪ�ж��ļ��Ƿ��Ѿ�����
		int count=
		attachementDao.findObjectByDisgest(fileDigest);
		if(count>0)throw new RuntimeException("�ļ��Ѿ�����");
		//1.3ִ���ϴ�����
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
		String dateStr=sdf.format(new Date());
		//                              ��������ַ���
		String newFileName=UUID.randomUUID().toString()+"."
				//�����չ��
				+FilenameUtils.getExtension(oFileName);
		
		
		String realPath=serverPath+dateStr+File.separator+oFileName;
	    dest=new File(realPath);	
		File parent=dest.getParentFile();
		if(!parent.exists()){
			parent.mkdirs();
		}
		mFile.transferTo(dest);//�ϴ�
		}catch(IOException e){
		e.printStackTrace();
		throw new RuntimeException("upload error");
		}
		//2.�����ļ���Ϣ�����ݿ�
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

	
	
	
	/*2.��ü�¼����ʱ·��*/
	
	/*3.�����ļ��������������·��*/
	
	/*4.����ļ��Ƿ���ڣ�����������*/
	
	
	
	public File findObjectById(Integer id) {
		if(id==null)
			throw new RuntimeException("id can be null");
		/*1.����id���Ҽ�¼*/
		Attachement a=attachementDao.findObjectById(id);
		
		if(a==null)
			throw new RuntimeException("���ݿ�û�ж�Ӧ�ļ�¼");
		/*2.��ü�¼����ʱ·�����������ļ��������������·��*/
		File file=new File(a.getFilePath());
		
		
		if(!file.exists())
			throw new RuntimeException("�ļ��Ѿ�������");
		
		return file;
	}
}

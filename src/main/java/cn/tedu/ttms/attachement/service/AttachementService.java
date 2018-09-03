package cn.tedu.ttms.attachement.service;

import java.io.File;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import cn.tedu.ttms.attachement.entity.Attachement;

public interface AttachementService {
	
	
	/**
     * @param title ��������
     * @param athType ������������(�����Ʒ����)
     * @param belongId ��������id(��������ĳ����Ʒ��id)
     * @param mFile �ϴ����ļ�����
     */
	public void saveObject(
			String title,
			Integer athType,
			Integer belongId,
			MultipartFile mFile,
			String serverPath);
	
	
	List<Attachement> findObject();
	File findObjectById(Integer id);
	
	

}
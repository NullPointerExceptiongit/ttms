package cn.tedu.ttms.attachement.dao;

import java.util.List;

import cn.tedu.ttms.attachement.entity.Attachement;
import cn.tedu.ttms.common.dao.BaseDao;

public interface AttachementDao  extends BaseDao{

	List<Attachement>findObjects();
	int findObjectByDisgest(String fileDisgest);
	Attachement findObjectById(Integer id);
}

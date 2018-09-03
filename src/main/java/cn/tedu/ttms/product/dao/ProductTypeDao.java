package cn.tedu.ttms.product.dao;

import java.util.List;
import java.util.Map;

import cn.tedu.ttms.common.dao.BaseDao;
import cn.tedu.ttms.product.entity.ProductType;
/**��Ʒ����*/


public interface ProductTypeDao extends 
    BaseDao<ProductType>{
	List<Map<String,Object>> findObjects();
	
	/**����zTree nodes �ڵ�*/
	List<Map<String,Object>> findTreeNodes();
	
	Map<String,Object> findObjectById(Integer id);
	/*���id�µ���Ԫ�صĸ���*/
	int  hasChilds(Integer id);
	/*����idɾ������*/
	int  deleteObject(Integer id);
}


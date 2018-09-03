package cn.tedu.ttms.product.service;

import java.util.List;
import java.util.Map;

import cn.tedu.ttms.product.entity.ProductType;

public interface ProductTypeService {
	/**��ѯ��Ʒ����һ������ӷ���,����treeGrid�н�����ʾ*/
	List<Map<String,Object>> findObjects();
	/**��ѯ������Ϣ��zTree�н������ݳ���*/
	List<Map<String,Object>> findTreeNodes();
	void saveObject(ProductType type);
	Map<String,Object> findObjectById(Integer id);
	void updateObject(ProductType type);
	
	/*����Ҫ��ʾɾ���˼������ݾ���void*/
	void deleteObject(Integer id);
	
}

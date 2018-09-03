package productType.dao;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import cn.tedu.ttms.product.dao.ProductTypeDao;
import cn.tedu.ttms.product.entity.ProductType;
import common.dao.TestBaseDao;

public class testProductType extends TestBaseDao{

	@Test
	public void testFindObjects(){
		ProductTypeDao typeDao=
	    (ProductTypeDao)
		ctx.getBean("productTypeDao");
		List<Map<String,Object>> list=
		typeDao.findObjects();
		System.out.println(list);
		Assert.assertNotEquals(null, list);
	}
	
	@Test
	public void testInsertObject(){
		ProductTypeDao typeDao=
	    (ProductTypeDao)
	    ctx.getBean("productTypeDao");
		ProductType t1=new ProductType();
		t1.setName("马拉松三日游");
		t1.setSort(3);
		t1.setParentId(145);
		t1.setNote("马拉松三日游....");
		t1.setCreatedUser("admin");
		t1.setModifiedUser("admin");
		int rows=typeDao.insertObject(t1);
		Assert.assertEquals(1, rows);
		
	}
	
	
	@Test
	public void testupdateObject(){
		ProductTypeDao typeDao=
			    (ProductTypeDao)
			    ctx.getBean("productTypeDao");
		Map<String ,Object> map=typeDao.findObjectById(136);
		ProductType type=new ProductType();
		type.setName("华北地区");
		type.setNote("华北地区......");
		int rows=typeDao.updateObject(type);
		
		
	}
	
}

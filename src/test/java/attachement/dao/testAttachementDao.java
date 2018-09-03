package attachement.dao;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Assert;
import org.junit.Test;

import cn.tedu.ttms.attachement.dao.AttachementDao;
import cn.tedu.ttms.attachement.entity.Attachement;
import common.dao.TestBaseDao;

public class testAttachementDao  extends TestBaseDao{
	@Test
	public void testInsertObject(){
		 AttachementDao dao=
				 ctx.getBean("attachementDao", AttachementDao.class);
		 Attachement t=new  Attachement();
		 t.setTitle("title-a");
		 t.setFileName("a.png");
		 t.setFilePath("c:/a/b/c");
		 t.setContentType("imagaes/png");
		 String fileContent="helloworkd";
		 String digest=DigestUtils.md5Hex(fileContent.getBytes());
		 t.setFileDisgest(digest);
		 t.setCreatedUser("admin");
		 int rows=dao.insertObject(t);
		 Assert.assertEquals(1, rows);
		 System.out.println(t);
		 
	}

}

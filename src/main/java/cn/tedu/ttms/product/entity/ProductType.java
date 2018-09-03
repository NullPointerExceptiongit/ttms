package cn.tedu.ttms.product.entity;

import java.io.Serializable;
import java.util.Date;

public class ProductType implements Serializable {

	private static final long serialVersionUID = -4024654706746365592L;
	/**����id*/
	private Integer id;
	/**��������*/
	private String name;
	private Integer sort;
	/**��һ������id*/
	private Integer parentId;
	/**��ע*/
	private String note;
	private Date createdTime;
	/**��Ŀ���޸�ʱ��*/
	private Date modifiedTime;
	/**��Ŀ�Ĵ�����*/
	private String createdUser;
	/**��Ŀ���޸�*/
    private String modifiedUser;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public Date getModifiedTime() {
		return modifiedTime;
	}
	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	public String getCreatedUser() {
		return createdUser;
	}
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}
	public String getModifiedUser() {
		return modifiedUser;
	}
	public void setModifiedUser(String modifiedUser) {
		this.modifiedUser = modifiedUser;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
    
    
    
    
	
}

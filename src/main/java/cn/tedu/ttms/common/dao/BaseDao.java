package cn.tedu.ttms.common.dao;
/**
 * ���ϵķ���
 * @author Noah
 *
 * @param <T>
 */
public interface BaseDao<T> {

	int insertObject (T t);
	int updateObject(T t);
	//T findObjectById(Integer id);
}

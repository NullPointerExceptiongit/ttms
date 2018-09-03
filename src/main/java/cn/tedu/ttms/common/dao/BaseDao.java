package cn.tedu.ttms.common.dao;
/**
 * 类上的泛型
 * @author Noah
 *
 * @param <T>
 */
public interface BaseDao<T> {

	int insertObject (T t);
	int updateObject(T t);
	//T findObjectById(Integer id);
}

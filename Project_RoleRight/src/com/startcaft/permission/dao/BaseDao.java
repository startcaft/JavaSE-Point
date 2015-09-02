package com.startcaft.permission.dao;

import java.io.Serializable;
import java.util.List;

/**
 * BaseDAO 定义DAO的通用操作 
 * @param <T>-要操作的实体对象
 */
public interface BaseDao<T> {
	
	public void save(T entity);  
	  
    public void update(T entity);  
  
    public void delete(Serializable id);  
  
    public T findById(Serializable id);  
  
    public List<T> findByHQL(String hql, Object... params);  
    
    public List<T> pageByHQL(String hql,int pageIndex,int pageSize,Object... params);
}

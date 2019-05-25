package com.wlh.jpa.handlers;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.RowProcessor;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.lang3.reflect.MethodUtils;

import com.wlh.exception.ConvertRunException;
import com.wlh.jpa.IJpaRepository;
import com.wlh.jpa.JpaRepositoryImp;
import com.wlh.jpa.entity.JpaEntity;
import com.wlh.jpa.entity.ManyOnManyEntity;
import com.wlh.jpa.entity.ORMEntity;
import com.wlh.jpa.entity.OneOnManyEntity;
import com.wlh.jpa.entity.OneOnOneEntity;
import com.wlh.jpa.entity.PrimitiveEntity;

public class CascadeBeanProcessor extends GenerousBeanProcessor {
	IJpaRepository repository;
	Class cla;
	// ColumnListHandler
	public static final ResultSetHandler<List<String>> HANDLER = new ColumnListHandler();
	
	public CascadeBeanProcessor(IJpaRepository repository, Class cla) {
		super();
		this.repository = repository;
		this.cla = cla;
	}

	public CascadeBeanProcessor( IJpaRepository repository) {
		this.repository = repository;
	}

	@Override
	public <T> List<T> toBeanList(ResultSet rs, Class<? extends T> type)
			throws SQLException {
		List<T> results = new ArrayList<T>();
		while (rs.next()) {
            results.add(toBean(rs, type));
        }
		return results;
	}

	@Override
	protected Object processColumn(ResultSet rs, int index, Class<?> propType)
			throws SQLException {
		PropertyDescriptor[] props = getPropertyDescriptors();
		int[] columnToProperty = this.mapColumnsToProperties(rs.getMetaData(), props);
		PropertyDescriptor propertyDescriptors = props[ columnToProperty[index] ]; 
		
		ORMEntity ormEntity = new ORMEntity( cla );
		for (PrimitiveEntity element : ormEntity.getAllPrimitive()) {
			if(element.equals(propertyDescriptors.getName() ) ){
				return super.processColumn(rs, index, propType);
			}
		}
		for (OneOnOneEntity element : ormEntity.getAllOneOnOne()) {
			if(element.equals(propertyDescriptors.getName() ) ){
				JpaEntity findOne = repository.findOne( element.getOnClass(), rs.getString(index) );
				if(findOne ==null) 
					findOne = (JpaEntity) this.newInstance(element.getOnClass() );
				return findOne;
			}
		}
		for (OneOnManyEntity element : ormEntity.getAllOneOnMany()) {
			if(element.equals(propertyDescriptors.getName() ) ){
				JpaEntity obj = (JpaEntity) this.newInstance(element.getOnClass());
				try {
					element.getOnField().set(obj, rs.getObject(index) );
					return repository.findBy(obj);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					throw new ConvertRunException(e);      
				}  
			}
		}
		for (ManyOnManyEntity element : ormEntity.getAllManyOnMany()) {
			if(element.equals(propertyDescriptors.getName() ) ){
				List<String> query =queryOneList(element.getSelectOnColumSql(),new Object[]{ rs.getObject(index) });
				List<JpaEntity> list = new ArrayList<>(query.size());
				for (String id : query) {
					JpaEntity findOne = repository.findOne(element.getOnClass(), id);
					list.add(findOne);
				}
				return list;
			}
		}
		return null;
	}
	protected List<String> queryOneList(String sql , Object[] pap) throws SQLException{
		if (repository instanceof JpaRepositoryImp) {
			JpaRepositoryImp new_name = (JpaRepositoryImp) repository;
			QueryRunner queryRunner = new_name.getQueryRunner();
			// 查询关联的所有id
			return queryRunner.query(sql, pap , HANDLER);
		}
		return null;
	}
	/**通过index反推获得反省的内容。
	 * @param index
	 * @return
	 */
	protected PropertyDescriptor[] getPropertyDescriptors()
			{
		try {
			return ((PropertyDescriptor[]) MethodUtils.invokeMethod(this, true, "propertyDescriptors", new Object[]{cla}));
		} catch (NoSuchMethodException | IllegalAccessException
				| InvocationTargetException e) {
			throw new ConvertRunException(e);
		}
	}
	

		
//		if (rs.next()) {

//		}
//		return bean;



}

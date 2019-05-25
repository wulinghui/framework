package com.wlh.jpa.entity;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.wlh.util.ClassUtils;
import com.wlh.util.FieldUtils;
import com.wlh.util.TypeResolvable;

public class ORMEntity {
	List<PrimitiveEntity> primitiveEntityList = new ArrayList<>();
	List<OneOnOneEntity> oneOnOneEntityList = new ArrayList<>();
	List<OneOnManyEntity> oneOnManyEntityList = new ArrayList<>();
	List<ManyOnManyEntity> manyOnManyEntityList = new ArrayList<>();
	
	public ORMEntity(Class<?> cla){
		init(cla);
	}
	protected void init(Class<?> cla) {  
		List<Field> allFieldsList = FieldUtils.getAllFieldsList(cla);
		TypeResolvable resolvable;
		for (Field field : allFieldsList) {
			resolvable = TypeResolvable.forField(field);
			Class<?> type2 = resolvable.toClass();
			String fieldName = "id"; // 外键对应表的名称,默认是1on1的id
			// Primitive 
			if (isPrimitive(type2)) {
				primitiveEntityList.add( new PrimitiveEntity(field) );
			// 级联
			} else {
				// 一对多 class [ id List<User> name ] < List<User> -- pid
				// > User [ id pid U_name ]
				// 多对多 User [ id List<Course> U_name ] < List<Course> --
				// List<User> > Course [ id List<User> C_name ]
				if (resolvable.hasGenerics()) {
					fieldName = field.getName();
					resolvable = resolvable.getGeneric(0);
					type2 = resolvable.toClass(); // User类
					// 确定一对多还是多对多。
					// 获取对应的类的Field , 我们这里默认的是2个对应的属性是同名的。 
					boolean hasGenerics = isManyOnMany(type2, fieldName);
					// 该Field有泛型。就是多对多了
					if ( hasGenerics ) {
						manyOnManyEntityList.add( new ManyOnManyEntity(field , FieldUtils.getField(type2, fieldName, true) ) );
						// 一对多  2表对应。
					} else {
						oneOnManyEntityList.add( new OneOnManyEntity(field ,  FieldUtils.getField(type2, fieldName, true))   ) ;
					} 
					// 一对一 user [ id name Info ] < Info -- id > Info [ id name ]
				} else {
					// 没有泛型的就是一对一    id
					oneOnOneEntityList.add( new OneOnOneEntity(field, FieldUtils.getField(type2, fieldName, true) ) ) ;
				}
			}
			field.setAccessible(true);
		}
	
	}
	protected boolean isPrimitive(Class<?> type2) {
		return type2.isPrimitive()
				|| (type2.getName().startsWith("java.") && !ClassUtils.isAssignable(type2, Collection.class) );
	}
	protected boolean isManyOnMany(Class<?> type2, String fieldName) {
		TypeResolvable noField = TypeResolvable
				.forField(FieldUtils.getField(type2,
						fieldName, true));
		boolean hasGenerics = noField.hasGenerics();
		return hasGenerics;
	}
	/**
	 * 获得所有普通的列(属性)，封装了一些必要的内容。
	 * @param cla
	 */
	public List<PrimitiveEntity> getAllPrimitive() {
		return primitiveEntityList;
	}
	/**
	 * @param cla
	 */
	public List<OneOnOneEntity> getAllOneOnOne() {
		return oneOnOneEntityList;
	}
	public List<OneOnManyEntity> getAllOneOnMany() {
		return oneOnManyEntityList;
	}
	public List<ManyOnManyEntity> getAllManyOnMany() {
		return manyOnManyEntityList;
	}
	
	public List<PrimitiveEntity> getAll(){
		List<PrimitiveEntity> primitiveList = new ArrayList<>();
		primitiveList.addAll(primitiveEntityList);
		primitiveList.addAll(oneOnOneEntityList);
		primitiveList.addAll(oneOnManyEntityList);
		primitiveList.addAll(manyOnManyEntityList);
		return primitiveList;
	}
	/**获得除了多对多的，所有。
	 * @return
	 */
	public List<PrimitiveEntity> getAllExecptManyOnMany(){
		List<PrimitiveEntity> primitiveList = new ArrayList<>();
		primitiveList.addAll(primitiveEntityList);
		primitiveList.addAll(oneOnOneEntityList);
		primitiveList.addAll(oneOnManyEntityList);
		return primitiveList;
	}
}

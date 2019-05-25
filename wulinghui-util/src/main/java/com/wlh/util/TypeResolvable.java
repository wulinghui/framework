package com.wlh.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Map;

import org.springframework.core.ResolvableType;

/**
 * @author wulinghui
 * 主要用于适配spring的ResolvableType。
 * 话说这个类还是很厉害的。
 * 同时提供给其它用户适配，和扩展。
 * 我们这里扩展一个forString(String inner){方法。
 */
public class TypeResolvable {
	protected ResolvableType type;
	public TypeResolvable(ResolvableType type) {
		super();
		this.type = type;
	}
	public TypeResolvable() {
		super();
	}

	public Type getType() {
		return  type.getType() ;
	}

	public Class<?> getRawClass() {
		return type.getRawClass();
	}

	public Object getSource() {
		return type.getSource();
	}

	public Class<?> toClass() {
		return type.toClass() ;
	}

	public boolean isInstance(Object obj) {
		return type.isInstance(obj);
	}

	public boolean isAssignableFrom(Class<?> other) {
		return type.isAssignableFrom(other);
	}

	public boolean isAssignableFrom(TypeResolvable other) {
		return type.isAssignableFrom(other.type);
	}

	public boolean isArray() {
		return type.isArray();
	}

	public TypeResolvable getComponentType() {
		return new TypeResolvable(type.getComponentType());
	}

	public TypeResolvable asCollection() {
		return new TypeResolvable(type.asCollection());
	}

	public TypeResolvable asMap() {
		return new TypeResolvable(type.asMap());
	}

	public TypeResolvable as(Class<?> type) {
		return new TypeResolvable(this.type.as(type));
	}

	public TypeResolvable getSuperType() {
		return new TypeResolvable(type.getSuperType());
	}

	public TypeResolvable[] getInterfaces() {
		return toArray(type.getInterfaces());
	}

	public boolean hasGenerics() {
		return type.hasGenerics();
	}

	public boolean hasUnresolvableGenerics() {
		return type.hasUnresolvableGenerics();
	}

	public TypeResolvable getNested(int nestingLevel) {
		return new TypeResolvable(type.getNested(nestingLevel));
	}

	public TypeResolvable getNested(int nestingLevel,
			Map<Integer, Integer> typeIndexesPerLevel) {
		return new TypeResolvable(type.getNested(nestingLevel,
				typeIndexesPerLevel));
	}

	public TypeResolvable getGeneric(int... indexes) {
		return new TypeResolvable(type.getGeneric(indexes));
	}

	public TypeResolvable[] getGenerics() {
		return toArray(type.getGenerics());
	}

	public Class<?>[] resolveGenerics() {
		return type.resolveGenerics();
	}

	public Class<?>[] resolveGenerics(Class<?> fallback) {
		return type.resolveGenerics(fallback);
	}

	public Class<?> resolveGeneric(int... indexes) {
		return type.resolveGeneric(indexes);
	}

	public Class<?> resolve() {
		return type.resolve();
	}

	public Class<?> resolve(Class<?> fallback) {
		return type.resolve(fallback);
	}

	public boolean equals(Object other) {
		return type.equals(other);
	}

	public int hashCode() {
		return type.hashCode();
	}

	public String toString() {
		return type.toString();
	}

	protected TypeResolvable[] toArray(ResolvableType[] resolvables) {
		if (resolvables == null)
			return null;
		TypeResolvable[] types = new TypeResolvable[resolvables.length];
		for (int i = 0; i < types.length; i++) {
			types[i] = new TypeResolvable(resolvables[i]);
		}
		return types;
	}

	protected static ResolvableType[] toTypeArray(TypeResolvable[] resolvables) {
		if (resolvables == null)
			return null;
		ResolvableType[] types = new ResolvableType[resolvables.length];
		for (int i = 0; i < types.length; i++) {
			types[i] = resolvables[i].type;
		}
		return types;
	}

	public static TypeResolvable forClass(Class<?> clazz) {
		return new TypeResolvable(ResolvableType.forClass(clazz));
	}

	/**
	 * Return a {@link TypeResolvable} for the specified {@link Class}, doing
	 * assignability checks against the raw class only (analogous to
	 * {@link Class#isAssignableFrom}, which this serves as a wrapper for. For
	 * example: {@code TypeResolvable.forRawClass(List.class)}.
	 * 
	 * @param clazz
	 *            the class to introspect ({@code null} is semantically
	 *            equivalent to {@code Object.class} for typical use cases here}
	 * @return a {@link TypeResolvable} for the specified class
	 * @since 4.2
	 * @see #forClass(Class)
	 * @see #getRawClass()
	 */
	public static TypeResolvable forRawClass(Class<?> clazz) {
		return new TypeResolvable(ResolvableType.forRawClass(clazz));
	}

	/**
	 * Return a {@link TypeResolvable} for the specified base type (interface or
	 * base class) with a given implementation class. For example:
	 * {@code TypeResolvable.forClass(List.class, MyArrayList.class)}.
	 * 
	 * @param baseType
	 *            the base type (must not be {@code null})
	 * @param implementationClass
	 *            the implementation class
	 * @return a {@link TypeResolvable} for the specified base type backed by
	 *         the given implementation class
	 * @see #forClass(Class)
	 * @see #forClassWithGenerics(Class, Class...)
	 */
	public static TypeResolvable forClass(Class<?> baseType, Class<?> implementationClass) {

		return new TypeResolvable( ResolvableType.forClass(baseType, implementationClass) );
	}

	/**
	 * Return a {@link TypeResolvable} for the specified {@link Class} with
	 * pre-declared generics.
	 * 
	 * @param clazz
	 *            the class (or interface) to introspect
	 * @param generics
	 *            the generics of the class
	 * @return a {@link TypeResolvable} for the specific class and generics
	 * @see #forClassWithGenerics(Class, TypeResolvable...)
	 */
	public static TypeResolvable forClassWithGenerics(Class<?> clazz, Class<?>... generics) {
		
		return new TypeResolvable( ResolvableType.forClassWithGenerics(clazz,   generics) );

	}

	/**
	 * Return a {@link TypeResolvable} for the specified {@link Class} with
	 * pre-declared generics.
	 * 
	 * @param clazz
	 *            the class (or interface) to introspect
	 * @param generics
	 *            the generics of the class
	 * @return a {@link TypeResolvable} for the specific class and generics
	 * @see #forClassWithGenerics(Class, Class...)
	 */
	public static TypeResolvable forClassWithGenerics(Class<?> clazz, TypeResolvable... generics) {
		return new TypeResolvable( ResolvableType.forClassWithGenerics(clazz, toTypeArray(generics )) );
	}

	/**
	 * Return a {@link TypeResolvable} for the specified instance. The instance
	 * does not convey generic information but if it implements
	 * {@link TypeResolvableProvider} a more precise {@link TypeResolvable} can
	 * be used than the simple one based on the {@link #forClass(Class) Class
	 * instance}.
	 * 
	 * @param instance
	 *            the instance
	 * @return a {@link TypeResolvable} for the specified instance
	 * @since 4.2
	 * @see TypeResolvableProvider
	 */
	public static TypeResolvable forInstance(Object instance) {
		return new TypeResolvable( ResolvableType.forInstance(instance) );

	}

	/**
	 * Return a {@link TypeResolvable} for the specified {@link Field}.
	 * 
	 * @param field
	 *            the source field
	 * @return a {@link TypeResolvable} for the specified field
	 * @see #forField(Field, Class)
	 */
	public static TypeResolvable forField(Field field) {
		return new TypeResolvable( ResolvableType.forField(field) );
	}

	/**
	 * Return a {@link TypeResolvable} for the specified {@link Field} with a
	 * given implementation.
	 * <p>
	 * Use this variant when the class that declares the field includes generic
	 * parameter variables that are satisfied by the implementation class.
	 * 
	 * @param field
	 *            the source field
	 * @param implementationClass
	 *            the implementation class
	 * @return a {@link TypeResolvable} for the specified field
	 * @see #forField(Field)
	 */
	public static TypeResolvable forField(Field field, Class<?> implementationClass) {
		return new TypeResolvable( ResolvableType.forField(field, implementationClass));
	}

	/**
	 * Return a {@link TypeResolvable} for the specified {@link Field} with a
	 * given implementation.
	 * <p>
	 * Use this variant when the class that declares the field includes generic
	 * parameter variables that are satisfied by the implementation type.
	 * 
	 * @param field
	 *            the source field
	 * @param implementationType
	 *            the implementation type
	 * @return a {@link TypeResolvable} for the specified field
	 * @see #forField(Field)
	 */
	public static TypeResolvable forField(Field field,  TypeResolvable implementationType) {
		return new TypeResolvable( ResolvableType.forField(field, implementationType.type));
	}

	/**
	 * Return a {@link TypeResolvable} for the specified {@link Field} with the
	 * given nesting level.
	 * 
	 * @param field
	 *            the source field
	 * @param nestingLevel
	 *            the nesting level (1 for the outer level; 2 for a nested
	 *            generic type; etc)
	 * @see #forField(Field)
	 */
	public static TypeResolvable forField(Field field, int nestingLevel) {
		return new TypeResolvable( ResolvableType.forField(field, nestingLevel));
	}

	/**
	 * Return a {@link TypeResolvable} for the specified {@link Field} with a
	 * given implementation and the given nesting level.
	 * <p>
	 * Use this variant when the class that declares the field includes generic
	 * parameter variables that are satisfied by the implementation class.
	 * 
	 * @param field
	 *            the source field
	 * @param nestingLevel
	 *            the nesting level (1 for the outer level; 2 for a nested
	 *            generic type; etc)
	 * @param implementationClass
	 *            the implementation class
	 * @return a {@link TypeResolvable} for the specified field
	 * @see #forField(Field)
	 */
	public static TypeResolvable forField(Field field, int nestingLevel,  Class<?> implementationClass) {
		return new TypeResolvable( ResolvableType.forField(field, nestingLevel, implementationClass));
	}

	/**
	 * Return a {@link TypeResolvable} for the specified {@link Constructor}
	 * parameter.
	 * 
	 * @param constructor
	 *            the source constructor (must not be {@code null})
	 * @param parameterIndex
	 *            the parameter index
	 * @return a {@link TypeResolvable} for the specified constructor parameter
	 * @see #forConstructorParameter(Constructor, int, Class)
	 */
	public static TypeResolvable forConstructorParameter(Constructor<?> constructor, int parameterIndex) {
		return new TypeResolvable(  ResolvableType.forConstructorParameter(constructor, parameterIndex));
	}

	/**
	 * Return a {@link TypeResolvable} for the specified {@link Constructor}
	 * parameter with a given implementation. Use this variant when the class
	 * that declares the constructor includes generic parameter variables that
	 * are satisfied by the implementation class.
	 * 
	 * @param constructor
	 *            the source constructor (must not be {@code null})
	 * @param parameterIndex
	 *            the parameter index
	 * @param implementationClass
	 *            the implementation class
	 * @return a {@link TypeResolvable} for the specified constructor parameter
	 * @see #forConstructorParameter(Constructor, int)
	 */
	public static TypeResolvable forConstructorParameter(Constructor<?> constructor, int parameterIndex,
			Class<?> implementationClass) {
		return new TypeResolvable( ResolvableType.forConstructorParameter(constructor, parameterIndex, implementationClass));
	}

	/**
	 * Return a {@link TypeResolvable} for the specified {@link Method} return
	 * type.
	 * 
	 * @param method
	 *            the source for the method return type
	 * @return a {@link TypeResolvable} for the specified method return
	 * @see #forMethodReturnType(Method, Class)
	 */
	public static TypeResolvable forMethodReturnType(Method method) {
		return new TypeResolvable( ResolvableType.forMethodReturnType(method));
	}

	/**
	 * Return a {@link TypeResolvable} for the specified {@link Method} return
	 * type. Use this variant when the class that declares the method includes
	 * generic parameter variables that are satisfied by the implementation
	 * class.
	 * 
	 * @param method
	 *            the source for the method return type
	 * @param implementationClass
	 *            the implementation class
	 * @return a {@link TypeResolvable} for the specified method return
	 * @see #forMethodReturnType(Method)
	 */
	public static TypeResolvable forMethodReturnType(Method method, Class<?> implementationClass) {
		return new TypeResolvable( ResolvableType.forMethodReturnType(method, implementationClass));
	}

	/**
	 * Return a {@link TypeResolvable} for the specified {@link Method}
	 * parameter.
	 * 
	 * @param method
	 *            the source method (must not be {@code null})
	 * @param parameterIndex
	 *            the parameter index
	 * @return a {@link TypeResolvable} for the specified method parameter
	 * @see #forMethodParameter(Method, int, Class)
	 * @see #forMethodParameter(MethodParameter)
	 */
	public static TypeResolvable forMethodParameter(Method method, int parameterIndex) {
		return new TypeResolvable(  ResolvableType.forMethodParameter(method, parameterIndex));
	}

	/**
	 * Return a {@link TypeResolvable} for the specified {@link Method}
	 * parameter with a given implementation. Use this variant when the class
	 * that declares the method includes generic parameter variables that are
	 * satisfied by the implementation class.
	 * 
	 * @param method
	 *            the source method (must not be {@code null})
	 * @param parameterIndex
	 *            the parameter index
	 * @param implementationClass
	 *            the implementation class
	 * @return a {@link TypeResolvable} for the specified method parameter
	 * @see #forMethodParameter(Method, int, Class)
	 * @see #forMethodParameter(MethodParameter)
	 */
	public static TypeResolvable forMethodParameter(Method method, int parameterIndex, Class<?> implementationClass) {
		return new TypeResolvable( ResolvableType.forMethodParameter(method, parameterIndex, implementationClass));
	}

	/**
	 * Return a {@link TypeResolvable} as a array of the specified
	 * {@code componentType}.
	 * 
	 * @param componentType
	 *            the component type
	 * @return a {@link TypeResolvable} as an array of the specified component
	 *         type
	 */
	public static TypeResolvable forArrayComponent(TypeResolvable componentType) {
		return new TypeResolvable( ResolvableType.forArrayComponent(componentType.type));
	}

	/**
	 * Return a {@link TypeResolvable} for the specified {@link Type}.
	 * <p>
	 * Note: The resulting {@link TypeResolvable} instance may not be
	 * {@link Serializable}.
	 * 
	 * @param type
	 *            the source type (potentially {@code null})
	 * @return a {@link TypeResolvable} for the specified {@link Type}
	 * @see #forType(Type, TypeResolvable)
	 */
	public static TypeResolvable forType( Type type) {
		return new TypeResolvable( ResolvableType.forType(type) );
	}

	/**
	 * Return a {@link TypeResolvable} for the specified {@link Type} backed by
	 * the given owner type.
	 * <p>
	 * Note: The resulting {@link TypeResolvable} instance may not be
	 * {@link Serializable}.
	 * 
	 * @param type
	 *            the source type or {@code null}
	 * @param owner
	 *            the owner type used to resolve variables
	 * @return a {@link TypeResolvable} for the specified {@link Type} and owner
	 * @see #forType(Type)
	 */
	public static TypeResolvable forType( Type type,  TypeResolvable owner) {
		return new TypeResolvable( ResolvableType.forType(type, owner.type) );
	}
}

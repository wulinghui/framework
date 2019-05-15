package com.wlh.ioc;

import java.util.Collection;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.configuration2.beanutils.BeanCreationContext;

import com.wlh.ioc.apache.BeanDeclaration;
import com.wlh.ioc.apache.BeanFactory;

/**
 * @author wulinghui
 *	 * 如果所有子类都用 getMaxLevel(bcc, this);这个方法的话。
	 * 那么最后一个put进去的类就是实现类。
 */
public abstract class AbstractBeanFactory implements BeanFactory{
	protected Class thisClass = this.getClass();	

	@Override
	public final Object createBean(BeanCreationContext bcc) throws Exception {
		org.apache.commons.configuration2.beanutils.BeanDeclaration beanDeclaration = bcc.getBeanDeclaration();
		
		return createBean0(bcc, (BeanDeclaration)beanDeclaration,(BeanEntity) beanDeclaration.getBeanFactoryParameter());
	}
	public abstract Object createBean0(BeanCreationContext bcc,BeanDeclaration beandel , BeanEntity beanFactoryParameter) throws Exception;
	
	public final String getScopeFlag(BeanDeclaration beanDeclaration) throws RuntimeException{
		return getScopeFlag0(beanDeclaration,(BeanEntity) beanDeclaration.getBeanFactoryParameter());
	}
	
	public abstract String getScopeFlag0(BeanDeclaration beandel , BeanEntity beanFactoryParameter) throws RuntimeException ;
	@Override
	public int getLevel(BeanDeclaration bcc) {
//		Collection<BeanFactory> allFactory = getAllFactoryNotMyself(this,bcc);
//		int max = getMaxLevel(bcc, allFactory);
		int max = getMaxLevel(bcc, this);
		return max + 1;
	}

	/**这个方法有个问题。
	 * 如果所有子类都用这个方法的话。
	 * 那么第一个put进去的类就是实现类。
	 * @param bcc
	 * @param allFactory
	 * @return
	 */
	public static final int getMaxLevel(BeanDeclaration bcc,
			Collection<BeanFactory> allFactory) {
		int max = -1;
		int level2 = -1;
		for (BeanFactory beanFactory : allFactory) {
			level2 = beanFactory .getLevel(bcc);
			max =  max < level2 ? level2 : max;
		}
		return max;
	}
	/**这个是返回factory所在map的位置。
	 * 如果所有子类都用这个方法的话。
	 * 那么最后一个put进去的类就是实现类。
	 * @param bcc
	 * @param factory
	 * @return
	 */
	public static final int getMaxLevel(BeanDeclaration bcc,
			BeanFactory factory) {
		Set<Entry<String, BeanFactory>> entrySet = bcc.getAllFactory().entrySet();
		int i = 0;
		for (Entry<String, BeanFactory> entry : entrySet) {
			if(factory.equals(entry.getValue()) ) break;
			i++;
		}
		return i;
	}
	

	public static final Collection<BeanFactory> getAllFactoryNotMyself(BeanFactory factory , BeanDeclaration bcc) {
		Collection<BeanFactory> allFactory = bcc.getAllFactory().values();
		allFactory.remove(factory);
		return allFactory;
	}
	@Override
	public Class<?> getDefaultBeanClass() {
		return null;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((thisClass == null) ? 0 : thisClass.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractBeanFactory other = (AbstractBeanFactory) obj;
		if (thisClass == null) {
			if (other.thisClass != null)
				return false;
		} else if (!thisClass.equals(other.thisClass))
			return false;
		return true;
	}
}

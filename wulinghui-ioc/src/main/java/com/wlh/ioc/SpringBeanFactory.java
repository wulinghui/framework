package com.wlh.ioc;

import java.util.Objects;

import org.apache.commons.configuration2.beanutils.BeanCreationContext;
import org.springframework.context.ApplicationContext;

import com.wlh.ioc.apache.BeanDeclaration;

/**
 * @author wulinghui
 * test com.wlh.ioc.SpringIocTest.main1()
 */
public class SpringBeanFactory extends AbstractBeanFactory {
	ApplicationContext context;
	
	public SpringBeanFactory(ApplicationContext context) {
		this.context = context;
	}
	
	@Override
	public int getLevel(BeanDeclaration bcc) {
		String beanClassName = bcc.getBeanClassName();
		BeanEntity beanFactoryParameter = (BeanEntity) bcc.getBeanFactoryParameter();
		if( Objects.isNull(beanClassName)){
			if (bcc instanceof BeanDeclarationImp) {
				BeanDeclarationImp new_name = (BeanDeclarationImp) bcc;
				Class<?> type = context.getType(beanFactoryParameter.getName());
				new_name.setBeanClassName(type);
			}
		}
		return super.getLevel(bcc);
	}

	@Override
	public Object createBean0(BeanCreationContext bcc,
			BeanDeclaration beandel, BeanEntity beanFactoryParameter)
			throws Exception {
		Class<?> beanClass = beanFactoryParameter.getTypeToMatch();
		String beanName = beanFactoryParameter.getName();
		Object[] parameter = beanFactoryParameter.getArgs();
		if(beanClass !=null && beanName == null && parameter == null){
			return context.getBean(beanClass);
		}else if(beanClass ==null && beanName != null && parameter == null ){
			return context.getBean(beanName);
		}else if(beanClass !=null && beanName == null && parameter != null ){
			return context.getBean(beanClass, parameter);
		}else if(beanClass !=null && beanName != null && parameter == null ){
			return context.getBean(beanName, beanClass);
		}else if(beanClass ==null && beanName != null && parameter != null ){
			return context.getBean(beanName, parameter);
		}else {
			return null;
		}
	}
	@Override
	public String getScopeFlag0(BeanDeclaration beandel,
			BeanEntity beanFactoryParameter) throws RuntimeException {
		if( context.isSingleton(beanFactoryParameter.getName()) ) {
			return beandel.SESSION;
		}else if( context.isPrototype(beanFactoryParameter.getName()) ){
			return beandel.PROTOTYPE;
		}else{
			return "";
		}
	}
}

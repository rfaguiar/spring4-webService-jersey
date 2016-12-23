package test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.support.AbstractXmlApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.SessionFactoryUtils;
import org.springframework.orm.hibernate4.SessionHolder;
import org.springframework.transaction.support.TransactionSynchronizationManager;

public class SpringUtil {

	private static SpringUtil instance;
	protected AbstractXmlApplicationContext ctx;
	
	//hibernate
	protected HibernateTransactionManager txManager;
	private Session session;
	
	private SpringUtil() {
		try {
			ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		} catch (BeansException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public static SpringUtil getInstance(){
		if(instance == null){
			instance = new SpringUtil();
		}
		return instance;
	}
	
	@SuppressWarnings("rawtypes")
	public Object getBean(Class c){
		if(ctx == null){
			return null;
		}
		if(session == null){
			openSession();
		}
		String[] beansNamesForType = ctx.getBeanNamesForType(c);
		if(beansNamesForType == null || beansNamesForType.length == 0){
			return null;
		}
		String name = beansNamesForType[0];
		Object bean = getBean(name);
		return bean;
	}
	
	public Object getBean(String name){
		if(ctx == null){
			return null;
		}
		if(session == null){
			openSession();
		}
		Object bean = ctx.getBean(name);
		return bean;
	}
	
	public Session openSession(){
		if(ctx != null){
			txManager = (HibernateTransactionManager) ctx.getBean("transactionManager");
			SessionFactory sessionFactory = txManager.getSessionFactory();
			session = sessionFactory.openSession();
			TransactionSynchronizationManager.bindResource(sessionFactory, new SessionHolder(session));
		}
		return session;
	}
	
	public void closeSession(){
		if(ctx !=null && txManager != null){
			SessionFactory sessionFactory = txManager.getSessionFactory();
			TransactionSynchronizationManager.unbindResource(sessionFactory);
			SessionFactoryUtils.closeSession(session);
			session = null;
		}
	}
	
	public Session getSession(){
		return session;
	}
	
	public SessionFactory getSessionFactory(){
		SessionFactory sf = txManager.getSessionFactory();
		return sf;
	}
}

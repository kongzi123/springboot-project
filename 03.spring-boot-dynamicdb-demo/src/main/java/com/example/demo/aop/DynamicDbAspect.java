package com.example.demo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;

import com.example.demo.common.dynamicdb.DynamicDbContextHolder;
import com.example.demo.common.dynamicdb.annotation.Master;
import com.example.demo.common.dynamicdb.annotation.Slave;
import com.example.demo.enums.DynamicDBType;

/**
 * 动态数据源切面类
 * @author Administrator
 *
 */
@Aspect
@Component
public class DynamicDbAspect implements PriorityOrdered {
	
	public static final Logger logger = LoggerFactory.getLogger(DynamicDbAspect.class);
	
	
	/**
	 * 切换到master主库
	 * @param proceedingJoinPoint
	 * @param Page
	 * @return
	 * @throws Throwable
	 */
	@Around("@annotation(master)")
	public Object proceed(ProceedingJoinPoint proceedingJoinPoint, Master master) throws Throwable {
		try {
			logger.debug("set database connection to master only");
			DynamicDbContextHolder.setDbType(DynamicDBType.MASTER);
		 	Object result = proceedingJoinPoint.proceed();
		 	return result;
		 } finally {
			 DynamicDbContextHolder.clearDbType();
			 logger.debug("restore master database connection");
		 }
	}
	
	/**
	 * 切换到slave从库
	 * @param proceedingJoinPoint
	 * @param Slave
	 * @return
	 * @throws Throwable
	 */
	@Around("@annotation(slave)")
	public Object proceed(ProceedingJoinPoint proceedingJoinPoint, Slave slave) throws Throwable {
		try {
			logger.debug("set database connection to slave only");
			DynamicDbContextHolder.setDbType(DynamicDBType.SLAVE);
		 	Object result = proceedingJoinPoint.proceed();
		 	return result;
		 } finally {
			 DynamicDbContextHolder.clearDbType();
			 logger.debug("restore slave database connection");
		 }
	}
	

	@Override
	public int getOrder() {
		return 1;
	}

}

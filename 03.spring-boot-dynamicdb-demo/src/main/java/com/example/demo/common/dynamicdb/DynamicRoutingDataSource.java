package com.example.demo.common.dynamicdb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 动态数据源路由
 * @author Administrator
 *
 */
public class DynamicRoutingDataSource extends AbstractRoutingDataSource {

	public static final Logger logger = LoggerFactory.getLogger(DynamicRoutingDataSource.class);
	
	/**
	 * 设置动态数据源
	 */
    @Override
    protected Object determineCurrentLookupKey() {
    	logger.debug("Current DataSource is [{}]", DynamicDbContextHolder.getDbType());
        return DynamicDbContextHolder.getDbType();
    }
}
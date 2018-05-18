package com.example.demo.common.dynamicdb.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.example.demo.common.dynamicdb.DynamicDbContextHolder;
import com.example.demo.common.dynamicdb.DynamicRoutingDataSource;
import com.example.demo.enums.DynamicDBType;


@Configuration
@EnableTransactionManagement
public class DataSourceConfiguration {
	
	public static final Logger logger = LoggerFactory.getLogger(DataSourceConfiguration.class);
	
	@Value("${druid.type}")
	private Class<? extends DataSource> dataSourceType;

	/**
	 * 主数据库
	 * @return
	 */
	@Primary  //必须指定默认数据源
	@Bean(name = "masterDataSource")
	@ConfigurationProperties(prefix = "druid.demo.master")
	public DataSource masterDataSource() {
		return DataSourceBuilder.create().type(dataSourceType).build();
	}
	
	/**
	 * 从数据库（如有多个按这种方式增加）
	 * @return
	 */	
	@Bean(name = "slaveDataSource")
	@ConfigurationProperties(prefix = "druid.demo.slave")
	public DataSource slaveDataSource() {
		return DataSourceBuilder.create().type(dataSourceType).build();
	}
	
	@Bean(name = "dataSource")
    public AbstractRoutingDataSource dataSource() {
        DynamicRoutingDataSource proxy = new DynamicRoutingDataSource();
        
        Map<Object, Object> targetDataResources = new HashMap<Object, Object>();
        targetDataResources.put(DynamicDBType.MASTER, masterDataSource());
        targetDataResources.put(DynamicDBType.SLAVE, slaveDataSource());        
        
        //设置默认数据源是master，没有添加注解的都是默认数据源
        proxy.setDefaultTargetDataSource(masterDataSource());
        proxy.setTargetDataSources(targetDataResources);
        proxy.afterPropertiesSet();
        return proxy;
    }

	@Bean
    @ConfigurationProperties(prefix = "mybatis")
	@Primary	
    public SqlSessionFactoryBean sqlSessionFactoryBean() {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());
        return sqlSessionFactoryBean;
    }
	
	// 事务管理
	@Bean
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}
}

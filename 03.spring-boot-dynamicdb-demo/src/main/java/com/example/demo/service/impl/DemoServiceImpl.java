package com.example.demo.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.common.dynamicdb.DynamicDbContextHolder;
import com.example.demo.common.dynamicdb.annotation.Master;
import com.example.demo.common.dynamicdb.annotation.Slave;
import com.example.demo.common.redis.RedisClient;
import com.example.demo.constant.RedisKeyConstant;
import com.example.demo.enums.DynamicDBType;
import com.example.demo.mapper.DemoMapper;
import com.example.demo.model.Demo;
import com.example.demo.service.IDemoService;
import com.example.demo.util.StringUtil;

@Service
public class DemoServiceImpl implements IDemoService {
	
	private static final Logger logger = LoggerFactory.getLogger(DemoServiceImpl.class);

	@Autowired
	private DemoMapper demoMapper;
	
	@Autowired
	private RedisClient redisClient;
	
//	@Slave //如果缓存中存在，则查的不是数据库
	@Override
	public List<Demo> queryAllDemo() {
		List<Demo> listDemos = null;
		//查询缓存
		List<Demo> list = redisClient.lRange(RedisKeyConstant.ALL_DEMOS, 0, -1);
		if(StringUtil.isEmptyList(list)) {
			logger.debug("缓存中不存在demo信息");
			//手动切换数据源
			DynamicDbContextHolder.setDbType(DynamicDBType.SLAVE);
			listDemos = demoMapper.queryAllDemo();
			
			//缓存中不存在，则添加
			redisClient.lPush(RedisKeyConstant.ALL_DEMOS, listDemos);
		} else {			
			logger.debug("从缓存中获取demo信息");
			listDemos = (List<Demo>)list;
		}
		
		return listDemos;
	}

	@Master
	@Override
	public int addDemo(Demo demo) {		
		int addRow = demoMapper.insert(demo);		
		if(addRow > 0) {
			//新增成功，清除缓存
			redisClient.remove(RedisKeyConstant.ALL_DEMOS);
		}
		
		return addRow;
	}

}

package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.common.redis.RedisClient;
import com.example.demo.constant.RedisKeyConstant;
import com.example.demo.mapper.DemoMapper;
import com.example.demo.model.Demo;
import com.example.demo.service.IDemoService;
import com.example.demo.util.StringUtil;

@Service
public class DemoServiceImpl implements IDemoService {

	@Autowired
	private DemoMapper demoMapper;
	
	@Autowired
	private RedisClient redisClient;
	
	@Override
	public List<Demo> queryAllDemo() {
		List<Demo> listDemos = null;
		//查询缓存
		List<Demo> list = redisClient.lRange(RedisKeyConstant.ALL_DEMOS, 0, -1);
		if(StringUtil.isEmptyList(list)) {
			listDemos = demoMapper.queryAllDemo();
			
			//缓存中不存在，则添加
			redisClient.lPush(RedisKeyConstant.ALL_DEMOS, listDemos);
		} else {			
			listDemos = (List<Demo>)list;
		}
		
		return listDemos;
	}

}

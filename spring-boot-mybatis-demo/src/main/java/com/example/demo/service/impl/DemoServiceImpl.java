package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.DemoMapper;
import com.example.demo.model.Demo;
import com.example.demo.service.IDemoService;

@Service
public class DemoServiceImpl implements IDemoService {

	@Autowired
	private DemoMapper demoMapper;
	
	@Override
	public List<Demo> queryAllDemo() {
		
		return demoMapper.queryAllDemo();
	}

}

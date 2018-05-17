package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Demo;
import com.example.demo.service.IDemoService;

@RestController
@RequestMapping("/demo/v1")
public class DemoController {
	
	@Autowired
	private IDemoService demoServiceImpl;
	
	@RequestMapping(value = "/demos", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<List<Demo>> queryAll() {
		
		List<Demo> list = demoServiceImpl.queryAllDemo();
		return ResponseEntity.ok(list);
	}
	
}

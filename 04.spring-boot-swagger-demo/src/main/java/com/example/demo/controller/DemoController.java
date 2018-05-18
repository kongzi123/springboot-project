package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Demo;
import com.example.demo.service.IDemoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/demo/v1")
@Api(value="DEMO接口", tags="DEMO接口")
public class DemoController {
	
	@Autowired
	private IDemoService demoServiceImpl;
	
	@ApiOperation(value = "查询DEMO信息", notes = "查询DEMO信息")
	@ApiResponses({ @ApiResponse(code = 200, message = "操作成功！"), 
			@ApiResponse(code = 501, message = "操作失败，SDK接入调用失败！"),
			@ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对！") })
	@RequestMapping(value = "/demos", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<List<Demo>> queryAll() {
		
		List<Demo> list = demoServiceImpl.queryAllDemo();
		return ResponseEntity.ok(list);
	}
	
	@ApiOperation(value = "新增DEMO信息", notes = "新增DEMO信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "demo", value = "DEMO信息", paramType = "body", dataType = "Demo", required=true) })
	@ApiResponses({ @ApiResponse(code = 200, message = "操作成功！"), 
			@ApiResponse(code = 501, message = "操作失败，SDK接入调用失败！"),
			@ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对！") })
	@RequestMapping(value = "/demo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<Integer> addDemo(@RequestBody Demo demo) {
		
		int addRow = demoServiceImpl.addDemo(demo);
		return ResponseEntity.ok(addRow);
	}
	
}

package org.sjq.test.controller;

import java.util.List;

import org.sjq.test.entities.Dept;
import org.sjq.test.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class DeptController {

	@Autowired
	private DeptService deptService;
	
	@RequestMapping(value="/dept/add",method=RequestMethod.POST)
	public boolean add(@RequestBody Dept dept) {
		return deptService.add(dept);
	}
	
	@RequestMapping(value="/dept/get/{id}",method=RequestMethod.GET)
	//一旦调用服务方法失败并抛出错误信息后，会自动调用@HystrixCommand标注好的fallbackMethod方法
	@HystrixCommand(fallbackMethod="processHystrix_Get")
	public Dept get(@PathVariable("id") Long id) {
		Dept dept =deptService.get(id);
		if(null==dept) {//为空时认为的抛出异常
			throw new RuntimeException("该ID："+id+"没有对应的信息");
		}
		return dept;
	}
	
	//熔断器处理方法-快速返回错误响应
	public Dept processHystrix_Get(@PathVariable("id") Long id) {
		return new Dept().setDeptNo(id).setDName("该ID："+id+"没有对应的信息-HystrixCommand处理")
				.setDb_source("no this database in mysql ");
	}
	
	@RequestMapping(value="/dept/list",method=RequestMethod.GET)
	public List<Dept> list() {
		return deptService.list();
	}
	
	@Autowired
	private DiscoveryClient client;
	
	@RequestMapping(value="/dept/discovery",method=RequestMethod.GET)
	public Object discovery() {
		List<String> list=client.getServices();
		
		System.out.println("注册中心的服务列表"+list);
		
		//获取注册中心的服务实例
		List<ServiceInstance> serviceInstanceList=client.getInstances("MICROSERVICECLOUD-DEPT");
		
		for(ServiceInstance instance:serviceInstanceList) {
			System.out.println(instance.getServiceId()+"\t"+
					instance.getHost()+"\t"+
					instance.getPort()+"\t"+
					instance.getUri());
		}
		
		return this.client;
	}
}

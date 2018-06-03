package org.sjq.test.controller;

import java.util.List;

import javax.annotation.Resource;

import org.sjq.test.entities.Dept;
import org.sjq.test.service.DeptClientService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeptController_Consumer {

	/*private static final String REST_URL_PREFIX="http://MICROSERVICECLOUD-DEPT";*/
	
	/*@Autowired
	private RestTemplate restTemplate;*/
	
	@Resource
	private DeptClientService deptClientService;
	
	@RequestMapping("/consumer/dept/add")
	public boolean add(Dept dept) {
		//return restTemplate.postForObject(REST_URL_PREFIX+"/dept/add", dept, Boolean.class);
		return deptClientService.add(dept);
	}
	
	@RequestMapping("/consumer/dept/get/{id}")
	public Dept get(@PathVariable("id")Long id) {
		//return restTemplate.getForObject(REST_URL_PREFIX+"/dept/get/"+id, Dept.class);
		return deptClientService.get(id);
	}
	
	@RequestMapping("/consumer/dept/list")
	public List<Dept> list() {
		//return restTemplate.getForObject(REST_URL_PREFIX+"/dept/list", List.class);
		return deptClientService.list();
	}
}

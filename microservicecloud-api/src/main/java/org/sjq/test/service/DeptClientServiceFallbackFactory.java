package org.sjq.test.service;

import java.util.List;

import org.sjq.test.entities.Dept;
import org.springframework.stereotype.Component;

import feign.hystrix.FallbackFactory;

@Component//不要忘记添加注解
public class DeptClientServiceFallbackFactory implements FallbackFactory<DeptClientService>{

	@Override
	public DeptClientService create(Throwable arg0) {
		return new DeptClientService() {
			
			//这里只处理get方法，处理由其他客户端调用已经被关掉的服务方法的处理方法
			@Override
			public Dept get(Long id) {
				return new Dept().setDeptNo(id).setDName("该ID："+id+"没有对应的信息，consumer客户端提供了服务降级信息，此刻服务不可用")
						.setDb_source("data not in mysql");
			}
			
			@Override
			public List<Dept> list() {
				return null;
			}
			
			@Override
			public boolean add(Dept dept) {
				return false;
			}
		};
	}

}

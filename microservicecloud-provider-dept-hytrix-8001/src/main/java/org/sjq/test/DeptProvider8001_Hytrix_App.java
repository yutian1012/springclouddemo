package org.sjq.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient //服务启动后自动注册到eureka服务中心
@EnableDiscoveryClient //启动服务发现功能
@EnableCircuitBreaker//启动hytrix熔断机制的支持
public class DeptProvider8001_Hytrix_App {
	public static void main(String[] args) {
		SpringApplication.run(DeptProvider8001_Hytrix_App.class, args);
	}
}

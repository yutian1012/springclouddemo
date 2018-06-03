package org.sjq.test;

import org.sjq.rule.MySelfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication
@EnableEurekaClient
//name指定需要新负载均衡规则的服务名，configuration指定一个configuration类，配置类中返回自定义的规则
//要避免MySelfRule类所在的路径被主启动类的ComponentScan扫描到，这样所有的微服务都会使用该规则
@RibbonClient(name="MICROSERVICECLOUD-DEPT",configuration=MySelfRule.class)
public class DeptConsumer80_App {
	public static void main(String[] args) {
		SpringApplication.run(DeptConsumer80_App.class, args);
	}
}

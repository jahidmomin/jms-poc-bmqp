package com.mspocs.jmspoc;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.activemq.ActiveMQAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.core.JmsTemplate;

import jakarta.jms.ConnectionFactory;

@SpringBootApplication
public class JmsPocApplication {

	@Value("${spring.activemq.broker-url}")
	private String brokerURL;

	@Value("${spring.activemq.user}")
	private String username;

	@Value("${spring.activemq.password}")
	private String password;

	public static void main(String[] args) {
		SpringApplication.run(JmsPocApplication.class, args);
	}

	@Bean
	ConnectionFactory conn() {
		ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
		activeMQConnectionFactory.setBrokerURL(brokerURL);
		activeMQConnectionFactory.setUserName(username);
		activeMQConnectionFactory.setPassword(password);
		return activeMQConnectionFactory;
	}

	@Bean
	JmsTemplate build() {
		return new JmsTemplate(conn());
	}
}

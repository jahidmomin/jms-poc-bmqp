package com.mspocs.jmspoc;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.jms.activemq.ActiveMQAutoConfiguration;
import org.springframework.boot.autoconfigure.jms.activemq.ActiveMQConnectionFactoryCustomizer;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class Producer implements CommandLineRunner {

    @Autowired
    JmsTemplate jmsTemplate;

    @Override
    public void run(String... args) throws Exception {

        // session created by spring container using auto config..
        // JMS is interface given by java sun microsysterm
        // active MQ is impl of JMS API
        // when we call jmstemplate.send() then it send to MOM (messge oriented mediator
        // activeMQ)
        // P2P or pub Sub
        // we are doing p2p - one prod one consumer
        // session
        // we will use jms interface only internally when spring found impl then he
        // execute
        // resp overrriden methods

        jmsTemplate.send("q1", session -> session.createTextMessage("Hello World"));
        jmsTemplate.convertAndSend("q2","Hello world 2");
        System.out.println("SEND");
    }

    @JmsListener(destination = "q1")
    public void consumer1(String s) {
        System.out.println("CONSUMER");
        System.out.println(s);
    }

    @JmsListener(destination = "q1")
    public void consumer2(String s) {
        System.out.println("CONSUMER 2");
        System.out.println(s);
    }

}

package com.practice;

import org.apache.camel.component.jms.JmsComponent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.JmsTransactionManager;

import javax.jms.ConnectionFactory;

@Configuration
public class JmsConfig {

    @Bean
    public JmsTransactionManager createJmsTransactionManager(final ConnectionFactory connectionFactory){
        JmsTransactionManager jmsTransactionManager = new JmsTransactionManager();
        jmsTransactionManager.setConnectionFactory(connectionFactory);
        return jmsTransactionManager;
    }

    @Bean
    public JmsComponent createJmsComponent(final ConnectionFactory connectionFactory, final JmsTransactionManager jmsTransactionManager){
        JmsComponent jmsComponent = JmsComponent.jmsComponentTransacted(connectionFactory, jmsTransactionManager);
        jmsComponent.setMaxConcurrentConsumers(10); /** to be made configurable later */
        return jmsComponent;
    }

}

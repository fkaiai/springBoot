/*
package cn.fk.te.rabbitMQ;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitConfig {

    @Bean(name="bgp_to_cmk_connectionFactory")
    public ConnectionFactory bgp_to_cmk_connectionFactory(
            @Value("${spring.bgp_to_cmk_rabbitmq.host}") String host,
            @Value("${spring.bgp_to_cmk_rabbitmq.port}") int port,
            @Value("${spring.bgp_to_cmk_rabbitmq.username}") String username,
            @Value("${spring.bgp_to_cmk_rabbitmq.password}") String password,
            @Value("${spring.bgp_to_cmk_rabbitmq.virtualHost}") String virtualHost
    ){
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(host);
        connectionFactory.setPort(port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost(virtualHost);
        return connectionFactory;
    }

    @Bean(name="bgp_to_cmk_rabbitTemplate")
    public RabbitTemplate bgp_to_cmk_rabbitTemplate(
            @Qualifier("bgp_to_cmk_connectionFactory") ConnectionFactory connectionFactory
    ){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        return rabbitTemplate;
    }

    @Bean(name="bgp_to_cmk_factory")
    public SimpleRabbitListenerContainerFactory bgp_to_cmk_factory(
            SimpleRabbitListenerContainerFactoryConfigurer configurer,
            @Qualifier("bgp_to_cmk_connectionFactory") ConnectionFactory connectionFactory
    ) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        return factory;
    }

    @Value("${spring.bgp_to_cmk_rabbitmq.queueName1}") String BGP_TO_CMK_QUEUE_queueName1;
    @Bean
    public Object bgp_to_cmk_queue_1() {
        return new Queue(BGP_TO_CMK_QUEUE_queueName1);
    }

    @Value("${spring.bgp_to_cmk_rabbitmq.queueName2}") String BGP_TO_CMK_QUEUE_queueName2;
    @Bean
    public Object bgp_to_cmk_queue_2() {
        return new Queue(BGP_TO_CMK_QUEUE_queueName2);
    }

    @Value("${spring.bgp_to_cmk_rabbitmq.queueName3}") String BGP_TO_CMK_QUEUE_queueName3;
    @Bean
    public Object bgp_to_cmk_queue_3() {
        return new Queue(BGP_TO_CMK_QUEUE_queueName3);
    }

}

*/

package com.hexiang.rabbitmq.exchanges.topic.send;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.nio.charset.StandardCharsets;

/**
 * 主题队列 -  消息生产者
 */
public class Send {

    //定义交换机名称
    private final static String EXCHANGE_NAME = "exchange_topic";

    public static void main(String[] argv) throws Exception {
        //创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.238.130");
        factory.setPort(5672);
        factory.setUsername("guest");
        factory.setPassword("guest");
        //创建连接
        try (Connection connection = factory.newConnection();
             //创建信道
             Channel channel = connection.createChannel()) {
            //声明交换机
            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
            String infoMessage = "普通信息!";
            String errorMessage = "错误信息!";
            String warningMessage = "警告信息!";
            //准备路由
            String infoRoutingKey = "info.message.orange";
            String errorRoutingKey = "error.rabbit.lazy";
            String warningRoutingKey = "orange.warning.message";

            /*
              参数说明：
              String exchange, String routingKey, boolean mandatory, BasicProperties props, byte[] body
              exchange：交换机名称
              routingKey：路由键
              mandatory：是否强制发送，如果为true，发送失败会抛出异常
              props：消息属性
              body：消息内容
             */
            //发送消息
            channel.basicPublish(EXCHANGE_NAME, infoRoutingKey, null, infoMessage.getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] sent '" + infoMessage + "'");
            channel.basicPublish(EXCHANGE_NAME, errorRoutingKey, null, errorMessage.getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] sent '" + errorMessage + "'");
            channel.basicPublish(EXCHANGE_NAME, warningRoutingKey, null, warningMessage.getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] sent '" + warningMessage + "'");

        }
    }
}

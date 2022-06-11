package com.hexiang.rabbitmq.exchanges.direct.receive;

import com.rabbitmq.client.*;

import java.nio.charset.StandardCharsets;

/*
    路由队列 - 消息消费者
 */
public class Recv02 {

    //定义队列名称
    private final static String EXCHANGE_NAME = "exchange_direct";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.238.130");
        factory.setPort(5672);
        factory.setUsername("guest");
        factory.setPassword("guest");
        //创建连接
        Connection connection = factory.newConnection();
        //创建通道
        Channel channel = connection.createChannel();
        //绑定交换机
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
        //获取队列（排他队列）
        String queueName = channel.queueDeclare().getQueue();
        //绑定队列到交换机
        String infoRoutingKey = "info";
        String errorRoutingKey = "error";
        String warningRoutingKey = "warning";
        channel.queueBind(queueName, EXCHANGE_NAME, infoRoutingKey);
        channel.queueBind(queueName, EXCHANGE_NAME, errorRoutingKey);
        channel.queueBind(queueName, EXCHANGE_NAME, warningRoutingKey);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
        /*
            消费消息
            1. 定义消费者
            2. 定义消费消息的回调函数
            3. 开始消费
         */
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {

            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            System.out.println(" [x] Received '" + message + "'");
        };
        //消费消息
        /*
         参数说明：
            1. 队列名称
            2. 是否自动确认
            3. 是否独占队列
            4. 是否阻塞
         */
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> { });
    }
}

package com.hexiang.rabbitmq.work.rr.receive;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.nio.charset.StandardCharsets;
/*
    工作队列 - 轮询 - 消息消费者
 */
public class Recv01 {

    //定义队列名称
    private final static String QUEUE_NAME = "work_rr";

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
        //声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
        /*
            消费消息
            1. 定义消费者
            2. 定义消费消息的回调函数
            3. 开始消费
         */
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            //模拟消费耗时
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            System.out.println(" [x] Received '" + message + "'");
            //手动确认
            /*
                参数说明：
                1. deliveryTag：消息的唯一标识
                2. multiple：是否批量确认  true：批量确认  false：单条确认

             */
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        };
        //消费消息
        /*
         参数说明：
            1. 队列名称
            2. 是否自动确认
            3. 是否独占队列
            4. 是否阻塞
         */
        channel.basicConsume(QUEUE_NAME, false, deliverCallback, consumerTag -> { });
    }
}

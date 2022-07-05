package com.hexiang.rabbitmq.work.rr.send;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.nio.charset.StandardCharsets;

/**
 * 工作队列 - 轮询 -  消息生产者
 */
public class Send {

    //定义队列名称
    private final static String QUEUE_NAME = "work_rr";

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
            //创建队列
            /*
              参数说明：
              String queue, boolean durable, boolean exclusive, boolean autoDelete, Map<String, Object> arguments
              queue：队列名称
              durable：是否持久化，默认true。持久化：消息队列存储在磁盘上，当服务器重启时，消息队列不会丢失 不持久化：消息队列存储在内存中，当服务器重启时，消息队列会丢失
              exclusive：是否排他队列 如果为true，表示只有自己能访问，其他人不能访问。如果为false，表示其他人可以访问，默认为false。排他队列只有一个消费者能访问其他人不能访问
              autoDelete：是否自动删除队列和消息，当所有消费者断开连接后，队列会自动删除和消息
              arguments：队列参数 参数名称：x-message-ttl，参数值：消息过期时间，单位：毫秒
             */
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            String message = "Hello World!";
            for (int i = 0; i < 20; i++) {
               message = message+" --- "+i;
                //发送消息
            /*
              参数说明：
              String exchange, String routingKey, boolean mandatory, BasicProperties props, byte[] body
              exchange：交换机名称
              routingKey：路由键
              mandatory：是否强制发送，如果为true，发送失败会抛出异常
              props：消息属性
              body：消息内容
             */
                channel.basicPublish("", QUEUE_NAME, null, message.getBytes(StandardCharsets.UTF_8));
                System.out.println(" [x] Sent '" + message + "'"+" --- "+i);
            }

        }
    }
}

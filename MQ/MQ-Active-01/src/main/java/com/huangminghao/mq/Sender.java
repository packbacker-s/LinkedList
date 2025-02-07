package com.huangminghao.mq;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @User:Mingaho
 * @Date:2021/11/24
 * @Time:16:30
 */


import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 消息发送
 */
public class Sender {

    //1、获取接连工厂
    public static void main(String[] args) throws Exception {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
                ActiveMQConnectionFactory.DEFAULT_USER,
                ActiveMQConnectionFactory.DEFAULT_PASSWORD,
                "tcp://localhost:61616"
        );


        //2、获取一个向ActiveMQ的连接
        Connection connection = connectionFactory.createConnection();

        //3、获取session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);//是否需要事务， 确认方式

        //4、找到目的地，获取destination，消费端，也会从则合格目的地取消息
        Queue queue = session.createQueue("user");

        //5.1消息创建者
        MessageProducer producer = session.createProducer(queue);
        //consumer ->消费者
        //producer ->创建者
        //5.2创建消息
        for (int i = 0; i < 100; i++) {
            TextMessage textMessage = session.createTextMessage("hi: " + i);
            //5.3向目的地写入消息
            producer.send(textMessage);
            Thread.sleep(1000);

        }

        //6、关闭连接
        connection.close();
        System.out.println("System is Out");


    }


}

package com.avseredyuk.activemq.pubsub;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTopic;

import javax.jms.*;

/**
 * Created by Anton_Serediuk on 4/25/2017.
 */
public class JMSProducerHelper {
    private Connection connection;
    private Session session;
    private Destination destination;
    private MessageProducer producer;

    public boolean create() {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://localhost:61616?trace=true");
        try {
            connection = factory.createConnection("toha", "qwerty");
            connection.start();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            destination = new ActiveMQTopic("topicname");
            producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

            return true;
        } catch (JMSException e) {
            //todo: log
            System.out.println("create exception");
            e.printStackTrace();
            return false;
        }
    }

    public void send(String text) {
        try {
            TextMessage message = session.createTextMessage(text);
            producer.send(message);
        } catch (JMSException e) {
            //todo: log
            System.out.println("send exception");
            e.printStackTrace();
        }

    }

    public void close() {
        try {
            connection.close();
        } catch (JMSException e) {
            //todo: log
            System.out.println("close exception");
            e.printStackTrace();
        }
    }


}

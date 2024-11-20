package com.example.jmsactivemqimpl.queue;

import jakarta.jms.*;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class MessageReceiver {
    //URL of the JMS server. DEFAULT_BROKER_URL will just mean that JMS server is on localhost
    //default broker URL is : tcp://localhost:61616"
    private static final String BROKER_URL= ActiveMQConnection.DEFAULT_BROKER_URL;
    //Queue Name.You can create any/many queue names as per your requirement.
    private static final String QUEUE_NAME="MON-QUEUE";
    public static void main(String[] args) throws JMSException{
        //Getting JMS connection from the JMS server and starting it
        ConnectionFactory factory= new ActiveMQConnectionFactory(BROKER_URL);
        Connection connection= factory.createConnection();
        connection.start();
        //Creating a non transactional session to send/receive JMS message.
        Session session= connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //The queue will be created automatically on the server.

        Destination destination= session.createQueue(QUEUE_NAME);
        //Destination represents here our queue 'MESSAGE_QUEUE' on the JMS server.
        // MessageProducer is used for sending messages to the queue
        MessageConsumer consumer= session.createConsumer(destination);

        Message message= consumer.receive();

        System.out.println("le message est bien recu "+ ((TextMessage)message).getText());
        connection.close();

    }
}

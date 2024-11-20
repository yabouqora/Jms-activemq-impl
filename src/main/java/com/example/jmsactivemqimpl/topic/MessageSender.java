package com.example.jmsactivemqimpl.topic;

import jakarta.jms.*;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class MessageSender {
    //URL of the JMS server. DEFAULT_BROKER_URL will just mean that JMS server is on localhost
    //default broker URL is : tcp://localhost:61616"
    private static final String BROKER_URL= ActiveMQConnection.DEFAULT_BROKER_URL;
    //Queue Name.You can create any/many queue names as per your requirement.
    private static final String TOPIC_NAME="Mon-topic";

    public static void main(String[] args) throws JMSException {

        //Getting JMS connection from the JMS server and starting it
        ConnectionFactory factory= new ActiveMQConnectionFactory(BROKER_URL);
        Connection connection= factory.createConnection();
        connection.start();
        //Creating a non transactional session to send/receive JMS message.
        Session session= connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //The queue will be created automatically on the server.
        
        Destination destination= session.createTopic(TOPIC_NAME);
        //Destination represents here our queue 'MESSAGE_QUEUE' on the JMS server.
        // MessageProducer is used for sending messages to the queue
        MessageProducer producer= session.createProducer(destination);

        Message message= session.createTextMessage("Bonjour !! c'est votre premier message envoyee au brokers topic mon-topic");
        // Here we are sending our message!

        producer.send(message);

        System.out.println("le message est bien envoye "+ ((TextMessage)message).getText());
        connection.close();


    }
}

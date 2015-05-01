package dcc.com.agent.message;

import dcc.com.agent.config.AgentProperties;

import java.net.InetAddress;


/**
 * Created by teo on 25/04/15.
 */
public class Receive {
    public static AgentProperties Properties;
    public static void main(String[] args) throws Exception {
        System.out.println(InetAddress.getLocalHost().getHostName());
        System.out.println(InetAddress.getLocalHost().getHostAddress());





        /*
        ConnectionFactory factory=new ConnectionFactory();
        factory.setUsername(Properties.Rabbitmq_Username);
        factory.setPassword(Properties.Rabbitmq_Password);
        factory.setHost(Properties.Rabbitmq_Host);
        Connection connection=factory.newConnection();
        Channel channel=connection.createChannel();

        channel.queueDeclare(Properties.Rabbitmq_QueueName,false,false,false,null);
        System.out.println("[*] Waiting for messages. To exit press CTRL +C");
        QueueingConsumer consumer=new QueueingConsumer(channel);
        channel.basicConsume(Properties.Rabbitmq_QueueName,true,consumer);
        while(true)
        {
            //   Thread.sleep(500);
            QueueingConsumer.Delivery delivery=consumer.nextDelivery();
            String message =new String(delivery.getBody());
            System.out.println("[x] Received '"+ message+"'");
            //Retrasmitiendo
            ReSender reSender =new ReSender();
            reSender.sender(message);
        }
*/
    }}
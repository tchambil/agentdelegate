package dcc.com.agent.message;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

/**
 * Created by teo on 25/04/15.
 */
public class Receive {
    private final static String Queque_name="hello";
    public static void main(String[] args) throws Exception
    {
        ConnectionFactory factory=new ConnectionFactory();
        factory.setUsername("agent");
        factory.setPassword("159753");
        // factory.setPort(15672);
        //factory.setVirtualHost("teo");
        factory.setHost("192.168.50.19");
        Connection connection=factory.newConnection();
        Channel channel=connection.createChannel();

        channel.queueDeclare(Queque_name,false,false,false,null);
        System.out.println("[*] Waiting for messages. To exit press CTRL +C");
        QueueingConsumer consumer=new QueueingConsumer(channel);
        channel.basicConsume(Queque_name,true,consumer);
        while(true)
        {
            //   Thread.sleep(500);
            QueueingConsumer.Delivery delivery=consumer.nextDelivery();
            String message =new String(delivery.getBody());
            System.out.println("[x] Received '"+ message+"'");

        }


    }
}

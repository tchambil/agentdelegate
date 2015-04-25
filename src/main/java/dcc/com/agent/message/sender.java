package dcc.com.agent.message;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * Created by teo on 25/04/15.
 */
public class sender {
    private final static String QUEUE_NAME="hello";

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("agent");
        factory.setPassword("159753");
        factory.setHost("192.168.50.19");

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        String message = "";
        for (int i = 0; i < 500000; i++) {

            message="message "+"["+i+"]";
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println(" ["+i+"] Sent '" + message + "'");
        }

        channel.close();
        connection.close();

    }
}

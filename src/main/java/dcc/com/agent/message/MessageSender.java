package dcc.com.agent.message;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import dcc.com.agent.config.AgentProperties;

/**
 * Created by teo on 28/04/15.
 */
public class MessageSender {

    public static AgentProperties Properties;
    public void sender(String message)throws Exception{
    ConnectionFactory factory = new ConnectionFactory();
    factory.setUsername(Properties.Rabbitmq_Username);
    factory.setPassword(Properties.Rabbitmq_Password);
    factory.setHost(Properties.Rabbitmq_Host);

    Connection connection = factory.newConnection();
    Channel channel = connection.createChannel();

    channel.queueDeclare(Properties.Rabbitmq_QueueName, false, false, false, null);
    channel.basicPublish("", Properties.Rabbitmq_QueueName, null, message.getBytes());
     channel.close();
    connection.close();
  }

}

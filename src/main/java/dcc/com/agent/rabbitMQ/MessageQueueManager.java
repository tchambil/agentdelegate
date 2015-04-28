package dcc.com.agent.rabbitMQ;

import org.springframework.amqp.core.MessageListener;

/**
 * Created by teo on 28/04/15.
 */
public interface MessageQueueManager extends MessageListener {

    String createQueue(String queueName);

    void sendMessage(String message, String destinationQueueName) throws Exception;

}

package dcc.com.agent.message;

import org.springframework.amqp.core.MessageListener;

/**
 * Created by teo on 25/04/15.
 * Manager responsible for interacting with RabbitMQ. Is responsible for
 * 1. creating queues in RabbitMQ
 * 2. sending messages to queues
 * 3. acting as a listener for messages received over the queues.
 */
public interface MessageQueueManager extends MessageListener {
    String createQueue(String queueName);

    void sendMessage(String message, String destinationQueueName) throws Exception;
}

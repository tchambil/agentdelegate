package dcc.com.agent.rabbitMQ;

import dcc.com.agent.config.AgentProperties;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * Created by teo on 27/04/15.
 */
public class Producer {
    public AgentProperties agentProperties;
    public static void main(String[] args) throws Exception {
        /*ApplicationContext context = new AnnotationConfigApplicationContext("classpath:/rabbitmq.xml");
        MessageQueueManager manager = context.getBean(MessageQueueManagerImpl.class);
        manager.createQueue("myTestQueue");
        manager.sendMessage("myTestMessage", "myTestQueue");*/

        ApplicationContext context =
                new GenericXmlApplicationContext("classpath:/rabbitmq.xml");
        AmqpTemplate template = context.getBean(AmqpTemplate.class);

       template.convertAndSend("myqueue", "foo01");

         String foo = (String) template.receiveAndConvert("myqueue");
        System.out.println(foo);
    }

}

package dcc.com.agent.rabbitMQ;

import dcc.com.agent.config.AgentProperties;

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
        int n=1;
    while(true)
    {  n++;
        n=n*n;
        System.out.println("Print"+n);
    }
    /*    ApplicationContext context =
                new GenericXmlApplicationContext("classpath:/rabbitmq.xml");
        AmqpTemplate template = context.getBean(AmqpTemplate.class);

       template.convertAndSend("hello", "foo01");

         String foo = (String) template.receiveAndConvert("hello");
        System.out.println(foo);
   */




    }
}

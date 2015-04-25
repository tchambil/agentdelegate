# agentdelegate
==Introduction to agent at web servers:

It's a idea of https://github.com/jack-krupansky-BT/Agent-Server-Stage-0.

the idea is contribute and improve "Agent-Server-Stage-0", with framework "spring boot" (http://projects.spring.io/spring-boot/)  and run directly from RESTFUL. 

our work is implement the comunication between web servers with message queue of high concurrency. For the  delegation of tasks between web servers.

Actually we are working in the implement of RabbitMQ broker. we are writing the manual for functionality.

For install:
execute from terminal: java -jar agent-rest-1.0.jar

For start agent:

http://localhost:8080/index.html 

# For Install RabbitMQ in Ubuntu

sudo apt-get remove rabbitmq-server
sudo apt-get install python-software-properties
sudo add-apt-repository "deb http://www.rabbitmq.com/debian/ testing main"
wget http://www.rabbitmq.com/rabbitmq-signing-key-public.asc
sudo apt-key add rabbitmq-signing-key-public.asc
sudo apt-get update
sudo apt-get install rabbitmq-server -y
sudo service rabbitmq-server start
sudo rabbitmq-plugins enable rabbitmq_management
sudo service rabbitmq-server restart


## Simple Microservices Spring Rabbitmq RPC

Simple example of microservice architecture app with RabbitMQ messaging using Spring Web and Spring AMQP with Remote Procedure Call messaging pattern.

API Gateway, Server-1 and Server-2 are independent Spring applications that start manually. After each of them starts running and RabbitMQ Server is up we can work with our microservice infrastructure.

API Gateway is an entry point to infrastructure. It receives web requests from clients and remotely calls the functions on server-1 and server-2 to use their functionality, get replies from them and send the results of their work to clients.

Microservices communicate by custom class CustomMessage. It can be replaced with any Entity or DTO we need for business logic.

Server-1 and Server-2 (depending on which of them was called) receive the CustomMessage entity from their own Rabbit Queue. 
After receiving the entity they modify it with the string "Listener 1 / 2 works" to prove that they are working and imitate the useful business-logic. 
This action can be replaced by any functionality we need.
After that they send back modified CustomMessage entities to API Gateway. 
API Gateway receives the result from Queue and returns it to the client.

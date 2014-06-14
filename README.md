## MPS_AE_ExternalComponents
=============================
The Project is using http://www.gradle.org/ as build and dependency management.
For contributing to this project:
* Download `gradle` and put it in path
* install eclipse `gradle-plugin`


Bank
-------
* Start tomcat with `gradle bootRun`

 
Transport
-------
* Download [rabbit-mq](https://www.rabbitmq.com/download.html)
* Install management-plugin `rabbitmq-plugins enable rabbitmq_management`
* Restart rabbitmq `rabbitmq-server restart`
* visit [localhost](http://localhost:15672) and login with `user: guest pw: guest`



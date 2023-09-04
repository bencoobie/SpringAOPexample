package org.example;

import org.example.config.Configuration;
import org.example.model.User;
import org.example.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class App 
{
    public static void main( String[] args )
    {
     var ac=new AnnotationConfigApplicationContext(Configuration.class);

        var service=ac.getBean(UserService.class);

        User user=new User();

        user.setName("Kubilay");
        user.setUserName("bencoobie");

        service.checkOrder(user,1234);

    }
}


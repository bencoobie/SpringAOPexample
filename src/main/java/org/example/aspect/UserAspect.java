package org.example.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.example.model.User;
import java.util.logging.Logger;

@Aspect
public class UserAspect {
private Logger logger=Logger.getLogger(UserAspect.class.getName());

    @Around("execution(* org.example.service.*.*(..))")
    public void log(ProceedingJoinPoint joinPoint)throws Throwable{

        //Getting method arguments with getArgs() method.
        Object[] arguments=joinPoint.getArgs();
        //Casting object to get name,username by User
      User user=(User)arguments[0];

        logger.info("New Query\n "+
                "Name:"+user.getName()+"\n"+
                "Username:"+user.getUserName()+"\n"+
         "OrderId:"+arguments[1]);

            }
}

package org.example.config;

import org.example.aspect.UserAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@org.springframework.context.annotation.Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "org.example.service")
public class Configuration {
    @Bean
  public UserAspect userAspect(){
    return new UserAspect();
}

}

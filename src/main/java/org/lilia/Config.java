package org.lilia;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("org.lilia")
@PropertySource("classpath:spring.properties")
public class Config {
}

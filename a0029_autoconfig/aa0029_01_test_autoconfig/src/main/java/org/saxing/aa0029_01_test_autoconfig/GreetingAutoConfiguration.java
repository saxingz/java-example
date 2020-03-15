package org.saxing.aa0029_01_test_autoconfig;

import org.saxing.aa0029_02_test_greet.GreetingApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * auto config
 *
 * @author saxing 2020/3/15 23:58
 *
 */
@Configuration
@ConditionalOnClass(GreetingApplicationRunner.class)
public class GreetingAutoConfiguration  {

    @Bean
    @ConditionalOnMissingBean(GreetingApplicationRunner.class)
    @ConditionalOnProperty(name = "greeting.enabled", havingValue = "true", matchIfMissing = true)
    public GreetingApplicationRunner greetingApplicationRunner() {
        return new GreetingApplicationRunner();
    }

}

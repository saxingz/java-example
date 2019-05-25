package org.saxing.aa0029_02_test_greet;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;


/**
 * runner
 *
 * @author saxing 2019/5/25 14:26
 */
@Slf4j
public class GreetingApplicationRunner implements ApplicationRunner {

    public GreetingApplicationRunner() {
        log.info("Initializing GreetingApplicationRunner.");
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("Hello everyone! We all like Spring! ");
    }
}

package org.saxing.aa0035_01_validatedemo.demo.component;

import org.saxing.aa0035_01_validatedemo.demo.dto.DTO;
import org.saxing.validator.annotation.ServiceValidation;
import org.springframework.stereotype.Component;

@Component
public class MyComponent {

    @ServiceValidation
    public void doSomething (DTO dto) {

    }
}

package org.saxing.validator.matchers;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.saxing.validator.exception.ServiceValidationErrorCollection;
import org.saxing.validator.exception.ServiceValidationException;

public class ServiceValidatorErrorMatcher extends TypeSafeMatcher<ServiceValidationException> {

    private Integer expectedSize;
    private ServiceValidationErrorCollection errors;

    public ServiceValidatorErrorMatcher(final Integer expectedSize) {
        this.expectedSize = expectedSize;
    }

    public static ServiceValidatorErrorMatcher errorCollectionHasSize(final Integer expectedSize) {
        return new ServiceValidatorErrorMatcher(expectedSize);
    }

    @Override
    protected boolean matchesSafely(final ServiceValidationException e) {
        errors = e.getErrors();
        return errors.size() == expectedSize;
    }

    @Override
    public void describeTo(final Description description) {
        description.appendValue(expectedSize).appendText(" was not found instead of ").appendValue(errors.size());
    }
}

package org.saxing.chain;

import java.util.Objects;

/**
 * Request
 *
 * @author saxing  2018/11/18 14:39
 */
public class Request {

    /**
     * The type of this request, used by each item in the chain to see if they should or can handle
     * this particular request
     */
    private final RequestType requestType;

    /**
     * A description of the request
     */
    private final String requestDescription;

    /**
     * Indicates if the request is handled or not. A request can only switch state from unhandled to
     * handled, there's no way to 'unhandle' a request
     */
    private boolean handled;

    /**
     * Create a new request of the given type and accompanied description.
     *
     * @param requestType        The type of request
     * @param requestDescription The description of the request
     */
    public Request(final RequestType requestType, final String requestDescription) {
        this.requestType = Objects.requireNonNull(requestType);
        this.requestDescription = Objects.requireNonNull(requestDescription);
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public String getRequestDescription() {
        return requestDescription;
    }

    public boolean isHandled() {
        return handled;
    }

    /**
     * Mark the request as handled
     */
    public void markHandled() {
        this.handled = true;
    }

    @Override
    public String toString() {
        return getRequestDescription();
    }
}

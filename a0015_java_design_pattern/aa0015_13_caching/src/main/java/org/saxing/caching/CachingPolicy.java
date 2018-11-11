package org.saxing.caching;

/**
 * caching policy
 *
 * @author saxing  2018/11/11 21:48
 */
public enum CachingPolicy {
    THROUGH("through"),
    AROUND("around"),
    BEHIND("behind"),
    ASIDE("aside"),
    ;

    private String policy;

    CachingPolicy(String policy) {
        this.policy = policy;
    }

    public String getPolicy() {
        return policy;
    }

    public void setPolicy(String policy) {
        this.policy = policy;
    }
}

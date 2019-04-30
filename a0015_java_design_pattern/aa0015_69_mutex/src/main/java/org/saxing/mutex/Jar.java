package org.saxing.mutex;

/**
 * jar
 *
 * @author saxing 2019/4/30 14:29
 */
public class Jar {

    /**
     * The lock which must be acquired to access the beans resource.
     */
    private final Lock lock;

    /**
     * The resource within the jar.
     */
    private int beans;

    public Jar(int beans, Lock lock) {
        this.beans = beans;
        this.lock = lock;
    }

    /**
     * Method for a thief to take a bean.
     */
    public boolean takeBean() {
        boolean success = false;
        try {
            lock.acquire();
            success = beans > 0;
            if (success) {
                beans = beans - 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.release();
        }

        return success;
    }

}

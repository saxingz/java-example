package org.saxing.objectpool;

/**
 * Oliphaunt object pool
 *
 * @author saxing 2019/5/9 15:30
 */
public class OliphauntPool extends ObjectPool<Oliphaunt> {

    @Override
    protected Oliphaunt create() {
        return new Oliphaunt();
    }
}

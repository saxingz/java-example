package org.saxing.factorykit;

import java.util.function.Supplier;

/**
 * Functional interface that allows adding builder with name to the factory.
 *
 * @author saxing 2019/1/8 21:18
 */
public interface Builder {

    void add(WeaponType name, Supplier<Weapon> supplier);

}

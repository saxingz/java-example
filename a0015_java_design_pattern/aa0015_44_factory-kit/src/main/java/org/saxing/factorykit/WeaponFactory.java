package org.saxing.factorykit;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Functional interface, an example of the factory-kit design pattern.
 * <br>Instance created locally gives an opportunity to strictly define
 * which objects types the instance of a factory will be able to create.
 * <br>Factory is a placeholder for {@link Builder}s
 * with {@link WeaponFactory#create(WeaponType)} method to initialize new objects.
 *
 * @author saxing 2019/1/8 21:17
 */
public interface WeaponFactory {

    Weapon create(WeaponType name);

    static WeaponFactory factory(Consumer<Builder> consumer){
        Map<WeaponType, Supplier<Weapon>> map = new HashMap<>();
        consumer.accept(map::put);
        return name -> map.get(name).get();
    }

}

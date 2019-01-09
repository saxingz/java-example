package org.saxing.factorymethod;

/**
 * Concrete subclass for creating new objects.
 *
 * @author saxing 2019/1/9 23:42
 */
public class ElfBlacksmith implements Blacksmith {

    @Override
    public Weapon manufactureWeapon(WeaponType weaponType) {
        return new ElfWeapon(weaponType);
    }
}

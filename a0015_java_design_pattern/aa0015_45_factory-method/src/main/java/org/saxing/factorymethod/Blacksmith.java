package org.saxing.factorymethod;

/**
 * The interface containing method for producing objects.
 *
 * @author saxing 2019/1/9 23:29
 */
public interface Blacksmith {

    Weapon manufactureWeapon(WeaponType weaponType);

}

package org.saxing.bridge;

/**
 * Weapon interface
 *
 * @author saxing  2018/11/3 17:28
 */
public interface Weapon {

    void wield();

    void swing();

    void unwield();

    Enchantment getEnchantment();

}

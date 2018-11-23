package org.saxing.command;

/**
 * Goblin
 *
 * @author saxing  2018/11/23 14:36
 */
public class Goblin extends Target {

    public Goblin() {
        setSize(Size.NORMAL);
        setVisibility(Visibility.VISIBLE);
    }

    @Override
    public String toString() {
        return "Goblin";
    }
}

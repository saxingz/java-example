package org.saxing.command;

/**
 * Interface for Commands.
 * 
 * @author saxing  2018/11/23 14:21
 */
public abstract class Command {

    public abstract void execute(Target target);

    public abstract void undo();

    public abstract void redo();

    @Override
    public abstract String toString();

}

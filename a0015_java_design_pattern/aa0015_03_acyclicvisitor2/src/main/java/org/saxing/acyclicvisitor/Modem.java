package org.saxing.acyclicvisitor;

/**
 * Modem abstract class
 * 
 * @author saxing  2018/10/8 23:25
 */
public abstract class Modem {
    
    public abstract void accept(ModemVisitor modemVisitor);
    
}

package org.saxing.databus;

import java.util.function.Consumer;

/**
 * Member
 *
 * @author saxing 2018/12/3 15:42
 */
public interface Member extends Consumer<DataType> {

    void accept(DataType event);

}

package org.saxing.a.visitor;

/**
 *  电脑的硬件CPU，用于数据的运算
 */
public class CPU extends ComputerPart {

    @Override
    protected void link(HardwareInterface hardwareInterface) {

        // 先得通过接口连接数据
        hardwareInterface.visitor(this);
        // 连接完了之后，就开始使用cpu
        System.out.println("连接上了之后，利用cpu进行计算数据");
    }

}
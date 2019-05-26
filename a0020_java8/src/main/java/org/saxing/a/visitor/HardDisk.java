package org.saxing.a.visitor;

/**
 * 电脑硬件之硬盘
 */
public class HardDisk extends ComputerPart {

    @Override
    protected void link(HardwareInterface hardwareInterface) {
        // 必须先通过接口，把硬盘先连上，然后才能操作
        hardwareInterface.visitor(this);
        // 硬盘开始干活
        System.out.println("硬盘以及连接上了，开始存储数据");
    }

}

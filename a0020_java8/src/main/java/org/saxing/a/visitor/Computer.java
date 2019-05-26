package org.saxing.a.visitor;

/**
 * 电脑的类，当需要装机的话，就先准备好硬件，即new出来，然后插上接口
 */
public class Computer {

    /**
     * 想装机，先得提供硬件接口才行
     * @param hardwareInterface
     */
    public void useComputer(HardwareInterface hardwareInterface){

        //通过接口，连接cpu
        new CPU().link(hardwareInterface);
        //通过接口，连接显卡
        new VideoCard().link(hardwareInterface);
        //通过接口连接硬盘
        new HardDisk().link(hardwareInterface);

    }

}

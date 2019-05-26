package org.saxing.a.visitor;

/**
 * 电脑硬件之显卡 通过显卡可以进行电脑的屏幕图像的显示
 */
public class VideoCard extends ComputerPart {

    @Override
    protected void link(HardwareInterface hardwareInterface) {

        // 必须先用接口连接上显卡
        hardwareInterface.visitor(this);

        System.out.println("连接上显卡之后，显卡开始工作，提供图像");
    }

}

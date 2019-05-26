package org.saxing.a.visitor;

/**
 * 自定义的接口，实现了硬件接口的类
 */
public class USBInterface implements HardwareInterface {
    @Override
    public void visitor(CPU cpu) {
        System.out.println("usb连接cpu");
    }

    @Override
    public void visitor(VideoCard videoCard) {
        System.out.println("用usb连接显卡");
    }

    @Override
    public void visitor(HardDisk hardDisk) {
        System.out.println("用usb连接硬盘");
    }
}

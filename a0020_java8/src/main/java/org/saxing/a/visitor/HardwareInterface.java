package org.saxing.a.visitor;

/**
 * 硬件的接口
 */
public interface HardwareInterface {

    //定义了一些接口，访问硬件用的
    public void visitor(CPU cpu);
    public void visitor(VideoCard videoCard);
    public void visitor(HardDisk hardDisk);

}

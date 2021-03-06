package org.saxing.multithread.a20180815_netty.activeObject.activeobject;

import org.saxing.multithread.a20180815_netty.activeObject.result.RealResult;
import org.saxing.multithread.a20180815_netty.activeObject.result.Result;

public class Servant implements ActiveObject {

    @Override
    public Result<String> makeString(int count, char fillchar) {
        char[] buffer = new char[count];
        for (int i = 0; i < count; i++) {
            buffer[i] = fillchar;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
        }
        return new RealResult<>(new String(buffer));
    }

    @Override
    public void displayString(String string) {
        try {
            System.out.println("displaying: " + string);
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

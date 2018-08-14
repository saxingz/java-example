package org.saxing.study.a20180814_handler;

public class Helper {

    public void handle(int count, char c){
        System.out.println("    handle （" + count + ", " + c +"）begin");
        for (int i = 0; i < count; i++) {
            slowly();
            System.out.print(c);
        }
        System.out.println();
        System.out.println("    handle （" + count + ", " + c +"）end");
    }

    private void slowly() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

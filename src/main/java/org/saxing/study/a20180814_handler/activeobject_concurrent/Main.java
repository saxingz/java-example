package org.saxing.study.a20180814_handler.activeobject_concurrent;

public class Main {

    public static void main(String[] args) {
        ActiveObject activeObject = ActiveObjectFactory.createActiveObject();
        try {
            new MakerClientThread("Alice", activeObject).start();
            new MakerClientThread("Booby", activeObject).start();
            new DisplayClientThread("Chris", activeObject).start();
            Thread.sleep(5000);
        } catch (InterruptedException e){
        } finally {
            System.out.println("*** shutdown ***");
            activeObject.shutdown();
        }
    }

}

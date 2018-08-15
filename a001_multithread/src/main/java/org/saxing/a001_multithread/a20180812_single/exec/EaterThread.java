package org.saxing.a001_multithread.a20180812_single.exec;

public class EaterThread extends Thread {

    private String name;
    private final Tool leftHand;
    private final Tool rightHand;

    public EaterThread(String name, Tool leftHand, Tool rightHand) {
        this.name = name;
        this.leftHand = leftHand;
        this.rightHand = rightHand;
    }

    @Override
    public void run() {
        while (true){
            eat();
        }
    }

    private void eat() {
        synchronized (leftHand){
            System.out.println(name + " take up " + leftHand + " (left)." );
            synchronized (rightHand){
                System.out.println(name + " take up " + rightHand + " (right).");
                System.out.println("eating.... yum yum");
                System.out.println(name + " puts down " + rightHand + " (right).");
            }
            System.out.println(name + " puts down " + leftHand + " (left).");
        }
    }
}

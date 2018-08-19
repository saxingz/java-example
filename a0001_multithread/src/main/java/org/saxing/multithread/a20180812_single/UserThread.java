package org.saxing.multithread.a20180812_single;

public class UserThread extends Thread {

    private final Gate gate;
    private final String myname;
    private final String myaddress;

    public UserThread(Gate gate, String myname, String myaddress) {
        this.gate = gate;
        this.myname = myname;
        this.myaddress = myaddress;
    }

    @Override
    public void run() {
        System.out.println(myname + " begin");
        while (true){
            gate.pass(myname, myaddress);
        }
    }
}

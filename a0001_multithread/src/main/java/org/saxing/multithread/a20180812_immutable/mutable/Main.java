package org.saxing.multithread.a20180812_immutable.mutable;

public class Main {

    public static void main(String[] args) {
        MutablePerson mutable = new MutablePerson("start", "start");
        new CrackerThread(mutable).start();
        new CrackerThread(mutable).start();
        new CrackerThread(mutable).start();
        for (int i = 0; true; i++) {
            mutable.setPerson("" + i, "" + i);
        }

    }

}

class CrackerThread extends Thread{
    private final MutablePerson mutablePerson;

    public CrackerThread(MutablePerson mutablePerson) {
        this.mutablePerson = mutablePerson;
    }

    @Override
    public void run() {
        while (true){
            ImmutablePerson immutable = new ImmutablePerson(mutablePerson);
            if (!immutable.getName().equals(immutable.getAddress())){
                System.out.println(currentThread().getName() + "***   broken ****" + immutable);
            }
        }
    }
}
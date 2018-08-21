package org.saxing.nettystudy.a03_netty_test.test2;

import java.util.List;

public class NettyMessage2 {

    //协议号，用于标注本条协议的用法，
    private int opCode;
    //指用户的ID
    private int playerId;
    //本条协议做事情要的多余参数
    private List<String> list;

    public int getOpCode() {
        return opCode;
    }

    public void setOpCode(int opCode) {
        this.opCode = opCode;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }
}

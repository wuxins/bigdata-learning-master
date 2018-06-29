package com.fsmeeting;

import java.io.Serializable;

public class OnlineMsg implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 企业ID
     */
    private int bpartnerId;

    /**
     * 房间ID
     */
    private int roomId;

    /**
     * 事件时间
     */
    private long time;

    private String formatTime;

    /**
     * 在线人数
     */
    private int num;

    public int getBpartnerId() {
        return bpartnerId;
    }

    public void setBpartnerId(int bpartnerId) {
        this.bpartnerId = bpartnerId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getFormatTime() {
        return formatTime;
    }

    public void setFormatTime(String formatTime) {
        this.formatTime = formatTime;
    }
}

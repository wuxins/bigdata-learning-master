package com.fsmeeting.analytics.collect.bean;

import java.io.Serializable;

/**
 * Client devices request info
 */
public class ClientLog extends IntrinsicParams implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * device
     */

    private String device;

    /**
     * event
     */
    private String event;

    /**
     * meeting
     */
    private String meeting;

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getMeeting() {
        return meeting;
    }

    public void setMeeting(String meeting) {
        this.meeting = meeting;
    }
}

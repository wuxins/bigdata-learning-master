package com.fsmeeting.analytics.collect.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Client devices request info
 */
public class ClientDevicesRequest extends IntrinsicParams implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * device
     */
    private Map<String, Object> device = new HashMap<String, Object>();

    /**
     * event
     */
    private Map<String, Object> event = new HashMap<String, Object>();

    /**
     * meeting
     */
    private Map<String, Object> meeting = new HashMap<String, Object>();

    public Map<String, Object> getDevice() {
        return device;
    }

    public void setDevice(Map<String, Object> device) {
        this.device = device;
    }

    public Map<String, Object> getEvent() {
        return event;
    }

    public void setEvent(Map<String, Object> event) {
        this.event = event;
    }

    public Map<String, Object> getMeeting() {
        return meeting;
    }

    public void setMeeting(Map<String, Object> meeting) {
        this.meeting = meeting;
    }

}

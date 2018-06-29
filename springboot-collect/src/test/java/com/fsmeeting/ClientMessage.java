package com.fsmeeting;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ClientMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * base info
     */
    private Map<String, Object> base = new HashMap<>();

    /**
     * event
     */
    private Map<String, Object> event = new HashMap<>();

    /**
     * meeting
     */
    private Map<String, Object> meeting = new HashMap<>();

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

    public Map<String, Object> getBase() {
        return base;
    }

    public void setBase(Map<String, Object> base) {
        this.base = base;
    }

}

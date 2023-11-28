package com.NPU.分布式实验.实验3;

import java.io.Serializable;
import java.util.Calendar;

/**
 * class <em>Meeting</em> represents a meeting in the SSD8 Distributed Calendar
 * Assignment.
 *
 * @author wben
 * @version 1.0
 */
public class Meeting implements Serializable {

    /**
     * Each meeting has a caller.
     */
    private String callerName;

    /**
     * Each meeting has a another in attendance.
     */
    private String attenderName;

    /**
     * Each meeting has a starting time and date.
     */
    private Calendar meetingStart;

    /**
     * Each meeting has an ending time and date.
     */
    private Calendar meetingEnd;

    /**
     * Each meeting has a title.
     */
    private String meetingTitle;

    /**
     * Each meeting has a distinct id number.
     */
    private int id;

    /**
     * class <em>Meeting</em> constructor.
     *
     * @param inId           int id for this meeting
     * @param inCallerName   String name of meeting caller
     * @param inAttenderName String name of user in attendance
     * @param inStart        Calendar start time of the meeting
     * @param inEnd          Calendar end time of the meeting
     * @param inTitle        String title of meeting
     */
    public Meeting(int inId, String inCallerName, String inAttenderName, Calendar inStart, Calendar inEnd,
                   String inTitle) {

        /*
         * Initialize the variables here.
         */
        id = inId;
        callerName = inCallerName;
        attenderName = inAttenderName;
        meetingStart = inStart;
        meetingEnd = inEnd;
        meetingTitle = inTitle;
    }

    /**
     * Accessor for callerName.
     *
     * @return String name of caller
     */
    public String getCallerName() {
        return callerName;
    }

    /**
     * Mutator for callerName
     *
     * @param inCallerName String name of the meeting caller
     */
    public void setCallerName(String inCallerName) {
        callerName = inCallerName;
    }

    /**
     * Accessor for attender name.
     *
     * @return String name of attender
     */
    public String getAttenderName() {
        return attenderName;
    }

    /**
     * Mutator for attender name.
     *
     * @param inAttenderName String name of attender
     */
    public void setAttenderName(String inAttenderName) {
        attenderName = inAttenderName;
    }

    /**
     * Accessor for meeting title.
     *
     * @return String title of meeting
     */
    public String getMeetingTitle() {
        return meetingTitle;
    }

    /**
     * Mutator for meeting title.
     *
     * @param inMeetingTitle String title of meeting.
     */
    public void setMeetingTitle(String inMeetingTitle) {
        meetingTitle = inMeetingTitle;
    }

    /**
     * Accessor for meeting start time.
     *
     * @return java.util.Calendar start time of meeting.
     */
    public Calendar getMeetingStart() {
        return meetingStart;
    }

    /**
     * Mutator for meeting start time.
     *
     * @param inMeetingStart java.util.Calendar start time of meeting.
     */
    public void setMeetingStart(Calendar inMeetingStart) {
        meetingStart = inMeetingStart;
    }

    /**
     * Accessor for meeting end time.
     *
     * @return java.util.Calendar end time of meeting.
     */
    public Calendar getMeetingEnd() {
        return meetingEnd;
    }

    /**
     * Mutator for meeting end time.
     *
     * @param inMeetingEnd java.util.Calendar end time of meeting.
     */
    public void setMeetingEnd(Calendar inMeetingEnd) {
        meetingEnd = inMeetingEnd;
    }

    /**
     * Accessor for meeting id.
     *
     * @return int id of meeting
     */
    public int getId() {
        return id;
    }

    /**
     * Mutator for meeting id.
     *
     * @param inId int id of meeting.
     */
    public void setId(int inId) {
        id = inId;
    }

    /**
     * toString method
     *
     * @return String string representation of this meeting.
     */
    public String toString() {
        return new String("ID: " + id + " Called by: " + callerName + " attended by: " + attenderName + "\nStarting: "
                + meetingStart.get(Calendar.DATE) + "/" + meetingStart.get(Calendar.MONTH) + "/"
                + meetingStart.get(Calendar.YEAR) + " " + meetingStart.get(Calendar.HOUR) + ":"
                + meetingStart.get(Calendar.MINUTE) + " Ending: " + meetingEnd.get(Calendar.DATE) + "/"
                + meetingEnd.get(Calendar.MONTH) + "/" + meetingEnd.get(Calendar.YEAR) + " "
                + meetingEnd.get(Calendar.HOUR) + ":" + meetingEnd.get(Calendar.MINUTE) + "\nTitle: " + meetingTitle);
    }
}

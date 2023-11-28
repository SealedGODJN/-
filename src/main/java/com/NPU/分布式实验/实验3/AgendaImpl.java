package com.NPU.分布式实验.实验3;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Vector;

/**
 * class <em>AgendaImplementation</em> implements the remote methods defined in
 * the interface <em>Agenda</em>.
 *
 * @author wben
 * @version 1.0
 */

public class AgendaImpl extends UnicastRemoteObject implements Serializable, Agenda {

    /**
     * The agenda is represented as a Vector of object of class meeting.
     */
    private Vector calendar;

    /**
     * The usernames and passwords that are registered are stored in a HashMap.
     */
    private HashMap users;

    int meetingId = 0;

    /**
     * class <em>AgendaImpl</em> constructor.
     */
    public AgendaImpl() throws RemoteException {
        calendar = new Vector();
        users = new HashMap();
    }

    /**
     * Authenticate a user.
     *
     * @param username String name of user
     * @param password String password of user
     * @return boolean true if authenticate succeeds, false otherwise
     */
    private boolean authenticate(String username, String password) {

        String returnPasswd = null;
        /*
         * Authenticate the user by getting his password from the HashMap.
         */
        returnPasswd = (String) users.get(username);

        /*
         * A null result means that the key was not present.
         */
        if (returnPasswd != null) {
            /*
             * if the password matches, return true.
             */
            if (returnPasswd.equals(password)) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    /**
     * Check conflicts for an input time window with the caller of the meeting
     * and the other user in attendance.
     *
     * @param attender String name of the meeting attender
     * @param caller   String name of meeting caller
     * @param start    java.util.GregorianCalendar start time of meeting
     * @param end      java.util.GregorianCalendar end time of meeting
     * @return boolean true indicates a time conflict exists for one of the
     * participants.
     */
    private boolean CheckConflict(String caller, String attender, GregorianCalendar start, GregorianCalendar end) {

        /*
         * Iterate through an enumeration of the calendars and check if the
         * caller or the attendee has another meeting at that time.
         */
        Enumeration meetings = calendar.elements();

        while (meetings.hasMoreElements()) {
            /*
             * Get the next element from the enumeration.
             */
            Meeting tempMeeting = (Meeting) meetings.nextElement();

            /*
             * See if tempMeeting falls in the given time range and if the
             * caller or attendee were involved at all. If so, return false.
             */
            if (((tempMeeting.getMeetingStart().before(start) && tempMeeting.getMeetingEnd().before(end)
                    && tempMeeting.getMeetingEnd().after(start))
                    || (tempMeeting.getMeetingStart().after(start) && tempMeeting.getMeetingEnd().before(end))
                    || (tempMeeting.getMeetingStart().after(start) && tempMeeting.getMeetingEnd().after(end)
                    && tempMeeting.getMeetingStart().before(end))
                    || (tempMeeting.getMeetingStart().before(start) && tempMeeting.getMeetingEnd().after(end)))
                    && ((tempMeeting.getCallerName().equals(caller)) || (tempMeeting.getAttenderName().equals(attender))
                    || (tempMeeting.getCallerName().equals(attender))
                    || (tempMeeting.getAttenderName().equals(caller)))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Implementation of the add function.
     *
     * @param attender String name of the meeting attender
     * @param username String username of meeting caller
     * @param password String password of meeting caller
     * @param start    java.util.GregorianCalendar start time of meeting
     * @param end      java.util.GregorianCalendar end time of meeting
     * @param title    String title of the meeting
     * @return boolean indicating success of addition
     */
    public boolean add(String attender, String username, String password, GregorianCalendar start,
                       GregorianCalendar end, String title) throws RemoteException {

        /*
         * Try to authenticate the user.
         */
        if (!authenticate(username, password)) {
            return false;
        }

        /*
         * Create a new meeting object to add to the agenda.
         */
        Meeting newMeeting = new Meeting(++meetingId, username, attender, start, end, title);

        /*
         * Check the agenda to make sure this meeting does not conflict with
         * other meetings for the caller or the attendee.
         */
        if (CheckConflict(username, attender, start, end)) {
            return false;
        }

        /*
         * If we have made it to here, then the meeting must have been
         * acceptable, so, add it to the Vector of meetings and return true.
         */
        calendar.add(newMeeting);
        return true;
    }

    /**
     * Implementation of query function.
     *
     * @param start    java.util.GregorianCalendar start time of the interval
     * @param end      java.util.GregorianCalendar end time of the interval
     * @param username String username of the person who called the meeting
     * @return java.util.Vector vector containing all meetings in the given
     * range.
     */
    public Vector query(GregorianCalendar start, GregorianCalendar end, String name, String password)
            throws RemoteException {

        if (!authenticate(name, password)) {
            return null;
        }

        Vector matches = new Vector();
        /**
         * Enumerate through the vector of meetings and add the meetings that
         * match to the return vector.
         */
        Enumeration meetings = calendar.elements();

        while (meetings.hasMoreElements()) {
            /**
             * Get the next element from the enumeration.
             */
            Meeting tempMeeting = (Meeting) meetings.nextElement();

            /**
             * See if tempMeeting falls in the given time range and was called
             * by the input name or is to be attended by the input name. If it
             * was, add it to the return vector.
             */
            if ((tempMeeting.getMeetingStart().after(start)) && (tempMeeting.getMeetingEnd().before(end))
                    && ((tempMeeting.getCallerName().equals(name)) || (tempMeeting.getAttenderName().equals(name)))) {
                matches.add(tempMeeting);
            }
        }
        return matches;
    }

    /**
     * Implementation of register function.
     *
     * @param username String username of new user
     * @param password String password of new user
     * @return boolean indicating success for registration
     */
    public boolean register(String username, String password) throws RemoteException {

        /*
         * Hashing key is the username and the value is the password.
         */
        if (users.containsKey(username)) {
            /*
             * If the user name already exists in the hash map, return false to
             * indicate a failed registration.
             */
            return false;
        } else {
            /*
             * Add the new username and password combination to the hash map.
             */
            users.put(username, password);
        }
        return true;
    }

    /**
     * Implementation of delete function.
     *
     * @param username String user name of user
     * @param password String password of user
     * @param itemId   int id of item to delete
     * @return boolean indicating success of delete
     */
    public boolean delete(String username, String password, int itemId) throws RemoteException {

        int index = -1, removeIndex = -1;

        /*
         * Try to authenticate the user.
         */
        if (!authenticate(username, password)) {
            return false;
        }

        /*
         * Iterate through an enumeration of the vector of meetings and remove
         * the one that with id itemId.
         */
        Enumeration meetings = calendar.elements();

        while (meetings.hasMoreElements()) {
            /*
             * Get the next element from the enumeration.
             */
            Meeting tempMeeting = (Meeting) meetings.nextElement();

            /*
             * Increment the index and see if the meeting we have just gotten
             * from the enumeration matches the id we are searching for.
             */
            index++;
            if (tempMeeting.getId() == itemId) {
                if (!tempMeeting.getCallerName().equals(username)) {
                    return false;
                }
                removeIndex = index;
                break;
            }
        }
        /*
         * If we have found what we are looking for, remove it and return true;
         */
        if (removeIndex != -1) {
            calendar.removeElementAt(removeIndex);
            return true;
        }

        /*
         * If we have made it to here, then the item was not found, so return
         * false.
         */
        return false;
    }

    /**
     * Implementation of clear function.
     *
     * @param username String user name of user
     * @param password String password of user
     * @return boolean indicating success of the clear
     */
    public boolean clear(String username, String password) throws RemoteException {

        /*
         * Try to authenticate the user.
         */
        if (!authenticate(username, password)) {
            return false;
        }

        /*
         * Iterate through an enumeration of the vector of meetings and remove
         * the ones that match the input user name.
         */
        Enumeration meetings = calendar.elements();

        while (meetings.hasMoreElements()) {
            /*
             * Get the next element from the enumeration.
             */
            Meeting tempMeeting = (Meeting) meetings.nextElement();

            /*
             * If the caller of this meeting matches the input username, remove
             * it from the vector.
             */
            if (tempMeeting.getCallerName().equals(username)) {
                calendar.remove(tempMeeting);
            }
        }

        return true;
    }
}

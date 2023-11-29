package com.NPU.分布式实验.实验3;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.GregorianCalendar;
import java.util.Vector;

/**
 * Interface <em>agenda</em> extends <em>Remote</em> to allow functions to be
 * used via RMI.
 *
 * @author wben
 * @version 1.0
 */
public interface Agenda extends Remote {

    /**
     * Add a meeting to the agenda
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
                       GregorianCalendar end, String title) throws RemoteException;

    /**
     * Query the meetings in a given interval.
     *
     * @param start    java.util.GregorianCalendar start time of the interval
     * @param end      java.util.GregorianCalendar end time of the interval
     * @param username String username of the person who called the meeting
     * @param password String password of the person who called the meeting
     * @return java.util.Vector vector containing all meetings in the given
     * range.
     */
    public Vector query(GregorianCalendar start, GregorianCalendar end, String name, String password)
            throws RemoteException;

    /**
     * Register a new user.
     *
     * @param username String username of new user
     * @param password String password of new user
     * @return boolean indicating success for registration
     */
    public boolean register(String username, String password) throws RemoteException;

    /**
     * Delete an item from the agenda.
     *
     * @param username String user name of user
     * @param password String password of user
     * @param itemId   int id of item to delete
     * @return boolean indicating success of delete
     */
    public boolean delete(String username, String password, int itemId) throws RemoteException;

    /**
     * Clear a user's agenda items
     *
     * @param username String user name of user
     * @param password String password of user
     * @return boolean indicating success of the clear
     */
    public boolean clear(String username, String password) throws RemoteException;

}

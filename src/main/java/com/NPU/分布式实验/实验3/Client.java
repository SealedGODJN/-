package com.NPU.分布式实验.实验3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.rmi.Naming;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 * class <em>Client</em> represents a distributed calendar client for the SSD8
 * RMI Distributed Calendar system.
 *
 * @author wben
 * @version 1.0
 */

public class Client {

    /**
     * Output is written to the screen (standard out)
     */
    static PrintWriter screen = new PrintWriter(System.out, true);

    /**
     * Main method.
     */
    public static void main(String[] args) {

        StringTokenizer paramTokenizer, dateTokenizer;
        String username = null, password = null, hostname = null, start_time, end_time, tempTime, tempDate,
                attender = null, title = null;
        int mon, day, year, hour, min, meetingid = 0, portNumber = 1099, choice = 0;
        Agenda myAgenda = null;
        GregorianCalendar begin = null, end = null;
        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
        Vector result = null;

        /*
         * Print usage instructions of the incorrect parameters are supplied.
         */
        if (args.length < 1) {
            screen.println("Usage: Client <servername> <port> [operation args]");
            System.exit(0);
        }
        if (args.length >= 3) {

            /*
             * Get the host name and port number of the server.
             */
            hostname = args[0];
            portNumber = Integer.parseInt(args[1]);

            /*
             * Set the reference for myAgenda equal to what we get back from the
             * Naming.lookup function.
             */
            try {
                myAgenda = (Agenda) Naming.lookup("rmi://" + hostname + ":" + portNumber + "/SSD8Exercise3");
            } catch (Exception e) {
                e.printStackTrace();
            }

            /*
             * Check to make sure there are the correct paramters for
             * registration.
             */
            if (args[2].equals("register")) {
                if (args.length != 5) {
                    screen.println("Usage: Client <servername> <port> register <username> <password>");
                    System.exit(0);
                } else {
                    /*
                     * Get the user name and password from the input paramters.
                     */
                    username = args[3];
                    password = args[4];
                    try {
                        /*
                         * Try to register the user, print an error if the
                         * register function does not succeed.
                         */
                        if (myAgenda.register(username, password)) {
                            screen.println("User: " + username + " registered successfully!");
                        } else {
                            screen.println("Could not register user: " + username + "!");
                            screen.println("Please try a different username!");
                            System.exit(0);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            /*
             * Check to make sure there are the correct paramters for an
             * addition.
             */
            if (args[2].equals("add")) {
                if (args.length != 9) {
                    screen.println("Usage: Client <servername> <port> add <username> <password> <attender>");
                    screen.println("              <begin> <end> <title>");
                    System.exit(0);
                } else {
                    /*
                     * Get the user name, password, start time, name of the user
                     * in attendance, end time, and title from the command line.
                     */
                    username = args[3];
                    password = args[4];
                    attender = args[5];
                    start_time = args[6];
                    end_time = args[7];

                    /*
                     * Tokenize the start_time into date and time based on the
                     * '-' character.
                     */
                    paramTokenizer = new StringTokenizer(start_time, "-");
                    tempDate = paramTokenizer.nextToken();
                    dateTokenizer = new StringTokenizer(tempDate, "/");

                    /*
                     * Parse out the month, day, and year from the date.
                     */
                    mon = Integer.parseInt(dateTokenizer.nextToken());
                    day = Integer.parseInt(dateTokenizer.nextToken());
                    year = Integer.parseInt(dateTokenizer.nextToken());

                    /*
                     * Get the time.
                     */
                    tempTime = paramTokenizer.nextToken();

                    /*
                     * Tokenize the time based on the ':' character.
                     */
                    dateTokenizer = new StringTokenizer(tempTime, ":");

                    /*
                     * Parse out the hour and minute from the time string.
                     */
                    hour = Integer.parseInt(dateTokenizer.nextToken());
                    min = Integer.parseInt(dateTokenizer.nextToken());

                    /*
                     * Create a GregorianCalendar object based on the time
                     * values parsed above.
                     */
                    begin = new GregorianCalendar(year, mon, day, hour, min);

                    /*
                     * Tokenize the end_time into date and time based on the '-'
                     * character.
                     */
                    paramTokenizer = new StringTokenizer(end_time, "-");
                    tempDate = paramTokenizer.nextToken();

                    /*
                     * Tokenize the date based upon the '/' character.
                     */
                    dateTokenizer = new StringTokenizer(tempDate, "/");

                    /*
                     * Parse out the month, day and year.
                     */
                    mon = Integer.parseInt(dateTokenizer.nextToken());
                    day = Integer.parseInt(dateTokenizer.nextToken());
                    year = Integer.parseInt(dateTokenizer.nextToken());
                    tempTime = paramTokenizer.nextToken();

                    /*
                     * Tokenize the time based on the ':' character.
                     */
                    dateTokenizer = new StringTokenizer(tempTime, ":");

                    /*
                     * Parse out the hour and minute for the time.
                     */
                    hour = Integer.parseInt(dateTokenizer.nextToken());
                    min = Integer.parseInt(dateTokenizer.nextToken());

                    /*
                     * Create a new Gregorian Calendar representing the end
                     * time.
                     */
                    end = new GregorianCalendar(year, mon, day, hour, min);

                    /*
                     * Get the title of the meeting.
                     */
                    title = args[8];
                    try {
                        /*
                         * Attempt to add the meeting to the agenda. If the
                         * addition fails, print an error message.
                         */
                        if (myAgenda.add(attender, username, password, begin, end, title)) {
                            screen.println("New meeting added successfully!");
                        } else {
                            screen.println("Could not add a new meeting!");
                            screen.println("Please try a different username or check for time conflicts!");
                            System.exit(0);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            /*
             * Check to make sure there are the correct paramters for a query.
             */
            if (args[2].equals("query")) {
                if (args.length != 7) {
                    screen.println("Usage: Client <servername> <port> query <username> <password>");
                    screen.println("              <begin> <end>");
                    System.exit(0);
                } else {
                    /*
                     * Get the user name, password, start time, and end time
                     * from the command line.
                     */
                    username = args[3];
                    password = args[4];
                    start_time = args[5];
                    end_time = args[6];

                    /*
                     * Tokenize the start_time into date and time based on the
                     * '-' character.
                     */
                    paramTokenizer = new StringTokenizer(start_time, "-");
                    tempDate = paramTokenizer.nextToken();

                    /*
                     * Tokenize the date based upon the '/' character.
                     */
                    dateTokenizer = new StringTokenizer(tempDate, "/");
                    mon = Integer.parseInt(dateTokenizer.nextToken());
                    day = Integer.parseInt(dateTokenizer.nextToken());
                    year = Integer.parseInt(dateTokenizer.nextToken());
                    tempTime = paramTokenizer.nextToken();
                    dateTokenizer = new StringTokenizer(tempTime, ":");
                    hour = Integer.parseInt(dateTokenizer.nextToken());
                    min = Integer.parseInt(dateTokenizer.nextToken());

                    /*
                     * Create a new GregorianCalendar based on the parsed out
                     * start time values.
                     */
                    begin = new GregorianCalendar(year, mon, day, hour, min);

                    /*
                     * Tokenize the start_time into date and time based on the
                     * '-' character.
                     */
                    paramTokenizer = new StringTokenizer(end_time, "-");
                    tempDate = paramTokenizer.nextToken();

                    /*
                     * Tokenize the date based upon the '/' character.
                     */
                    dateTokenizer = new StringTokenizer(tempDate, "/");
                    mon = Integer.parseInt(dateTokenizer.nextToken());
                    day = Integer.parseInt(dateTokenizer.nextToken());
                    year = Integer.parseInt(dateTokenizer.nextToken());
                    tempTime = paramTokenizer.nextToken();
                    dateTokenizer = new StringTokenizer(tempTime, ":");
                    hour = Integer.parseInt(dateTokenizer.nextToken());
                    min = Integer.parseInt(dateTokenizer.nextToken());

                    /*
                     * Create a new GregorianCalendar based on the parsed out
                     * start time values.
                     */
                    end = new GregorianCalendar(year, mon, day, hour, min);
                    try {
                        /*
                         * Query the agenda. If the result is empty or null,
                         * print an appropriate error message. Otherwise, create
                         * an enumeration of the meetings and print each meeting
                         * out.
                         */
                        result = myAgenda.query(begin, end, username, password);
                        if (result != null || result.size() > 0) {
                            Enumeration meetings = result.elements();
                            while (meetings.hasMoreElements()) {
                                Meeting tempMeeting = (Meeting) meetings.nextElement();
                                screen.println(tempMeeting);
                            }
                        } else {
                            screen.println("No results!");
                            screen.println("Try a different user name, time interval,  or password!");
                            System.exit(0);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            /*
             * Check to make sure there are the correct paramters for a
             * deletion.
             */
            if (args[2].equals("delete")) {
                if (args.length != 6) {
                    screen.println("Usage: Client <servername> <port> delete <username> <password> ");
                    screen.println("              <meeting_id>");
                    System.exit(0);
                } else {
                    /*
                     * Get the username, password, and meeting id from the input
                     * command line arguments.
                     */
                    username = args[3];
                    password = args[4];
                    meetingid = Integer.parseInt(args[5]);
                    try {
                        /*
                         * Try to delete the meeting. If the deletion fails,
                         * print an appropriate error message.
                         */
                        if (myAgenda.delete(username, password, meetingid)) {
                            screen.println("Meeting: " + meetingid + " deleted successfully!");
                        } else {
                            screen.println("Could not delete meeting: " + meetingid + "!");
                            screen.println("Please try a different username or meetingid!");
                            System.exit(0);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            /*
             * Check to make sure there are the correct paramters for clearing.
             */
            if (args[2].equals("clear")) {
                if (args.length != 5) {
                    screen.println("Usage: Client <servername> <port> clear <username> <password>");
                    System.exit(0);
                } else {
                    /*
                     * Get the user name and password form the command line
                     * input parameters.
                     */
                    username = args[3];
                    password = args[4];
                    try {
                        /*
                         * Attempt to clear the agenda of all meetings if
                         * possible. Print appropriate eror messages if the
                         * clear fails.
                         */
                        if (myAgenda.clear(username, password)) {
                            screen.println("User: " + username + " has all meetings cleared!");
                        } else {
                            screen.println("Please try a different username!");
                            System.exit(0);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            /*
             * No input parameters were suppplied, so use the menu. Get the
             * hostname and port from the comand line.
             */
            hostname = args[0];
            portNumber = Integer.parseInt(args[1]);
            try {
                /*
                 * Connect to the agenda.
                 */
                myAgenda = (Agenda) Naming.lookup("rmi://" + hostname + ":" + portNumber + "/SSD8Exercise3");
            } catch (Exception e) {
                e.printStackTrace();
            }
            while (true) {
                /*
                 * Loop forever printing the menu.
                 */
                screen.println("************* Distributed Agenda ***************** ");
                screen.println("(1) Register a new user");
                screen.println("(2) Add a meeting");
                screen.println("(3) Query meetings");
                screen.println("(4) Delete a meeting");
                screen.println("(5) Clear meetings");
                screen.println("**************************************************");
                screen.println("Enter choice: ");
                /**
                 * Read the chosen option from the standard input.
                 */
                try {
                    choice = Integer.parseInt(keyboard.readLine());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                /**
                 * Get the username and password from the standard input and
                 * attempt to register, printing the appropriate error messages
                 * if the registration fails.
                 */
                if (choice == 1) {
                    try {
                        screen.println("Enter a username: ");
                        username = keyboard.readLine();
                        screen.println("Enter a password: ");
                        password = keyboard.readLine();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    try {
                        if (myAgenda.register(username, password)) {
                            screen.println("User: " + username + " registered successfully!");
                        } else {
                            screen.println("Could not register user: " + username + "!");
                            screen.println("Please try a different username!");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                /*
                 * Get the appropriate parameters for an addition, attempt to
                 * add the meeting to the agenda, and print appropriate error
                 * messages if the addition fails.
                 */
                if (choice == 2) {
                    try {
                        screen.println("Enter the name of the user in attendance: ");
                        attender = keyboard.readLine();
                        screen.println("Enter your username: ");
                        username = keyboard.readLine();
                        screen.println("Enter your password: ");
                        password = keyboard.readLine();
                        screen.println("Enter the meeting title: ");
                        title = keyboard.readLine();
                        screen.println("Enter the start time of the meeting");
                        screen.println("For example, 12/22/2003 12:45");
                        start_time = keyboard.readLine();
                        screen.println("Enter the end time of the meeting");
                        screen.println("For example, 12/22/2003 12:45");
                        end_time = keyboard.readLine();
                        paramTokenizer = new StringTokenizer(start_time, " ");
                        tempDate = paramTokenizer.nextToken();
                        dateTokenizer = new StringTokenizer(tempDate, "/");
                        mon = Integer.parseInt(dateTokenizer.nextToken());
                        day = Integer.parseInt(dateTokenizer.nextToken());
                        year = Integer.parseInt(dateTokenizer.nextToken());
                        tempTime = paramTokenizer.nextToken();
                        dateTokenizer = new StringTokenizer(tempTime, ":");
                        hour = Integer.parseInt(dateTokenizer.nextToken());
                        min = Integer.parseInt(dateTokenizer.nextToken());
                        begin = new GregorianCalendar(year, mon, day, hour, min);
                        paramTokenizer = new StringTokenizer(end_time, " ");
                        tempDate = paramTokenizer.nextToken();
                        dateTokenizer = new StringTokenizer(tempDate, "/");
                        mon = Integer.parseInt(dateTokenizer.nextToken());
                        day = Integer.parseInt(dateTokenizer.nextToken());
                        year = Integer.parseInt(dateTokenizer.nextToken());
                        tempTime = paramTokenizer.nextToken();
                        dateTokenizer = new StringTokenizer(tempTime, ":");
                        hour = Integer.parseInt(dateTokenizer.nextToken());
                        min = Integer.parseInt(dateTokenizer.nextToken());
                        end = new GregorianCalendar(year, mon, day, hour, min);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    try {
                        if (myAgenda.add(attender, username, password, begin, end, title)) {
                            screen.println("New meeting added successfully!");
                        } else {
                            screen.println("Could not add a new meeting!");
                            screen.println("Please try a different username or check for time conflicts!");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                /*
                 * Get the necessary inputs for a query and print the results.
                 */
                if (choice == 3) {
                    try {

                        screen.println("Enter your username: ");
                        username = keyboard.readLine();
                        screen.println("Enter your password: ");
                        password = keyboard.readLine();
                        screen.println("Enter the start time");
                        screen.println("For example, 12/22/2003 12:45");
                        start_time = keyboard.readLine();
                        screen.println("Enter the end time");
                        screen.println("For example, 12/22/2003 12:45");
                        end_time = keyboard.readLine();
                        paramTokenizer = new StringTokenizer(start_time, " ");
                        tempDate = paramTokenizer.nextToken();
                        dateTokenizer = new StringTokenizer(tempDate, "/");
                        mon = Integer.parseInt(dateTokenizer.nextToken());
                        day = Integer.parseInt(dateTokenizer.nextToken());
                        year = Integer.parseInt(dateTokenizer.nextToken());
                        tempTime = paramTokenizer.nextToken();
                        dateTokenizer = new StringTokenizer(tempTime, ":");
                        hour = Integer.parseInt(dateTokenizer.nextToken());
                        min = Integer.parseInt(dateTokenizer.nextToken());
                        begin = new GregorianCalendar(year, mon, day, hour, min);
                        paramTokenizer = new StringTokenizer(end_time, " ");
                        tempDate = paramTokenizer.nextToken();
                        dateTokenizer = new StringTokenizer(tempDate, "/");
                        mon = Integer.parseInt(dateTokenizer.nextToken());
                        day = Integer.parseInt(dateTokenizer.nextToken());
                        year = Integer.parseInt(dateTokenizer.nextToken());
                        tempTime = paramTokenizer.nextToken();
                        dateTokenizer = new StringTokenizer(tempTime, ":");
                        hour = Integer.parseInt(dateTokenizer.nextToken());
                        min = Integer.parseInt(dateTokenizer.nextToken());
                        end = new GregorianCalendar(year, mon, day, hour, min);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    try {
                        result = myAgenda.query(begin, end, username, password);
                        if (result != null || result.size() > 0) {
                            Enumeration meetings = result.elements();
                            while (meetings.hasMoreElements()) {
                                Meeting tempMeeting = (Meeting) meetings.nextElement();
                                screen.println(tempMeeting);
                            }
                        } else {
                            screen.println("No results!");
                            screen.println("Try a different user name or password!");
                            System.exit(0);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                /*
                 * Get the necessary inputs for a deletion, print a confirmation
                 * or error report upoin failure.
                 */
                if (choice == 4) {
                    try {
                        screen.println("Please enter your username: ");
                        username = keyboard.readLine();
                        screen.println("Please enter your password: ");
                        password = keyboard.readLine();
                        screen.println("Please enter the meeting id: ");
                        meetingid = Integer.parseInt(keyboard.readLine());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    try {
                        if (myAgenda.delete(username, password, meetingid)) {
                            screen.println("Meeting: " + meetingid + " deleted successfully!");
                        } else {
                            screen.println("Could not delete meeting: " + meetingid + "!");
                            screen.println("Please try a different username or meetingid!");
                            System.exit(0);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                /*
                 * Get the necessary inputs for a clear, print a confirmation or
                 * error report upon failure.
                 */
                if (choice == 5) {

                    try {
                        screen.println("Please enter your username: ");
                        username = keyboard.readLine();
                        screen.println("Please enter your password: ");
                        password = keyboard.readLine();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    try {
                        if (myAgenda.clear(username, password)) {
                            screen.println("User: " + username + " has all meetings cleared!");
                        } else {
                            screen.println("Please try a different username!");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}

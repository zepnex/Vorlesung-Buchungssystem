package edu.kit.informatik.models;


import edu.kit.informatik.util.List;

/***Class that models an event
 *
 * @author unyrg
 * @version 0.0.1
 */
public class Event {
    private final LectureHall room;
    private final int date;
    private List<Person> attandees;
    private List<Person> security;
    private int eventID;
    private final Person head;
    private CovidVerification regulation;
    private static final int MAX_DAY = 364;

    /***contructor
     *
     * @param head lecturer of the event
     * @param room lecture hall the event takes place
     * @param regulation regulation of the event 2G/3G
     * @param date date of the event
     * @param attendees the users that will attend
     * @param eventID id of the event
     */
    public Event(Person head, LectureHall room, String regulation, int date, List<Person> attendees, int eventID) {
        this.room = room;
        this.date = date;
        this.attandees = attendees;
        this.eventID = eventID;
        this.head = head;
        setRegulation(regulation);
    }

    /***getter
     *
     * @return gets the security list
     */
    public List<Person> getSecurityList() {
        return security;
    }

    /***setter
     *
     * @param security sets the security list
     */
    public void setSecurityList(List<Person> security) {
        this.security = security;
    }

    /***getter
     *
     * @return return the hall the event is held in
     */
    public LectureHall getRoom() {
        return room;
    }

    /***getter
     *
     * @return all attandees of the event
     */
    public List<Person> getAttandees() {
        return attandees;
    }

    /***sets the regulation for the event
     *
     * @param regulation if 2G or 3G
     */
    private void setRegulation(String regulation) {
        if (regulation.equals("2G")) {
            this.regulation = new CovidVerification(MAX_DAY, "BOTH");
        } else if (regulation.equals("3G")) {
            this.regulation = new CovidVerification(MAX_DAY, "TESTED");
        } else
            this.regulation = null;
    }

    /***getter
     *
     * @return returns the covid regulation for the event
     */
    public CovidVerification getRegulation() {
        return regulation;
    }

    /***getter
     *
     * @return date of the event
     */
    public int getDate() {
        return date;
    }

    /***getter
     *
     * @return lecturer of the event
     */
    public Person getHead() {
        return head;
    }
}

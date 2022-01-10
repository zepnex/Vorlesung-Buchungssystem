package edu.kit.informatik.controller;


import edu.kit.informatik.models.CovidVerification;
import edu.kit.informatik.models.Event;
import edu.kit.informatik.models.LectureHall;
import edu.kit.informatik.models.Person;
import edu.kit.informatik.models.Role;
import edu.kit.informatik.util.List;

/***Class with all the different methods to perform the command
 *
 * @author unyrg
 * @version 0.0.1
 */
public class Commands {
    private static final int RECOVER_PROTECTION = 180;
    private static final int TRACKING = 14;

    /*** Adds a person to the list.
     *
     * @param list list of all users
     * @param firstName first name
     * @param lastName last name
     * @param role role of the person
     */
    public void addPerson(List<Person> list, String firstName, String lastName, Role role) {
        Person person;
        list.push(person = new Person(firstName, lastName, list.length(), role));
        System.out.println(person.getId());
    }

    /*** Add a Covid19 certificate to the person
     *
     * @param list list of all users
     * @param index ID of the person
     * @param verification type of verification
     * @param currentDate current date
     */
    public void addCertificate(List<Person> list, int index, CovidVerification verification, int currentDate) {

        Person person = list.get(index);
        person.setVerif(verification);
        System.out.println("OK");

    }

    /***Prints all information of a specific person
     *
     * @param list list of all users
     * @param id ID of the person
     * @param currentDate current date
     */
    public void printPerson(List<Person> list, int id, int currentDate) {
        Person person = list.get(id);
        System.out.printf("%d, %s %s, %s, %s%n",
                person.getId(), person.getFirstName(), person.getLastname(),
                person.getRole().toString().toLowerCase(),
                person.getType(currentDate).toType().toString().toLowerCase().replace("_", " "));
    }

    /*** Print a list of people with a specific role
     *
     * @param list list of all users
     * @param role the role you want to print out
     * @param currentDate current date
     */
    public void printPeople(List<Person> list, Role role, int currentDate) {
        boolean foundType = false;
        for (int i = 0; i < list.length(); i++) {
            if (list.get(i).getRole() == role) {
                printPerson(list, i, currentDate);
                foundType = true;
            }
        }
        if (Boolean.FALSE.equals(foundType)) System.out.printf("No %s in the system%n", role.toString().toLowerCase());
    }

    /***Adds a new event to the event list
     *
     * @param head the person who is holding the event
     * @param lectureHall where it takes place
     * @param capacity ammount of people that can take part
     * @param regulation 2G or 3G thanks corona for nothing
     * @param date current date
     * @param eventID ID of the event
     * @return returns a new event for the list
     */
    public Event addEvent(
            Person head, String lectureHall, int capacity, String regulation, int date, int eventID) {
        List<Person> attendees = new List<>();
        System.out.println(eventID);
        return new Event(head, new LectureHall(capacity, lectureHall), regulation, date, attendees, eventID);

    }

    /*** increase the amount of security for an event
     *
     * @param event the event
     * @param security the person who is a security guy
     */
    public void increase(Event event, Person security) {
        List<Person> securityList;
        if (event.getSecurityList() != null) {
            securityList = event.getSecurityList();
        } else {
            securityList = new List<>();
        }
        if (Boolean.TRUE.equals(securityList.contains(security))) {
            System.out.println("Could not add security");
        } else {
            securityList.push(security);
            event.setSecurityList(securityList);
            System.out.println("OK");
        }
    }

    /***Books a spot at an event the user wants to take part
     *
     * @param event the event he wants to visit
     * @param person the person
     * @param currentDate current date
     */
    public void bookSpot(Event event, Person person, int currentDate) {
        int roomCapacity = event.getRoom().getCapacity();
        List<Person> attendees = event.getAttandees();
        List<Person> security = event.getSecurityList();
        if (roomCapacity >= (attendees.length() + security.length() + 1)) {
            CovidVerification.Verifications personVerify = person.getType(event.getDate()).toType();
            CovidVerification.Verifications eventVerify = event.getRegulation().getType();
            switch (eventVerify) {
                case BOTH:
                    if (check2G(person, event, personVerify, currentDate) && !event.getAttandees().contains(person)) {
                        event.getAttandees().push(person);
                        System.out.printf("%o spot(s) left%n",
                                (roomCapacity - (attendees.length() + security.length() + 1)));

                    } else {
                        System.out.println("Could not book spot");
                    }
                    break;
                case TESTED:
                    if (!personVerify.toString().equals("TESTED") && !event.getAttandees().contains(person)) {
                        if (check2G(person, event, personVerify, currentDate)) {
                            event.getAttandees().push(person);
                            System.out.printf("%o spot(s) left%n",
                                    (roomCapacity - (attendees.length() + security.length() + 1)));
                        } else {
                            System.out.println("Could not book spot");
                        }
                    } else {
                        if (event.getDate() == person.getType(event.getDate()).getDate()) {
                            event.getAttandees().push(person);
                            int leftCap = roomCapacity - (attendees.length() + security.length() + 1);
                            System.out.printf("%s spot(s) left%n",
                                    leftCap);
                        } else {
                            System.out.println("Could not book spot");
                        }
                    }
            }
        } else {
            System.out.println("Could not book spot");
        }
    }

    /***checking if the person has the valid Covid19 verification for the event
     *
     * @param person the person
     * @param event the event he wants to visit
     * @param personVerify his verification type
     * @param currentDate current date
     * @return returns a boolean that indicates if he has the right verifications
     */
    public boolean check2G(Person person, Event event, CovidVerification.Verifications personVerify, int currentDate) {
        if (personVerify.toString().equals("VACCINATED")) {
            return true;
        } else if (personVerify.toString().equals("RECOVERED")) {
            return event.getDate() <= person.getType(currentDate).getDate() + RECOVER_PROTECTION;
        } else {
            return false;
        }
    }

    /***prints every person that had the same event as the person who got infected with corona in the last 14 days
     *
     * @param person the infected person
     * @param eventList list of all possible events
     * @param currentDate current date
     * @return list with person that took part at the same event as him
     */
    public List<Person> reportCase(Person person, List<Event> eventList, int currentDate) {
        List<Person> attendees = new List<>();
        List<Event> attendedEvents = new List<>();


        for (int i = 0; i < eventList.length(); i++) {
            if (currentDate - TRACKING < eventList.get(i).getDate()) {
                if ((eventList.get(i).getHead().getId() == person.getId()) || eventList.get(i).getAttandees().contains(person) || eventList.get(i).getSecurityList().contains(person)) {
                    attendedEvents.push(eventList.get(i));
                }
            }
        }
        for (int i = 0; i < attendedEvents.length(); i++) {
            attendees = addToList(attendedEvents.get(i).getAttandees(), attendedEvents.get(i).getSecurityList());
            attendees.push(eventList.get(i).getHead());
        }
        return sortByID(attendees);
    }

    /***helper methode to add every person to a list that took part at an event
     * with the infected person
     *
     * @param attendees all visitors of a specific event
     * @param security all security guards of a specific event
     * @return List of attenders, security guards and the lecturer combined
     */
    private List<Person> addToList(List<Person> attendees, List<Person> security) {
        List<Person> list = new List<>();
        for (int i = 0; i < attendees.length(); i++) {
            list.push(attendees.get(i));
        }
        for (int i = 0; i < security.length(); i++) {
            list.push(security.get(i));
        }
        return list;
    }

    /**
     * Sorts a list by ID
     *
     * @param list the list you want to sort
     * @return sorted list
     */
    public List<Person> sortByID(List<Person> list) {
        for (int i = 0; i < list.length(); i++) {
            if (list.get(i + 1) != null && (list.get(i).getId() > list.get(i + 1).getId())) {
                list.swap(i);
            }
        }

        return list;
    }

    /**
     * Prints all possible infected people to the console and how often they attended an event with
     * the infected perosn
     *
     * @param list        sorted list
     * @param person      infected person
     * @param currentDate current date
     */
    public void printCases(List<Person> list, Person person, int currentDate) {
        for (int i = 0; i < list.length(); i++) {
            Person currentPers = list.get(i);
            if (currentPers.getId() != person.getId()) {
                int count = 0;
                for (int j = 0; j < list.length(); j++) {
                    if (person.getId() == list.get(j).getId()) {
                        count++;
                    }
                }
                if (count > 0) {
                    System.out.printf("%s, %s %s, %s, %s [%s]%n", currentPers.getId(), currentPers.getFirstName(), currentPers.getLastname(),
                            currentPers.getRole().toString().toLowerCase(),
                            currentPers.getType(currentDate).toType().toString().toLowerCase().replace("_", " "), count);
                }
            }
        }
    }
}

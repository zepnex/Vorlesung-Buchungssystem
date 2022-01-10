package edu.kit.informatik.controller;


import edu.kit.informatik.models.CovidVerification;
import edu.kit.informatik.models.Event;
import edu.kit.informatik.models.Person;
import edu.kit.informatik.models.Role;
import edu.kit.informatik.util.List;

/***Class that processes your command and calls
 * the right methods to perform the command correctly
 *
 * @author unyrg
 * @version 0.0.1
 */
public class Controller {
    private Boolean running = true;
    private Commands commands = new Commands();
    private List<Person> persons = new List<>();
    private List<Event> eventList = new List<>();
    private CovidVerification verification;
    private int date = 0;
    private String params;
    private String[] args;

    /*** Performs the command you want to use
     *
     * @param input command you want to perform
     */
    public void perform(String input) {
        String cmd = input.split(" ")[0];
        if (input.split(" ").length > 1) {
            params = input.split(" ")[1];
            args = params.split(";");
        }
        switch (cmd) {
            case "add-person":
                commands.addPerson(
                        persons, args[1], args[2], Role.valueOf(args[0].toUpperCase()));
                break;
            case "set-date":
                this.date = Integer.parseInt(params);
                System.out.println("OK");
                break;
            case "add-certificate":
                commands.addCertificate(
                        persons, Integer.parseInt(args[0]), new CovidVerification(
                                Integer.parseInt(args[2]), args[1].toUpperCase()), date);
                break;
            case "print-person":
                commands.printPerson(persons, Integer.parseInt(params), date);
                break;
            case "print-people":
                commands.printPeople(persons, Role.valueOf(args[0].toUpperCase()), date);
                break;
            case "add-event":
                eventList.push(commands.addEvent(
                        persons.get(Integer.parseInt(args[0])),
                        args[1], Integer.parseInt(args[2]), args[3], Integer.parseInt(args[4]), eventList.length()));
                break;
            case "increase-security":
                commands.increase(eventList.get(Integer.parseInt(args[0])), persons.get(Integer.parseInt(args[1])));
                break;
            case "book-spot":
                commands.bookSpot(eventList.get(
                        Integer.parseInt(args[0])), persons.get(Integer.parseInt(args[1])), date);
                break;
            case "report-case":
                List<Person> list;
                list = commands.reportCase(persons.get(Integer.parseInt(params)), eventList, date);
                commands.printCases(list, persons.get(Integer.parseInt(params)), date);
                break;
            case "quit":
                running = false;
                break;
            default:
                System.out.println("Command doesn't exist");
        }
    }

    /***Check if program should run
     *
     * @return returns boolean that tells if the program should run or not
     */
    public Boolean getRunning() {
        return running;
    }
}

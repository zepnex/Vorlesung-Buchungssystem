package edu.kit.informatik.models;


import edu.kit.informatik.util.List;

/*** class that models a perosn
 *
 * @author unyrg
 * @version 0.0.1
 */
public class Person {

    private String firstName;
    private String lastname;
    private int id;
    private Role role;
    private List<CovidVerification> verifications = new List<>();
    private CovidVerification.Verifications verify;

    /*** contructor
     *
     * @param firstName first name
     * @param lastname last name
     * @param id ID of user
     * @param role role of user
     */
    public Person(String firstName, String lastname, int id, Role role) {
        this.firstName = firstName;
        this.lastname = lastname;
        this.id = id;
        this.role = role;
    }

    /***setter
     *
     * @param verify sets the certificate of the user
     */
    public void setVerif(CovidVerification verify) {
        verifications.push(verify);
    }

    /**getter*
     *
     * @return id of the user
     */
    public int getId() {
        return id;
    }

    /***getter
     *
     * @return role of user
     */
    public Role getRole() {
        return role;
    }

    /***getter
     *
     * @return first name of user
     */
    public String getFirstName() {
        return firstName;
    }

    /***getter
     *
     * @return last name of user
     */
    public String getLastname() {
        return lastname;
    }

    /***getter
     *
     * @param date current date
     * @return longets valid certificate
     */
    public CovidVerification getType(int date) {
        if (verifications.length() != 0) {
            if (verifications.length() > 1) {
                sortList(this.verifications);
            }
            for (int i = 0; i < verifications.length(); i++) {
                if (checkDate(verifications.get(i).getDate(), date)) {
                    return verifications.get(i);
                }
            }
            setVerif(new CovidVerification(0, "NO_CERTIFICATE"));
            return verifications.get(verifications.length() - 1);
        } else {
            setVerif(new CovidVerification(0, "NO_CERTIFICATE"));
            return verifications.get(verifications.length() - 1);
        }
    }

    /***Sorts a specific list
     *
     * @param verifications the list that should be sorted
     */
    void sortList(List<CovidVerification> verifications) {
        List<CovidVerification> list = new List<>();
        for (int i = 0; i < verifications.length(); i++) {
            if (verifications.get(i).getType().toString().equals("VACCINATED")) {
                list.push(verifications.get(i));
                verifications.remove(i);
                i--;
            }
        }
        for (int i = 0; i < verifications.length(); i++) {
            if (verifications.get(i).getType().toString().equals("RECOVERED")) {
                list.push(verifications.get(i));
                verifications.remove(i);
                i--;
            }
        }
        for (int i = 0; i < verifications.length(); i++) {
            if (verifications.get(i).getType().toString().equals("TESTED")) {
                list.push(verifications.get(i));
                verifications.remove(i);
                i--;
            }
        }
        for (int i = 0; i < verifications.length(); i++) {
            list.push(verifications.get(i));
            verifications.remove(i);
            i--;
        }
        for (int i = 0; i < list.length(); i++) {
            if (list.get(i + 1) != null) {
                if (!list.get(i).getType().equals(verify.VACCINATED)
                        && !list.get(i).getType().equals(verify.RECOVERED)) {
                    if (list.get(i).getDate() > list.get(i + 1).getDate()) {
                        list.swap(i);
                    }
                }
            }
        }

        this.verifications = list;
    }

    /***checks date, so there is no certificate from the future
     *
     * @param verifyDate date of certificate
     * @param currentDate current date
     * @return boolean if certificate is from the future
     */
    boolean checkDate(int verifyDate, int currentDate) {
        return verifyDate <= currentDate;
    }
}
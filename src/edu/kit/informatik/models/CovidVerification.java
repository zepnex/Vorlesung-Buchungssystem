package edu.kit.informatik.models;

/***Class that models a Covid19 certificate
 *
 * @author unyrg
 * @version 0.0.1
 */
public class CovidVerification {
    /***enum that reflects the different verification types
     */
    public enum Verifications {
        VACCINATED, RECOVERED, TESTED, BOTH, NO_CERTIFICATE

    }

    private final int date;
    private final CovidVerification.Verifications type;

    /*** Constructor of CovidVerification class
     *
     * @param verifyDate date the certificate is active
     * @param verifyType what type of certificate
     */
    public CovidVerification(int verifyDate, String verifyType) {
        this.date = verifyDate;
        type = Verifications.valueOf(verifyType);
    }

    /***getter
     *
     * @return type of certificate
     */
    public Verifications getType() {
        return type;
    }

    /***date when the certificate was created
     *
     * @return date of certificate creation
     */
    public int getDate() {
        return date;
    }

    /***Returns type of certificate
     *
     * @return certificate type
     */
    public CovidVerification.Verifications toType() {
        return type;
    }
}

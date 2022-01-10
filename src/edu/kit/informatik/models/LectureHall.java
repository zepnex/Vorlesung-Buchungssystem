package edu.kit.informatik.models;

/***Class that models a lecture hall
 *
 * @author unyrg
 * @version 0.0.1
 */
public class LectureHall {
    private int capacity;
    private String lectureHall;

    /*** constructor
     *
     * @param capacity
     * @param lectureHall
     */
    public LectureHall(int capacity, String lectureHall) {
        this.capacity = capacity;
        this.lectureHall = lectureHall;
    }

    /***getter
     *
     * @return capacity of the hall
     */
    public int getCapacity() {
        return capacity;
    }
}

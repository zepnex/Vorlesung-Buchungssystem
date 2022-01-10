package edu.kit.informatik.Main;


import edu.kit.informatik.controller.Controller;

import java.util.Scanner;

/***Here starts the programm
 *
 * @author unyrg
 * @version 0.0.1
 */
public class Main {
    /*** Main methode, from here the journey starts
     *
     * @param args args u can pass when you start the programm
     */
    public static void main(String[] args) {
        Controller controller = new Controller();
        while (Boolean.TRUE.equals(controller.getRunning())) {
            Scanner scan = new Scanner(System.in);
            controller.perform(scan.nextLine());

        }
    }
}

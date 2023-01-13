/**
 * Checkers game that contains 2 game modes
 *
 * date           20210419
 *
 * @filename      CheckersMain.java
 * @author sgu, azheng, szhan, mahmed
 * @version 1.0
 * @see assignment 4.3
 */
package ca.on.tdsb.student.checkers;

public class CheckersMain {

    public static void main(String[] args) {

        CheckersPoint gamePoint = new CheckersPoint();

        CheckersIntroScreen introScreen = new CheckersIntroScreen();
        introScreen.introVisible().setVisible(true);

    }
}
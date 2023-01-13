/**
 *
 * The class TimeControl provides a one-second metronome that is the core of the timer.
 *
 * @author azheng
 */
package ca.on.tdsb.student.checkers;

import java.util.logging.*;

// src: https://stackoverflow.com/questions/11892740/how-do-i-execute-multiple-processes-simultaneously-in-java
public class TimeControl extends Thread {

    private boolean active;
    private SecondsInfo secondsInfo;

    public TimeControl() {
        active = false;
    }

    // src: https://www.tutorialspoint.com/java/java_interfaces.htm and https://www.w3schools.com/java/java_interface.asp
    public static interface SecondsInfo {

        public void oneSecond();
    }

    @Override
    public void run() {
        try {
            while (active) {
                Thread.sleep(1000);
                secondsInfo.oneSecond();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(CheckersGameBoard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void play() {
        active = true;
    }

    public void pause() {
        active = false;
    }

    public boolean checkActivity() {
        return active;
    }

    public void setSecondsInfo(SecondsInfo secondsInfo) {
        this.secondsInfo = secondsInfo;
    }
}
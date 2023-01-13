/**
 * Implementation of the timer, stores the total amount of seconds left.
 * @author  azheng
 */
package ca.on.tdsb.student.checkers;

import ca.on.tdsb.student.checkers.TimeControl.SecondsInfo;

public class SetTimer implements SecondsInfo {

    private final TimeControl timer;
    long[] total;
    static int player = 0;
    private SecondsInfo secondsInfo;

    public SetTimer(long time) {
        timer = new TimeControl();
        timer.setSecondsInfo(this);
        total = new long[2];
        total[0] = time / 1000;
        total[1] = time / 1000;
    }

    @Override
    public void oneSecond() {
        total[player]--;
        if (total[player] < 0) {
            timer.pause();
        }
        secondsInfo.oneSecond();
    }

    public void play() {
        timer.play();
        timer.start();
    }

    public void pause() {
        timer.pause();
    }

    public boolean checkActivity() {
        return timer.checkActivity();
    }

    public static void flip() {
        player++;
        player = player % 2;
    }

    public long[] getPlayerTimes() {
        long[] ret = {total[0], total[1]};
        return ret;
    }

    public void reset(long millis) {
        total[0] = millis / 1000;
        total[1] = millis / 1000;
        player = 0;
        timer.pause();
    }

    public static int player() {
        return player;
    }

    public void setSecondsInfo(SecondsInfo secondsInfo) {
        this.secondsInfo = secondsInfo;
    }
}
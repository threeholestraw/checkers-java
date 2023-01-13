/**
 * Adds the timer to the game board
 * author@   azheng, mahmed
 */
package ca.on.tdsb.student.checkers;

import java.awt.*;
import java.io.IOException;
import java.util.logging.*;
import javax.swing.*;
import ca.on.tdsb.student.checkers.TimeControl.SecondsInfo;
import javax.sound.sampled.LineUnavailableException;

public class CheckersTime implements SecondsInfo {

    CheckersGameBoard startGame;
    static SetTimer gameClock;
    CheckersResultScreen timeResult;
    JFrame gameTimeChoice = new JFrame();
    JPanel gameTimePanel = new JPanel();
    JButton timeStart = new JButton();
    Integer[] gameTimeOption = {5, 10, 15, 20};
    JComboBox<Integer> gameTimeChooser = new JComboBox<>(gameTimeOption);
    long gameTime;
    static boolean tied = false;

    public void enterGameTime() throws LineUnavailableException {

        gameTimeChoice.setTitle("Player game time");
        gameTimeChoice.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        gameTimeChoice.setResizable(false);
        gameTimePanel.setLayout(new GridLayout(1, 3, 10, 10));

        gameTimePanel.add(new JLabel("Time (min) : "));
        gameTimePanel.add(gameTimeChooser);

        timeStart.setText("start");
        timeStart.setForeground(Color.WHITE);
        timeStart.setBackground(new Color(197, 0, 0));
        gameTimePanel.add(timeStart);

        gameTimePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        gameTimeChoice.add(gameTimePanel);
        gameTimeChoice.pack();
        gameTimeChoice.setVisible(true);

        timeResult = new CheckersResultScreen();

        timeStart.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    CheckersEvent.playerOneWin = 0;
                    CheckersEvent.playerTwoWin = 0;
                    gameTime = ((int) gameTimeChooser.getSelectedItem()) * 60000;
                    gameTimeChoice.setVisible(false);
                    setGameTime();
                    startGame = new CheckersGameBoard();
                    CheckersGameBoard.colourAssign = 0;
                } catch (IOException ex) {
                    Logger.getLogger(CheckersTime.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    void setGameTime() {
        gameClock = new SetTimer(gameTime);
        gameClock.setSecondsInfo(this);
        gameClock.play();
    }

    // azheng
    @Override
    public void oneSecond() {
        try {
            if (!tied) {
                if (!gameClock.checkActivity()) {
                    SetTimer.player = 0;
                    if (gameClock.getPlayerTimes()[0] <= 0) {
                        checkTimeWinner(true, true);
                    } else if (gameClock.getPlayerTimes()[1] <= 0) {
                        checkTimeWinner(true, false);
                    }
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(CheckersGameBoard.class.getName()).log(Level.SEVERE, null, ex);
        }
        CheckersGameBoard.timer1.setText(setTime(gameClock.getPlayerTimes()[0]));
        CheckersGameBoard.timer2.setText(setTime(gameClock.getPlayerTimes()[1]));
    }

    public String setTime(long time) {
        long[] info = timeCalc(time);
        String result = String.format("%d:%02d", info[0], info[1]);
        return result;
    }

    private long[] timeCalc(long time) {
        long min = time / 60;
        long sec = time - (60 * min);
        long[] ret = {min, sec};
        return ret;
    }

    private void checkTimeWinner(Boolean timeOut, Boolean player1) throws IOException {
        if (timeOut) {
            if (player1) {
                startGame.setVisible(false);
                CheckersGameBoard.gameStart = false;
                CheckersPoint.playerPoints(CheckersGameBoard.playerName2, CheckersEvent.playerTwoWin, CheckersEvent.playerOneWin, 1, 0, 0);
                CheckersPoint.playerPoints(CheckersGameBoard.playerName1, CheckersEvent.playerOneWin, CheckersEvent.playerTwoWin, 0, 1, 0);
                CheckersSFX.playEnd();
                timeResult.winnerScreen(CheckersGameBoard.playerName2, CheckersGameBoard.playerName1);
                startGame.instructionMain.instruction.setVisible(false);
                gameClock.pause();
            } else {
                startGame.setVisible(false);
                CheckersGameBoard.gameStart = false;
                CheckersPoint.playerPoints(CheckersGameBoard.playerName1, CheckersEvent.playerOneWin, CheckersEvent.playerTwoWin, 1, 0, 0);
                CheckersPoint.playerPoints(CheckersGameBoard.playerName2, CheckersEvent.playerTwoWin, CheckersEvent.playerTwoWin, 0, 1, 0);
                CheckersSFX.playEnd();
                timeResult.winnerScreen(CheckersGameBoard.playerName1, CheckersGameBoard.playerName2);
                startGame.instructionMain.instruction.setVisible(false);
                gameClock.pause();
            }
        }
    }

    public static void tiePause() {
        gameClock.pause();
        tied = true;
    }
}
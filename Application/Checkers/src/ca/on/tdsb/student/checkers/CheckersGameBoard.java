/**
 * GUI for the main game screen and methods to check the winner
 * author@   mahmed
 */
package ca.on.tdsb.student.checkers;

import static ca.on.tdsb.student.checkers.CheckersTime.gameClock;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CheckersGameBoard extends JFrame {

    CheckersInstructionScreen instructionMain = new CheckersInstructionScreen();
    CheckersPoint gamePoint = new CheckersPoint();
    CheckersEvent checkersEvent = new CheckersEvent(this);
    CheckersResultScreen gameWinner = new CheckersResultScreen();

    JPanel bottomRow = new JPanel();
    JPanel board = new JPanel();
    JTextArea txtPlayer1Score = new JTextArea();
    JTextArea txtPlayer2Score = new JTextArea();
    static JTextField timer1 = new JTextField();
    static JTextField timer2 = new JTextField();
    static JButton undoMove = new JButton();
    JButton gameReturn = new JButton();
    static JButton gameInstruction = new JButton();
    static boolean competitive, gameStart = false, instructionShown = false;
    static String player1Colour, player2Colour, playerName1, playerName2;
    static int colourAssign, spaceLabelNum = 0;

    JLabel[][] arrSpace = new JLabel[8][8];

    public CheckersGameBoard() throws IOException {
        setTitle("Checkers");
        setSize(470, 550);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        FlowLayout gameLayout = new FlowLayout();
        gameLayout.setHgap(10);
        setLayout(gameLayout);
        colourAssign = 0;
        txtPlayer1Score.setText(" " + playerName1 + "\n Pieces Left:\n Pieces Won:");
        txtPlayer1Score.setFont(new java.awt.Font("Tahoma", 0, 12));
        txtPlayer1Score.setPreferredSize(new Dimension(180, 50));
        txtPlayer1Score.setEditable(false);
        txtPlayer2Score.setText(" " + playerName2 + "\n Pieces Left:\n Pieces Won:");
        txtPlayer2Score.setFont(new java.awt.Font("Tahoma", 0, 12));
        txtPlayer2Score.setPreferredSize(new Dimension(180, 50));
        txtPlayer2Score.setEditable(false);
        add(txtPlayer1Score);
        add(txtPlayer2Score);

        board.setLayout(new GridLayout(8, 8, 0, 0));
        for (int row = 0; row <= 7; row++) {
            for (int col = 0; col <= 7; col++) {
                arrSpace[row][col] = new JLabel();
                arrSpace[row][col].setName(Integer.toString(spaceLabelNum));
                arrSpace[row][col].setOpaque(true);
                if (colourAssign % 2 == 0) {
                    arrSpace[row][col].setBackground(new Color(197, 0, 0));
                } else {
                    arrSpace[row][col].setBackground(Color.BLACK);
                }

                arrSpace[row][col].setPreferredSize(new Dimension(50, 50));
                arrSpace[row][col].setOpaque(true);
                colourAssign++;
                spaceLabelNum++;
                board.add(arrSpace[row][col]);

                // src: https://stackoverflow.com/questions/24690012/detect-which-jlabel-is-clicked
                // Adding mouseclicks for all 64 squares
                arrSpace[row][col].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        try {
                            checkersEvent.mouseClicked(e);
                        } catch (IOException ex) {
                            Logger.getLogger(CheckersGameBoard.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
            }
            colourAssign++;
        }

        add(board);
        checkersDisplay();

        bottomRow.setLayout(new GridLayout(1, 4, 4, 4));

        gameInstruction.setPreferredSize(new Dimension(30, 35));
        gameInstruction.setOpaque(true);
        gameInstruction.setIcon(new ImageIcon(CheckersResource.getRes("question.png")));
        gameInstruction.setFocusable(false);
        gameInstruction.setEnabled(true);
        bottomRow.add(gameInstruction);

        timer1.setEditable(false);
        timer1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        timer1.setBackground(Color.WHITE);
        timer2.setEditable(false);
        timer2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        timer2.setBackground(Color.WHITE);

        undoMove.setText("Undo Move");
        undoMove.setForeground(Color.WHITE);
        undoMove.setBackground(new Color(197, 0, 0));
        undoMove.setFocusable(false);

        if (!competitive) {
            bottomRow.add(undoMove);
        } else {
            bottomRow.add(timer1);
            bottomRow.add(timer2);
        }

        gameReturn.setText("Tie Game");
        gameReturn.setForeground(Color.WHITE);
        gameReturn.setBackground(new Color(197, 0, 0));
        gameReturn.setFocusable(false);
        gameReturn.setEnabled(true);

        bottomRow.add(gameReturn);
        add(bottomRow);

        setVisible(true);

        gameReturn.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tieScreen();
                gameReturn.setEnabled(false);
            }
        });

        undoMove.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkersEvent.undo();
            }
        });

        gameInstruction.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gameInstruction.setEnabled(false);
                gameStart = true;
                if (!instructionShown) {                    
                    instructionMain.instruction.setVisible(true);
                    instructionShown = true;
                }
            }
        });
    }

    public final void checkersDisplay() throws IOException {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                switch (checkersEvent.checkers[row][col]) {
                    case 1:
                        CheckersResource.makePiece(arrSpace[row][col], player1Colour, false);
                        break;
                    case 2:
                        CheckersResource.makePiece(arrSpace[row][col], player2Colour, false);
                        break;
                    case 3:
                        CheckersResource.makePiece(arrSpace[row][col], player1Colour, true);
                        break;
                    case 4:
                        CheckersResource.makePiece(arrSpace[row][col], player2Colour, true);
                        break;
                    default:
                        arrSpace[row][col].setIcon(null);
                        break;
                }

                // https://stackoverflow.com/questions/8112657/drawing-border-around-jlabel-when-selected-like-the-buttons
                switch (checkersEvent.possibleSpace[row][col]) {
                    case 1:
                        arrSpace[row][col].setBorder(BorderFactory.createLineBorder(Color.orange));
                        break;
                    case 2:
                        arrSpace[row][col].setBorder(BorderFactory.createLineBorder(Color.MAGENTA));
                        break;
                    case 0:
                        arrSpace[row][col].setBorder(null);
                        break;
                    default:
                        break;
                }
            }
        }

        if (CheckersEvent.whichPlayer) {
            txtPlayer1Score.setBorder(BorderFactory.createLineBorder(Color.orange));
            txtPlayer2Score.setBorder(BorderFactory.createEmptyBorder());
            if (checkersEvent.giveUndoCount() >= 2) {
                checkersEvent.setUndoCount(0);
                CheckersEvent.whichPlayer = !CheckersEvent.whichPlayer;
                txtPlayer2Score.setBorder(BorderFactory.createLineBorder(Color.orange));
                txtPlayer1Score.setBorder(BorderFactory.createEmptyBorder());
            }
            if (competitive) {
                if (SetTimer.player() != 0) {
                    SetTimer.flip();
                }
            }
        } else if (!CheckersEvent.whichPlayer) {
            txtPlayer2Score.setBorder(BorderFactory.createLineBorder(Color.orange));
            txtPlayer1Score.setBorder(BorderFactory.createEmptyBorder());
            if (checkersEvent.giveUndoCount() >= 2) {
                checkersEvent.setUndoCount(0);
                CheckersEvent.whichPlayer = !CheckersEvent.whichPlayer;
                txtPlayer1Score.setBorder(BorderFactory.createLineBorder(Color.orange));
                txtPlayer2Score.setBorder(BorderFactory.createEmptyBorder());
            }
            if (competitive) {
                if (SetTimer.player() != 1 && competitive) {
                    SetTimer.flip();
                }
            }
        }

        txtPlayer1Score.setText(" " + playerName1 + "\n Pieces Left:" + (12 - CheckersEvent.playerTwoWin)
                + "\n Pieces Won: " + CheckersEvent.playerOneWin);
        checkWinner();
        txtPlayer2Score.setText(" " + playerName2 + "\n Pieces Left:" + (12 - CheckersEvent.playerOneWin)
                + "\n Pieces Won: " + CheckersEvent.playerTwoWin);
        checkWinner();
    }

    public void checkWinner() throws FileNotFoundException, IOException {
        if (CheckersEvent.playerOneWin == 12) {
            setVisible(false);
            gameStart = false;
            CheckersPoint.playerPoints(playerName1, 12, CheckersEvent.playerTwoWin, 1, 0, 0);
            CheckersPoint.playerPoints(playerName2, CheckersEvent.playerTwoWin, 12, 0, 1, 0);
            CheckersSFX.playEnd();
            gameWinner.winnerScreen(playerName1, playerName2);
            instructionMain.instruction.setVisible(false);
            gameClock.pause();

        } else if (CheckersEvent.playerTwoWin == 12) {
            setVisible(false);
            gameStart = false;
            CheckersPoint.playerPoints(playerName2, 12, CheckersEvent.playerOneWin, 1, 0, 0);
            CheckersPoint.playerPoints(playerName1, CheckersEvent.playerOneWin, 12, 0, 1, 0);
            CheckersSFX.playEnd();
            gameWinner.winnerScreen(playerName2, playerName1);
            instructionMain.instruction.setVisible(false);
            gameClock.pause();
        }
    }

    private void tieScreen() {
        try {
            gameStart = false;
            setVisible(false);
            CheckersPoint.playerPoints(playerName1, CheckersEvent.playerOneWin, CheckersEvent.playerTwoWin, 0, 0, 1);
            CheckersPoint.playerPoints(playerName2, CheckersEvent.playerTwoWin, CheckersEvent.playerOneWin, 0, 0, 1);
            CheckersSFX.playEnd();
            gameWinner.tieScreen(playerName1, playerName2);
            if (competitive) {
                CheckersTime.tiePause();
            }
        } catch (IOException ex) {
            Logger.getLogger(CheckersGameBoard.class.getName()).log(Level.SEVERE, null, ex);
        }
        instructionMain.instruction.setVisible(false);
    }
}
/* Class for handling all the game events in the checkers game and the undo algortihms 
 *
 * @author szhan, sgu
 */
package ca.on.tdsb.student.checkers;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.logging.*;

public class CheckersEvent {

    CheckersGameBoard gameBoard;

    public int checkers[][] = new int[][]{{0, 1, 0, 1, 0, 1, 0, 1}, {1, 0, 1, 0, 1, 0, 1, 0},
    {0, 1, 0, 1, 0, 1, 0, 1}, {0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0},
    {2, 0, 2, 0, 2, 0, 2, 0}, {0, 2, 0, 2, 0, 2, 0, 2}, {2, 0, 2, 0, 2, 0, 2, 0}};

    public int possibleSpace[][] = new int[8][8];
    private final int possibleCheckerSpace[][] = new int[8][8];
    private final int directionCheck[][] = new int[8][8];

    private int clickChecker = -1;
    private int xOriginal, yOriginal, multipleWinInt, checking, player;
    public static int playerOneWin = 0, playerTwoWin = 0;

    public static boolean whichPlayer = true, multipleWin;
    private boolean possibleWin;

    private final int[][] pastBoard = new int[8][8];

    public int playerOnePastWin = 0, playerTwoPastWin = 0;
    public int undoCount = 0;

    public CheckersEvent(CheckersGameBoard in) {
        gameBoard = in;
        initBoard();
    }
    
    // sgu
    private void initBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                pastBoard[i][j] = 0;
            }
        }
        playerOneWin = 0;
        playerTwoWin = 0;
        whichPlayer = true;
    }
    
    // src: https://stackoverflow.com/questions/5617016/how-do-i-copy-a-2-dimensional-array-in-java
    private void saveBoard(int[][] source, int[][] destination) {
        for (int i = 0; i < source.length; i++) {
            System.arraycopy(source[i], 0, destination[i], 0, source[i].length);
        }
    }

    public void undo() {
        try {
            placeBoard();
        } catch (IOException ex) {
            Logger.getLogger(CheckersGameBoard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    // Places pieces and changes values according to the past
    private void placeBoard() throws IOException {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                gameBoard.arrSpace[i][j].setIcon(null);
            }
        }

        if (undoCount < 2) {
            whichPlayer = !whichPlayer;
            undoCount++;
        }

        saveBoard(pastBoard, checkers);
        playerOneWin = playerOnePastWin;
        playerTwoWin = playerTwoPastWin;
        gameBoard.checkersDisplay();
    }
    
    // szhan
    public void mouseClicked(MouseEvent e) throws IOException {
        if (e.getSource() == gameBoard.arrSpace[0][0]) {
            checkClicker(0, 0);
            possibleSpace(0, 0);
        } else if (e.getSource() == gameBoard.arrSpace[0][1]) {
            checkClicker(0, 1);
            possibleSpace(0, 1);
        } else if (e.getSource() == gameBoard.arrSpace[0][2]) {
            checkClicker(0, 2);
            possibleSpace(0, 2);
        } else if (e.getSource() == gameBoard.arrSpace[0][3]) {
            checkClicker(0, 3);
            possibleSpace(0, 3);
        } else if (e.getSource() == gameBoard.arrSpace[0][4]) {
            checkClicker(0, 4);
            possibleSpace(0, 4);
        } else if (e.getSource() == gameBoard.arrSpace[0][5]) {
            checkClicker(0, 5);
            possibleSpace(0, 5);
        } else if (e.getSource() == gameBoard.arrSpace[0][6]) {
            checkClicker(0, 6);
            possibleSpace(0, 6);
        } else if (e.getSource() == gameBoard.arrSpace[0][7]) {
            checkClicker(0, 7);
            possibleSpace(0, 7);
        } else if (e.getSource() == gameBoard.arrSpace[1][0]) {
            checkClicker(1, 0);
            possibleSpace(1, 0);
        } else if (e.getSource() == gameBoard.arrSpace[1][1]) {
            checkClicker(1, 1);
            possibleSpace(1, 1);
        } else if (e.getSource() == gameBoard.arrSpace[1][2]) {
            checkClicker(1, 2);
            possibleSpace(1, 2);
        } else if (e.getSource() == gameBoard.arrSpace[1][3]) {
            checkClicker(1, 3);
            possibleSpace(1, 3);
        } else if (e.getSource() == gameBoard.arrSpace[1][4]) {
            checkClicker(1, 4);
            possibleSpace(1, 4);
        } else if (e.getSource() == gameBoard.arrSpace[1][5]) {
            checkClicker(1, 5);
            possibleSpace(1, 5);
        } else if (e.getSource() == gameBoard.arrSpace[1][6]) {
            checkClicker(1, 6);
            possibleSpace(1, 6);
        } else if (e.getSource() == gameBoard.arrSpace[1][7]) {
            checkClicker(1, 7);
            possibleSpace(1, 7);
        } else if (e.getSource() == gameBoard.arrSpace[2][0]) {
            checkClicker(2, 0);
            possibleSpace(2, 0);
        } else if (e.getSource() == gameBoard.arrSpace[2][1]) {
            checkClicker(2, 1);
            possibleSpace(2, 1);
        } else if (e.getSource() == gameBoard.arrSpace[2][2]) {
            checkClicker(2, 2);
            possibleSpace(2, 2);
        } else if (e.getSource() == gameBoard.arrSpace[2][3]) {
            checkClicker(2, 3);
            possibleSpace(2, 3);
        } else if (e.getSource() == gameBoard.arrSpace[2][4]) {
            checkClicker(2, 4);
            possibleSpace(2, 4);
        } else if (e.getSource() == gameBoard.arrSpace[2][5]) {
            checkClicker(2, 5);
            possibleSpace(2, 5);
        } else if (e.getSource() == gameBoard.arrSpace[2][6]) {
            checkClicker(2, 6);
            possibleSpace(2, 6);
        } else if (e.getSource() == gameBoard.arrSpace[2][7]) {
            checkClicker(2, 7);
            possibleSpace(2, 7);
        } else if (e.getSource() == gameBoard.arrSpace[3][0]) {
            checkClicker(3, 0);
            possibleSpace(3, 0);
        } else if (e.getSource() == gameBoard.arrSpace[3][1]) {
            checkClicker(3, 1);
            possibleSpace(3, 1);
        } else if (e.getSource() == gameBoard.arrSpace[3][2]) {
            checkClicker(3, 2);
            possibleSpace(3, 2);
        } else if (e.getSource() == gameBoard.arrSpace[3][3]) {
            checkClicker(3, 3);
            possibleSpace(3, 3);
        } else if (e.getSource() == gameBoard.arrSpace[3][4]) {
            checkClicker(3, 4);
            possibleSpace(3, 4);
        } else if (e.getSource() == gameBoard.arrSpace[3][5]) {
            checkClicker(3, 5);
            possibleSpace(3, 5);
        } else if (e.getSource() == gameBoard.arrSpace[3][6]) {
            checkClicker(3, 6);
            possibleSpace(3, 6);
        } else if (e.getSource() == gameBoard.arrSpace[3][7]) {
            checkClicker(3, 7);
            possibleSpace(3, 7);
        } else if (e.getSource() == gameBoard.arrSpace[4][0]) {
            checkClicker(4, 0);
            possibleSpace(4, 0);
        } else if (e.getSource() == gameBoard.arrSpace[4][1]) {
            checkClicker(4, 1);
            possibleSpace(4, 1);
        } else if (e.getSource() == gameBoard.arrSpace[4][2]) {
            checkClicker(4, 2);
            possibleSpace(4, 2);
        } else if (e.getSource() == gameBoard.arrSpace[4][3]) {
            checkClicker(4, 3);
            possibleSpace(4, 3);
        } else if (e.getSource() == gameBoard.arrSpace[4][4]) {
            checkClicker(4, 4);
            possibleSpace(4, 4);
        } else if (e.getSource() == gameBoard.arrSpace[4][5]) {
            checkClicker(4, 5);
            possibleSpace(4, 5);
        } else if (e.getSource() == gameBoard.arrSpace[4][6]) {
            checkClicker(4, 6);
            possibleSpace(4, 6);
        } else if (e.getSource() == gameBoard.arrSpace[4][7]) {
            checkClicker(4, 7);
            possibleSpace(4, 7);
        } else if (e.getSource() == gameBoard.arrSpace[5][0]) {
            checkClicker(5, 0);
            possibleSpace(5, 0);
        } else if (e.getSource() == gameBoard.arrSpace[5][1]) {
            checkClicker(5, 1);
            possibleSpace(5, 1);
        } else if (e.getSource() == gameBoard.arrSpace[5][2]) {
            checkClicker(5, 2);
            possibleSpace(5, 2);
        } else if (e.getSource() == gameBoard.arrSpace[5][3]) {
            checkClicker(5, 3);
            possibleSpace(5, 3);
        } else if (e.getSource() == gameBoard.arrSpace[5][4]) {
            checkClicker(5, 4);
            possibleSpace(5, 4);
        } else if (e.getSource() == gameBoard.arrSpace[5][5]) {
            checkClicker(5, 5);
            possibleSpace(5, 5);
        } else if (e.getSource() == gameBoard.arrSpace[5][6]) {
            checkClicker(5, 6);
            possibleSpace(5, 6);
        } else if (e.getSource() == gameBoard.arrSpace[5][7]) {
            checkClicker(5, 7);
            possibleSpace(5, 7);
        } else if (e.getSource() == gameBoard.arrSpace[6][0]) {
            checkClicker(6, 0);
            possibleSpace(6, 0);
        } else if (e.getSource() == gameBoard.arrSpace[6][1]) {
            checkClicker(6, 1);
            possibleSpace(6, 1);
        } else if (e.getSource() == gameBoard.arrSpace[6][2]) {
            checkClicker(6, 2);
            possibleSpace(6, 2);
        } else if (e.getSource() == gameBoard.arrSpace[6][3]) {
            checkClicker(6, 3);
            possibleSpace(6, 3);
        } else if (e.getSource() == gameBoard.arrSpace[6][4]) {
            checkClicker(6, 4);
            possibleSpace(6, 4);
        } else if (e.getSource() == gameBoard.arrSpace[6][5]) {
            checkClicker(6, 5);
            possibleSpace(6, 5);
        } else if (e.getSource() == gameBoard.arrSpace[6][6]) {
            checkClicker(6, 6);
            possibleSpace(6, 6);
        } else if (e.getSource() == gameBoard.arrSpace[6][7]) {
            checkClicker(6, 7);
            possibleSpace(6, 7);
        } else if (e.getSource() == gameBoard.arrSpace[7][0]) {
            checkClicker(7, 0);
            possibleSpace(7, 0);
        } else if (e.getSource() == gameBoard.arrSpace[7][1]) {
            checkClicker(7, 1);
            possibleSpace(7, 1);
        } else if (e.getSource() == gameBoard.arrSpace[7][2]) {
            checkClicker(7, 2);
            possibleSpace(7, 2);
        } else if (e.getSource() == gameBoard.arrSpace[7][3]) {
            checkClicker(7, 3);
            possibleSpace(7, 3);
        } else if (e.getSource() == gameBoard.arrSpace[7][4]) {
            checkClicker(7, 4);
            possibleSpace(7, 4);
        } else if (e.getSource() == gameBoard.arrSpace[7][5]) {
            checkClicker(7, 5);
            possibleSpace(7, 5);
        } else if (e.getSource() == gameBoard.arrSpace[7][6]) {
            checkClicker(7, 6);
            possibleSpace(7, 6);
        } else if (e.getSource() == gameBoard.arrSpace[7][7]) {
            checkClicker(7, 7);
            possibleSpace(7, 7);
        }

    }

    private void checkClicker(int x, int y) {
        defaultSpace();
        if ((gameBoard.arrSpace[x][y].getIcon() == null && clickChecker != 1 && clickChecker != 2)) {
            clickChecker = -1;
        } else if (gameBoard.arrSpace[x][y].getIcon() != null && clickChecker == -1) {
            clickChecker = 0;
        } else if (gameBoard.arrSpace[x][y].getIcon() != null && clickChecker == 0) {
            clickChecker = 0;
        } else if (gameBoard.arrSpace[x][y].getIcon() != null) {
            clickChecker = 0;
        }
    }
    
    // Sets the array of available space to default for display
    private void defaultSpace() {
        for (int i = 0; i < 8; i++) {
            for (int x = 0; x < 8; x++) {
                possibleSpace[i][x] = 0;
            }
        }
    }

    private void defaultSelection() {
        for (int i = 0; i < 8; i++) {
            for (int x = 0; x < 8; x++) {
                possibleCheckerSpace[i][x] = 0;
            }
        }
    }

    private void defaultDirectionCheck() {
        for (int i = 0; i < 8; i++) {
            for (int x = 0; x < 8; x++) {
                directionCheck[i][x] = 0;
            }
        }
    }

    // Checks if there are pieces available for force-jump
    private void playerOnePossibleWin() {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if ((checkers[x][y] == 1 || checkers[x][y] == 3) && (x + 1) != 8 && (y + 1) != 8 && (x + 2) != 8
                        && (y + 2) != 8 && checkers[x + 2][y + 2] == 0
                        && (checkers[x + 1][y + 1] == 2 || checkers[x + 1][y + 1] == 4)) {
                    possibleSpace[x][y] = 2;
                    possibleWin = true;
                } else if ((checkers[x][y] == 1 || checkers[x][y] == 3) && (x + 1) != 8 && (y - 1) != -1 && (x + 2) != 8
                        && (y - 2) != -1 && checkers[x + 2][y - 2] == 0
                        && (checkers[x + 1][y - 1] == 2 || checkers[x + 1][y - 1] == 4)) {
                    possibleSpace[x][y] = 2;
                    possibleWin = true;

                } else if (checkers[x][y] == 3 && (x - 1) != -1 && (y + 1) != 8 && (x - 2) != -1 && (y + 2) != 8
                        && (checkers[x - 1][y + 1] == 2 || checkers[x - 1][y + 1] == 4)
                        && checkers[x - 2][y + 2] == 0) {
                    possibleSpace[x][y] = 2;
                    possibleWin = true;

                } else if (checkers[x][y] == 3 && (x - 1) != -1 && (y - 1) != -1 && (x - 2) != -1 && (y - 2) != -1
                        && checkers[x - 2][y - 2] == 0
                        && (checkers[x - 1][y - 1] == 2 || checkers[x - 1][y - 1] == 4)) {
                    possibleSpace[x][y] = 2;
                    possibleWin = true;
                }
            }
        }
    }

    private void playerTwoPossibleWin() {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if ((checkers[x][y] == 2 || checkers[x][y] == 4) && (x - 1) != -1 && (y + 1) != 8 && (x - 2) != -1
                        && (y + 2) != 8 && (checkers[x - 1][y + 1] == 1 || checkers[x - 1][y + 1] == 3)
                        && checkers[x - 2][y + 2] == 0) {
                    possibleSpace[x][y] = 2;
                    possibleWin = true;

                } else if ((checkers[x][y] == 2 || checkers[x][y] == 4) && (x - 1) != -1 && (y - 1) != -1
                        && (x - 2) != -1 && (y - 2) != -1
                        && (checkers[x - 1][y - 1] == 1 || checkers[x - 1][y - 1] == 3)
                        && checkers[x - 2][y - 2] == 0) {
                    possibleSpace[x][y] = 2;
                    possibleWin = true;
                } else if (checkers[x][y] == 4 && (x + 1) != 8 && (y + 1) != 8 && (x + 2) != 8 && (y + 2) != 8
                        && (checkers[x + 1][y + 1] == 1 || checkers[x + 1][y + 1] == 3)
                        && checkers[x + 2][y + 2] == 0) {
                    possibleSpace[x][y] = 2;
                    possibleWin = true;

                } else if (checkers[x][y] == 4 && (x + 1) != 8 && (y - 1) != -1 && (y - 2) != -1 && (x + 2) != 8
                        && (checkers[x + 1][y - 1] == 3 || checkers[x + 1][y - 1] == 1)
                        && checkers[x + 2][y - 2] == 0) {
                    possibleSpace[x][y] = 2;
                    possibleWin = true;
                }
            }
        }
    }
    
    // Finding possible positions for a piece in the down direction
    private void downMultipleWin(int x, int y, int check) {
        checking++;
        multipleWinInt += 2;
        if (checking == 0) {
            if (checkers[x][y] == 1 || checkers[x][y] == 3) {
                player = 2;
            } else if (checkers[x][y] == 2 || checkers[x][y] == 4) {
                player = 1;
            }
        }
        if (checkers[x][y] == 1 || checkers[x][y] == 4 || checkers[x][y] == 3 || multipleWin == true) {
            if ((x + 1) != 8 && (y + 1) != 8 && (x + 2) != 8 && (y + 2) != 8 && checkers[x + 2][y + 2] == 0
                    && (checkers[x + 1][y + 1] == player || checkers[x + 1][y + 1] == (player + 2))) {
                possibleSpace[x + 2][y + 2] = 1;
                possibleCheckerSpace[x + 2][y + 2] = multipleWinInt;
                possibleCheckerSpace[x + 1][y + 1] = multipleWinInt - 1;
                multipleWin = true;

                if (checking == 0) {
                    directionCheck[x + 1][y + 1] = 4;
                    directionCheck[x + 2][y + 2] = 4;
                    downMultipleWin(x + 2, y + 2, 4);
                } else if (checking > 0) {
                    directionCheck[x + 1][y + 1] = check;
                    directionCheck[x + 2][y + 2] = check;
                    downMultipleWin(x + 2, y + 2, check);
                }

            }
            if ((x + 1) != 8 && (y - 1) != -1 && (x + 2) != 8 && (y - 2) != -1 && checkers[x + 2][y - 2] == 0
                    && (checkers[x + 1][y - 1] == player || checkers[x + 1][y - 1] == (player + 2))) {
                possibleSpace[x + 2][y - 2] = 1;
                possibleCheckerSpace[x + 2][y - 2] = multipleWinInt;
                possibleCheckerSpace[x + 1][y - 1] = multipleWinInt - 1;
                multipleWin = true;

                if (checking == 0) {
                    directionCheck[x + 1][y - 1] = 3;
                    directionCheck[x + 2][y - 2] = 3;
                    downMultipleWin(x + 2, y - 2, 3);
                } else if (checking > 0) {
                    directionCheck[x + 1][y - 1] = check;
                    directionCheck[x + 2][y - 2] = check;
                    downMultipleWin(x + 2, y - 2, check);
                }
            }
        }
    }

    // Finding possible positions for a piece in the up direction
    private void upMultipleWin(int x, int y, int check) {
        checking++;
        multipleWinInt += 2;

        if (checking == 0) {
            if (checkers[x][y] == 1 || checkers[x][y] == 3) {
                player = 2;
            } else if (checkers[x][y] == 2 || checkers[x][y] == 4) {
                player = 1;
            }
        }

        if (checkers[x][y] == 2 || checkers[x][y] == 3 || checkers[x][y] == 4 || multipleWin == true) {
            if ((x - 1) != -1 && (y + 1) != 8 && (x - 2) != -1 && (y + 2) != 8
                    && (checkers[x - 1][y + 1] == player || checkers[x - 1][y + 1] == (player + 2)) && checkers[x - 2][y + 2] == 0) {
                possibleSpace[x - 2][y + 2] = 1;
                possibleCheckerSpace[x - 2][y + 2] = multipleWinInt;
                possibleCheckerSpace[x - 1][y + 1] = multipleWinInt - 1;
                multipleWin = true;

                if (checking == 0) {
                    directionCheck[x - 1][y + 1] = 2;
                    directionCheck[x - 2][y + 2] = 2;
                    upMultipleWin(x - 2, y + 2, 2);
                } else if (checking > 0) {
                    directionCheck[x - 1][y + 1] = check;
                    directionCheck[x - 2][y + 2] = check;
                    upMultipleWin(x - 2, y + 2, check);
                }
            }

            if ((x - 1) != -1 && (y - 1) != -1 && (x - 2) != -1 && (y - 2) != -1
                    && (checkers[x - 1][y - 1] == player || checkers[x - 1][y - 1] == (player + 2)) && checkers[x - 2][y - 2] == 0) {
                possibleSpace[x - 2][y - 2] = 1;
                possibleCheckerSpace[x - 2][y - 2] = multipleWinInt;
                possibleCheckerSpace[x - 1][y - 1] = multipleWinInt - 1;
                multipleWin = true;

                if (checking == 0) {
                    directionCheck[x - 1][y - 1] = 1;
                    directionCheck[x - 2][y - 2] = 1;
                    upMultipleWin(x - 2, y - 2, 1);
                } else if (checking > 0) {
                    directionCheck[x - 1][y - 1] = check;
                    directionCheck[x - 2][y - 2] = check;
                    upMultipleWin(x - 2, y - 2, check);
                }
            }
        }
    }

    // Method for moving the pieces and showing the available places a piece can go
    private void possibleSpace(int x, int y) throws IOException {
        int directionChecker = 0;
        boolean checkCapture = false;
        // Moving pieces if it's player one
        if (clickChecker == 1) {
            if (possibleCheckerSpace[x][y] % 2 == 0 && possibleCheckerSpace[x][y] != 0) {
                if (x == 7 || checkers[xOriginal][yOriginal] == 3) {
                    checkers[x][y] = 3;
                } else {
                    checkers[x][y] = 1;
                }
                checkers[xOriginal][yOriginal] = 0;
                clickChecker = 0;

                playerOnePastWin = playerOneWin;
                playerTwoPastWin = playerTwoWin;

                directionChecker = directionCheck[x][y];
                for (int i = 0; i < (possibleCheckerSpace[x][y] / 2); i++) {
                    for (int a = 0; a < 8; a++) {
                        for (int b = 0; b < 8; b++) {
                            if (possibleCheckerSpace[a][b] == (possibleCheckerSpace[x][y] - (i * 2)) - 1
                                    && directionCheck[a][b] == directionChecker) {
                                checkers[a][b] = 0;
                                playerOneWin++;
                                checkCapture = true;
                            }
                        }
                    }
                }

                if (checkCapture == true) {
                    CheckersSFX.playCapture();
                } else if (checkCapture == false) {
                    CheckersSFX.playMove();
                }

                whichPlayer = false;
            }

            defaultSelection();

        } // Moving pieces if it's player two
        else if (clickChecker == 2) {

            if (possibleCheckerSpace[x][y] % 2 == 0 && possibleCheckerSpace[x][y] != 0) {

                if (x == 0 || checkers[xOriginal][yOriginal] == 4) {
                    checkers[x][y] = 4;
                } else {
                    checkers[x][y] = 2;
                }
                checkers[xOriginal][yOriginal] = 0;

                clickChecker = 0;

                playerOnePastWin = playerOneWin;
                playerTwoPastWin = playerTwoWin;

                directionChecker = directionCheck[x][y];
                for (int i = 0; i < (possibleCheckerSpace[x][y] / 2); i++) {
                    for (int a = 0; a < 8; a++) {
                        for (int b = 0; b < 8; b++) {
                            if (possibleCheckerSpace[a][b] == (possibleCheckerSpace[x][y] - (i * 2)) - 1
                                    && directionCheck[a][b] == directionChecker) {
                                checkers[a][b] = 0;
                                playerTwoWin++;
                                checkCapture = true;
                            }
                        }
                    }
                }

                if (checkCapture == true) {
                    CheckersSFX.playCapture();
                } else if (checkCapture == false) {
                    CheckersSFX.playMove();
                }

                whichPlayer = true;
            }

            defaultSelection();
         
        } else if (clickChecker == 0) { // Logic for when player selects a checker piece

            saveBoard(checkers, pastBoard);

            // Logic for player one's pieces
            if ((checkers[x][y] == 1 || checkers[x][y] == 3) && whichPlayer == true) {
                playerOnePossibleWin();

                if (possibleWin == true) {

                    multipleWinInt = 0;
                    checking = -1;
                    player = 0;
                    defaultSelection();
                    defaultDirectionCheck();
                    multipleWin = false;

                    if (checkers[x][y] == 3) {
                        downMultipleWin(x, y, 0);
                        upMultipleWin(x, y, 0);
                    } else if (checkers[x][y] == 1) {
                        downMultipleWin(x, y, 0);
                    }

                } else if (possibleWin == false) {

                    if ((x + 1) != 8 && (y + 1) != 8 && checkers[x + 1][y + 1] == 0) {
                        possibleSpace[x + 1][y + 1] = 1;
                        possibleCheckerSpace[x + 1][y + 1] = 2;
                    }
                    if ((x + 1) != 8 && (y - 1) != -1 && checkers[x + 1][y - 1] == 0) {
                        possibleSpace[x + 1][y - 1] = 1;
                        possibleCheckerSpace[x + 1][y - 1] = 2;
                    }
                    if (checkers[x][y] == 3 && (x - 1) != -1 && (y - 1) != -1 && checkers[x - 1][y - 1] == 0) {
                        possibleSpace[x - 1][y - 1] = 1;
                        possibleCheckerSpace[x - 1][y - 1] = 2;
                    }
                    if (checkers[x][y] == 3 && (x - 1) != -1 && (y + 1) != 8 && checkers[x - 1][y + 1] == 0) {
                        possibleSpace[x - 1][y + 1] = 1;
                        possibleCheckerSpace[x - 1][y + 1] = 2;
                    }
                }

                possibleWin = false;
            }

            // Logic for player two's pieces
            if ((checkers[x][y] == 2 || checkers[x][y] == 4) && whichPlayer == false) {

                playerTwoPossibleWin();

                // Force-jump
                if (possibleWin == true) {

                    multipleWinInt = 0;
                    checking = -1;
                    player = 0;
                    defaultSelection();
                    defaultDirectionCheck();
                    multipleWin = false;

                    if (checkers[x][y] == 4) {
                        downMultipleWin(x, y, 0);
                        upMultipleWin(x, y, 0);
                    } else if (checkers[x][y] == 2) {
                        upMultipleWin(x, y, 0);
                    }

                } // No force-jump
                else if (possibleWin == false) {

                    if ((x - 1) != -1 && (y - 1) != -1 && checkers[x - 1][y - 1] == 0) {
                        possibleSpace[x - 1][y - 1] = 1;
                        possibleCheckerSpace[x - 1][y - 1] = 2;
                    }
                    if ((x - 1) != -1 && (y + 1) != 8 && checkers[x - 1][y + 1] == 0) {
                        possibleSpace[x - 1][y + 1] = 1;
                        possibleCheckerSpace[x - 1][y + 1] = 2;
                    }
                    if (checkers[x][y] == 4 && (x + 1) != 8 && (y - 1) != -1 && checkers[x + 1][y - 1] == 0) {
                        possibleSpace[x + 1][y - 1] = 1;
                        possibleCheckerSpace[x + 1][y - 1] = 2;
                    }
                    if (checkers[x][y] == 4 && (x + 1) != 8 && (y + 1) != 8 && checkers[x + 1][y + 1] == 0) {
                        possibleSpace[x + 1][y + 1] = 1;
                        possibleCheckerSpace[x + 1][y + 1] = 2;
                    }
                }

                possibleWin = false;

            }

            if (checkers[x][y] == 1 || checkers[x][y] == 3) {
                clickChecker = 1;
            } else if (checkers[x][y] == 2 || checkers[x][y] == 4) {
                clickChecker = 2;
            }

            xOriginal = x;
            yOriginal = y;
        }

        gameBoard.checkersDisplay();

    }
    public int giveUndoCount() {
        return undoCount;
    }

    public void setUndoCount(int newUndo) {
        this.undoCount = newUndo;
    }
}
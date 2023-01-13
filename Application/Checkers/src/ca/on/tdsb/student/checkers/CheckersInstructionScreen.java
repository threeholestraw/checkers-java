/**
 * GUI and text for instructions frame
 * author@ mahmed, azheng
 */
package ca.on.tdsb.student.checkers;

import javax.swing.*;
import java.awt.*;

public class CheckersInstructionScreen {

    // mahmed
    JFrame instruction = new JFrame("instructions");
    JLabel instructScrTitle = new JLabel("Instructions", SwingConstants.CENTER);
    JTextArea txtInstruction = new JTextArea();
    JPanel instructPanel = new JPanel(new GridLayout(1, 1, 50, 50));
    JButton exitInstruction = new JButton();
    JScrollPane instructionScroll;

    public CheckersInstructionScreen() {
        instruction.setSize(500, 475);
        instruction.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        FlowLayout instructionLayout = new FlowLayout();
        instruction.setLayout(instructionLayout);
        instruction.setResizable(false);

        instructScrTitle.setFont(new Font("Verdana", Font.BOLD, 50));
        instructScrTitle.setForeground(new Color(126, 3, 3));
        instruction.add(instructScrTitle);

        txtInstruction.setEditable(false);
        instructPanel.add(txtInstruction);
        instructionScroll = new JScrollPane(txtInstruction, 
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        instructionScroll.setViewportView(txtInstruction);
        instructionScroll.setPreferredSize(new Dimension(400, 300));
        instructPanel.add(instructionScroll);
        
        instruction.add(instructPanel);
        CheckersIntroScreen.introButton(exitInstruction);
        exitInstruction.setText("Return");
        instruction.add(exitInstruction);

        exitInstruction.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                instruction.setVisible(false);
                CheckersGameBoard.gameInstruction.setEnabled(true);
                CheckersGameBoard.instructionShown = false;
                if (!CheckersGameBoard.gameStart) {
                    CheckersIntroScreen instructionReturnMain = new CheckersIntroScreen();
                    instructionReturnMain.introVisible().setVisible(true);
                }
            }
        });

        // azheng
        txtInstruction.setLineWrap(true);
        txtInstruction.setWrapStyleWord(true);
        txtInstruction.append(" OBJECTIVE\n\n");
        txtInstruction.append("Capture all of your opponent's pieces before they capture all of yours, or before your time runs out\n\n");
        txtInstruction.append(" HOW TO PLAY\n\n");
        txtInstruction.append("-  Each side has 12 pieces called checkers on the black squares.\n");
        txtInstruction.append("-  Normal pieces move forward diagonally one square at a time, except when they capture a piece.\n");
        txtInstruction.append("-  King pieces can move in all four diagonal directions, one square at a time.\n");
        txtInstruction.append("-  A piece can't move if there is a piece in the square it wants to land on.\n");
        txtInstruction.append("-  If you are confused about the rules, the possible moves of your selected piece will be highlighted on the board.\n\n");
        txtInstruction.append(" CAPTURING A PIECE\n\n");
        txtInstruction.append("You are able to capture an enemy piece by being adjacent to the enemy piece, and jumping over it onto the empty square behind it. If you are able to capture a piece in a turn, you must do it.\n\n");
        txtInstruction.append(" KING PIECES\n\n");
        txtInstruction.append("Pieces are promoted into a king when they reach the end row on the opposite side of the board. King Pieces do the same things as normal pieces except they can move and capture backwards as well.\n\n");
        txtInstruction.append(" CASUAL VS COMPETITIVE\n\n");
        txtInstruction.append("Casual games are do not have a timer, and have an undo button for mistakes or faulty moves. Competitive games have a timer with a user specified amount of minutes, and they do not have an undo button.\n\n");
        txtInstruction.append(" TIE BUTTON\n\n");
        txtInstruction.append(" UNDO BUTTON\n\n");
        txtInstruction.append("Each player gets two chances to undo their moves during their turn. After the second undo, the turn is automatically transferred to opponent \n\n");
        txtInstruction.append("The tie button allows the game to end neutrally so no players get a loss. The button can be used if the players do not wish to continue the current game\n\n");
    }
} 
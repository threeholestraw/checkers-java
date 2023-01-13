/**
 * Collects and stores user player information
 * author@   mahmed
 */
package ca.on.tdsb.student.checkers;

import java.awt.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.*;

public class CheckersUserInfo {

    JFrame userInfoForm = new JFrame();
    JPanel contentPanel = new JPanel();
    ButtonGroup gameMode = new ButtonGroup();
    JRadioButton rbtnCasual = new JRadioButton("Casual");
    JRadioButton rbtnCompetitive = new JRadioButton("Competitive");
    JButton submit = new JButton();
    JTextField txtPlayer1 = new JTextField();
    JTextField txtPlayer2 = new JTextField();
    String[] player1ColourOption = {"yellow", "orange", "green"};
    String[] player2ColourOption = {"blue", "purple", "pink"};
    JComboBox<String> colourChooserPlayer1 = new JComboBox<>(player1ColourOption); // src: https://www.javatpoint.com/java-jcombobox and https://stackoverflow.com/questions/14418673/java-compiler-unchecked-or-unsafe-operations-warning-with-jcombobox/14418807
    JComboBox<String> colourChooserPlayer2 = new JComboBox<>(player2ColourOption);
    boolean contGame;
    CheckersTime gameTime = new CheckersTime();

    public CheckersUserInfo() {
        userInfoForm.setTitle("Enter the following information:");
        userInfoForm.setSize(520, 220);
        userInfoForm.setResizable(false);
        userInfoForm.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        
        contentPanel.setLayout(new GridLayout(3, 4, 10, 10));
        
        contentPanel.add(new JLabel("player 1 : "));
        txtPlayer1.setPreferredSize(new Dimension(50, 20));
        contentPanel.add(txtPlayer1);
        
        contentPanel.add(new JLabel("player 2 : "));
        txtPlayer2.setPreferredSize(new Dimension(50, 20));
        contentPanel.add(txtPlayer2);
        
        contentPanel.add(new JLabel("player 1 colour: "));
        colourChooserPlayer1.setPreferredSize(new Dimension(100, 40));
        contentPanel.add(colourChooserPlayer1);
        
        contentPanel.add(new JLabel("player 2 colour: "));
        colourChooserPlayer2.setPreferredSize(new Dimension(100, 40));
        contentPanel.add(colourChooserPlayer2);
        
        contentPanel.add(new JLabel("Game mode: "));
        gameMode.add(rbtnCasual);
        gameMode.add(rbtnCompetitive);
        contentPanel.add(rbtnCasual);
        contentPanel.add(rbtnCompetitive);
        
        submit.setBackground(new Color(126, 3, 3));
        submit.setForeground(Color.white);
        submit.setFont(new Font("Verdana", Font.PLAIN, 25));
        submit.setPreferredSize(new Dimension(20, 20));
        submit.setFocusable(false);     
        submit.setText("Play");
        contentPanel.add(submit);
        
        userInfoForm.add(contentPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(userInfoForm.getContentPane());
        userInfoForm.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(contentPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(contentPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(27, Short.MAX_VALUE))
        );

        submit.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkUserInput();
                if (contGame) {
                    CheckersGameBoard.playerName1 = txtPlayer1.getText();
                    CheckersGameBoard.playerName2 = txtPlayer2.getText();
                    CheckersGameBoard.player1Colour = colourChooserPlayer1.getSelectedItem().toString(); // src: https://www.javatpoint.com/java-jcombobox and https://www.youtube.com/watch?v=YxCm49O4zt8
                    CheckersGameBoard.player2Colour = colourChooserPlayer2.getSelectedItem().toString();
                    if (rbtnCompetitive.isSelected()) {
                        CheckersGameBoard.competitive = true;
                        try {
                            gameTime.enterGameTime();
                        } catch (LineUnavailableException ex) {
                            Logger.getLogger(CheckersUserInfo.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else if (rbtnCasual.isSelected()) {
                        try {
                            CheckersGameBoard.competitive = false;
                            CheckersGameBoard startGame = new CheckersGameBoard();
                            CheckersGameBoard.colourAssign = 0;
                        } catch (IOException ex) {
                            Logger.getLogger(CheckersUserInfo.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    
                    userInfoForm.setVisible(false);
                    
                }
            }
        });
    }

    private void checkUserInput() {
        if (rbtnCasual.isSelected() || rbtnCompetitive.isSelected()) {
            if (nameLength(txtPlayer1.getText()) && nameLength(txtPlayer2.getText())) {
                contGame = false;
                if ((txtPlayer1.getText().trim().equals(txtPlayer1.getText())) && (txtPlayer2.getText().trim().equals(txtPlayer2.getText()))) {  
                  if (!(txtPlayer1.getText().toLowerCase()).equals(txtPlayer2.getText().toLowerCase())) {
                    contGame = true;
                  } else {
                      contGame = false;
                      JOptionPane.showMessageDialog(null, "Player1 and player2 cannot have the same name. Names are case insensitive");
                  }
                } else {
                  contGame = false;
                  JOptionPane.showMessageDialog(null, "Names cannot contain leading or trailing empty spaces");
                }
            } else {
                contGame = false;
                JOptionPane.showMessageDialog(null, "Maximam name character length is 10. Names are case insensitive");
            }
        } else {
            contGame = false;
            JOptionPane.showMessageDialog(null, "Ensure all of the information is provided.");
        }
    }

    private boolean nameLength(String name) {
        if (name.length() > 10) {
            return false;
        } else if (name.length() == 0) {
            return false;
        } else {
            return true;
        }
    }

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CheckersUserInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CheckersUserInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CheckersUserInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CheckersUserInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
            }
        });
    }
}
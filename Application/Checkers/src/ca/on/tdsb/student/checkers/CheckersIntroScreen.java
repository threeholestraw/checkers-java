/**
 * GUI for the introduction screen
 * author@   mahmed
 */
package ca.on.tdsb.student.checkers;

import javax.swing.*;
import java.awt.*;

public class CheckersIntroScreen extends JFrame {

    JLabel introScrTitle = new JLabel("Checkers", SwingConstants.CENTER);
    JLabel introImg;
    JLabel teamName = new JLabel();
    JButton introPlay = new JButton("Play");
    JButton introInstruction = new JButton("Instructions");
    JButton introExit = new JButton("Exit");
    JPanel rowForBtn = new JPanel(new GridLayout(1, 2, 10, 20));

    public CheckersIntroScreen() {
        setTitle("Introduction");
        setSize(650, 525);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FlowLayout introLayout = new FlowLayout();
        introLayout.setVgap(10);
        setLayout(introLayout);
        setResizable(false);

        introScrTitle.setFont(new Font("Verdana", Font.BOLD, 50));
        introScrTitle.setForeground(new Color(126, 3, 3)); // src: https://htmlcolorcodes.com/
        add(introScrTitle);

        introImg = new JLabel(new ImageIcon(CheckersResource.getRes("introduction image.jpg"))); // src: https://www.photopea.com/#iNDJWxRfs (artist: mahmed)
        add(introImg);

        introButton(introPlay);
        add(introPlay);

        introButton(introInstruction);
        introButton(introExit);

        rowForBtn.add(new JLabel());
        rowForBtn.add(introInstruction);
        rowForBtn.add(introExit);
        rowForBtn.add(new JLabel());
        add(rowForBtn);

        teamName.setText("Made by USBEES");
        teamName.setFont(new Font("Verdana", Font.BOLD, 12));
        teamName.setForeground(new Color(126, 3, 3));
        teamName.setHorizontalAlignment(JLabel.CENTER);
        add(teamName);

        introPlay.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setVisible(false);
                CheckersUserInfo gameInfo = new CheckersUserInfo();
                gameInfo.userInfoForm.setVisible(true);
            }
        });

        introInstruction.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setVisible(false);
                CheckersInstructionScreen instructionMain = new CheckersInstructionScreen();
                instructionMain.instruction.setVisible(true);
            }
        });

        introExit.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                System.exit(0);
            }
        });
    }

    static void introButton(JButton btnSample) {
        btnSample.setBackground(new Color(126, 3, 3));
        btnSample.setForeground(Color.white);
        btnSample.setFont(new Font("Verdana", Font.PLAIN, 25));
        btnSample.setPreferredSize(new Dimension(200, 50));
        btnSample.setFocusable(false);
    }

    public JFrame introVisible() {
        return this;
    }
}
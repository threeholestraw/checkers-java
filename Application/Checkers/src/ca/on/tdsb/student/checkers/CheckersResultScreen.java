/**
 * GUI for result screen and parsing of player XML files
 * author@ mahmed
 */
package ca.on.tdsb.student.checkers;

import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.util.logging.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class CheckersResultScreen extends JFrame {

    private JLabel lblWinner;
    private JLabel resultImg;
    private JLabel resultTitle;
    private JButton returnMain;
    private JTextArea overallResult;
    private JScrollPane spResult;

    public CheckersResultScreen() {
        initComponents();

        returnMain.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CheckersIntroScreen returnToIntro = new CheckersIntroScreen();
                returnToIntro.introVisible().setVisible(true);
                setVisible(false);
            }
        });
    }

    private void designWinnerScreen(String winner, String loser) {
        resultTitle.setText("Congratulations!");
        resultTitle.setFont(new Font("Verdana", Font.BOLD, 40));

        resultImg.setIcon(new ImageIcon(CheckersResource.getRes("winner result.png"))); // img src: https://www.photopea.com/#ijUPDbgEL (artist: mahmed)

        lblWinner.setText(winner);
        updateResult(winner, loser);
        setVisible(true);
    }

    private void designTieScreen(String player1, String player2) {
        resultTitle.setText("Tie!");
        resultTitle.setFont(new Font("Verdana", Font.BOLD, 60));

        resultImg.setIcon(new ImageIcon(CheckersResource.getRes("tie result.png"))); // img src: https://www.photopea.com/#izGeP_N5s and https://webstockreview.net/pict/getfirst

        lblWinner.setText("");
        updateResult(player1, player2);
        setVisible(true);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        resultImg = new javax.swing.JLabel();
        resultTitle = new javax.swing.JLabel();
        lblWinner = new javax.swing.JLabel();
        returnMain = new javax.swing.JButton();
        spResult = new javax.swing.JScrollPane();
        overallResult = new javax.swing.JTextArea();

        setTitle("Results");
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);

        resultTitle.setFont(new java.awt.Font("Verdana", 1, 40));
        resultTitle.setForeground(new java.awt.Color(126, 3, 3));
        resultTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        lblWinner.setFont(new Font("Verdana", Font.BOLD, 20));
        lblWinner.setForeground(new Color(126, 3, 3));
        lblWinner.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        returnMain.setBackground(new java.awt.Color(197, 0, 0));
        returnMain.setFont(new java.awt.Font("Tahoma", 1, 16));
        returnMain.setForeground(new java.awt.Color(255, 255, 255));
        returnMain.setText("Leave");

        overallResult.setEditable(false);
        overallResult.setColumns(20);
        overallResult.setRows(5);
        spResult.setViewportView(overallResult);
        spResult.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(resultImg, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                                                .addComponent(lblWinner, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(returnMain, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(spResult, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addContainerGap(17, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(resultTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(36, 36, 36))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(resultTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(lblWinner, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(spResult, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(returnMain, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(39, 39, 39))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(resultImg, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
    }

    private void updateResult(String player1, String player2) {
        try {
            overallResult.setText("");

            String player1file = CheckersResource.getPath() + "/ca/on/tdsb/student/checkers/PlayerMatchHistory/" + player1 + ".xml";
            String player2file = CheckersResource.getPath() + "/ca/on/tdsb/student/checkers/PlayerMatchHistory/" + player2 + ".xml";
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document player1Doc = docBuilder.parse(player1file);
            Document player2Doc = docBuilder.parse(player2file);

            String player1PieceWon = ((player1Doc.getElementsByTagName("piecesWon").item(0)).getChildNodes().item(0)).getNodeValue();
            String player1PieceLost = ((player1Doc.getElementsByTagName("piecesLost").item(0)).getChildNodes().item(0)).getNodeValue();
            String player1Won = ((player1Doc.getElementsByTagName("gamesWon").item(0)).getChildNodes().item(0)).getNodeValue();
            String player1Lost = ((player1Doc.getElementsByTagName("gamesLost").item(0)).getChildNodes().item(0)).getNodeValue();
            String player1Tie = ((player1Doc.getElementsByTagName("gamesTied").item(0)).getChildNodes().item(0)).getNodeValue();

            String player2PieceWon = ((player2Doc.getElementsByTagName("piecesWon").item(0)).getChildNodes().item(0)).getNodeValue();
            String player2PieceLost = ((player2Doc.getElementsByTagName("piecesLost").item(0)).getChildNodes().item(0)).getNodeValue();
            String player2Won = ((player2Doc.getElementsByTagName("gamesWon").item(0)).getChildNodes().item(0)).getNodeValue();
            String player2Lost = ((player2Doc.getElementsByTagName("gamesLost").item(0)).getChildNodes().item(0)).getNodeValue();
            String player2Tie = ((player2Doc.getElementsByTagName("gamesTied").item(0)).getChildNodes().item(0)).getNodeValue();

            writeResult(player1, player1PieceWon, player1PieceLost, player1Won, player1Lost, player1Tie);
            overallResult.append("\n\n");
            writeResult(player2, player2PieceWon, player2PieceLost, player2Won, player2Lost, player2Tie);

        } catch (ParserConfigurationException ex) {
            Logger.getLogger(CheckersResultScreen.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(CheckersResultScreen.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CheckersResultScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void writeResult(String player, String pieceWon, String pieceLost, String gameWon, String gameLost, String gameTied) {
        overallResult.append(" " + player + "\n");
        overallResult.append(" Pieces won: " + pieceWon + "\n");
        overallResult.append(" Pieces lost: " + pieceLost + "\n");
        overallResult.append(" Games won: " + gameWon + "\n");
        overallResult.append(" Games lost: " + gameLost + "\n");
        overallResult.append(" Games tied: " + gameTied);
    }
    
    public void winnerScreen(String winner, String loser) {
        designWinnerScreen(winner, loser);
    }
    
    public void tieScreen(String winner, String loser) {
        designTieScreen(winner, loser);
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
            java.util.logging.Logger.getLogger(CheckersResultScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CheckersResultScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CheckersResultScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CheckersResultScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
}
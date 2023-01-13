/**
 * Holds methods that implements resources
 * author@ mahmed
 */
package ca.on.tdsb.student.checkers;

import java.io.*;
import javax.swing.*;

public class CheckersResource {
    
    static final File RES_PATH = new File(CheckersIntroScreen.class
            .getProtectionDomain().getCodeSource().getLocation().getPath());

    public static String getRes(String resName) {
        String iconRes = RES_PATH.toPath() + "/ca/on/tdsb/student/checkers/Resource/" + resName;
        return iconRes;
    }

    public static File getPath() {
      return RES_PATH;
    }

    public static void makePiece(JLabel gameSpace, String colour, boolean king) {
        String pieceImage;
        switch (colour) {
            case "orange":
                if (king) {
                    pieceImage = "orange king.png";
                } else {
                    pieceImage = "orange circle.png";
                }
                break;
            case "green":
                if (king) {
                    pieceImage = "green king.png";
                } else {
                    pieceImage = "green circle.png";
                }
                break;
            case "yellow":
                if (king) {
                    pieceImage = "yellow king.png";
                } else {
                    pieceImage = "yellow circle.png";
                }
                break;
            case "blue":
                if (king) {
                    pieceImage = "blue king.png";
                } else {
                    pieceImage = "blue circle.png";

                }
                break;
            case "purple":
                if (king) {
                    pieceImage = "purple king.png";
                } else {
                    pieceImage = "purple circle.png";
                }
                break;
            default:
                if (king) {
                    pieceImage = "pink king.png";
                } else {
                    pieceImage = "pink circle.png";
                }
                break;
        }
        gameSpace.setIcon(new ImageIcon(getRes(pieceImage)));
    }
}
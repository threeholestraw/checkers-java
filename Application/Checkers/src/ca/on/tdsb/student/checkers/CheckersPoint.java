/**
 * class to export player points to xml
 * author@   sgu
 */
package ca.on.tdsb.student.checkers;

import java.io.*;
import java.util.logging.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class CheckersPoint {
    static String xmlPathStr = CheckersResource.getPath() + "/ca/on/tdsb/student/checkers/PlayerMatchHistory"; // src: https://tinyurl.com/5ccxebwx
    File xmlPath = new File(xmlPathStr);
    // src: https://tinyurl.com/5ccxebwx
    
    public CheckersPoint() {
        xmlPath.mkdir(); // src: https://tinyurl.com/5ccxebwx 
    }
    
    public static void playerPoints(String player, int piecesWon, int piecesLost, int gamesWon, int gamesLost, int gamesTied) throws FileNotFoundException, IOException {
        File playerInfo = new File(xmlPathStr + "/" + player + ".xml");
        boolean exists = playerInfo.exists();

        if (!exists) {
            try {
                OutputStream fout = new FileOutputStream(xmlPathStr + "/" + player + ".xml");
                OutputStream bout = new BufferedOutputStream(fout);
                OutputStreamWriter out = new OutputStreamWriter(bout, "8859_1");

                out.write("<?xml version=\"1.0\" ");
                out.write("encoding=\"ISO-8859-1\"?>\r\n");
                out.write("<matchHistory>\r\n");
                out.write("<piecesWon>" + piecesWon + "</piecesWon>\r\n");
                out.write("<piecesLost>" + piecesLost + "</piecesLost>\r\n");
                out.write("<gamesWon>" + gamesWon + "</gamesWon>\r\n");
                out.write("<gamesLost>" + gamesLost + "</gamesLost>\r\n");
                out.write("<gamesTied>" + gamesTied + "</gamesTied>\r\n");
                out.write("</matchHistory>\r\n");

                out.flush();
                out.close();
            } catch (UnsupportedEncodingException e) {
                System.out.println("This VM does not support the Latin-1 character set.");
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } else {
            try {
                Node tempNode;
                Node tempCNode;
                int tempVal;
                int tempNum;

                String filepath = xmlPathStr + "/" + player + ".xml";
                DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
                Document doc = docBuilder.parse(filepath);

                tempNode = doc.getElementsByTagName("gamesWon").item(0);
                tempCNode = tempNode.getChildNodes().item(0);
                tempVal = Integer.parseInt(tempCNode.getNodeValue());
                //https://www.w3schools.com/xml/dom_nodes_get.asp
                //https://stackoverflow.com/questions/773012/getting-xml-node-text-value-with-java-dom/773048
                gamesWon += tempVal;
                
                tempNode = doc.getElementsByTagName("gamesLost").item(0);
                tempCNode = tempNode.getChildNodes().item(0);
                tempVal = Integer.parseInt(tempCNode.getNodeValue());
                gamesLost += tempVal;
                
                tempNode = doc.getElementsByTagName("gamesTied").item(0);
                tempCNode = tempNode.getChildNodes().item(0);
                tempVal = Integer.parseInt(tempCNode.getNodeValue());
                gamesTied += tempVal;
                
                Node piecesWonNode = doc.getElementsByTagName("piecesWon").item(0);
                piecesWonNode.setTextContent(piecesWon + "");
                
                Node piecesLostNode = doc.getElementsByTagName("piecesLost").item(0);
                piecesLostNode.setTextContent(piecesLost + "");
 
                Node gamesWonNode = doc.getElementsByTagName("gamesWon").item(0);
                gamesWonNode.setTextContent(gamesWon + "");

                Node gamesLostNode = doc.getElementsByTagName("gamesLost").item(0);
                gamesLostNode.setTextContent(gamesLost + "");

                Node gamesTiedNode = doc.getElementsByTagName("gamesTied").item(0);
                gamesTiedNode.setTextContent(gamesTied + "");

                Transformer transformer = TransformerFactory.newInstance().newTransformer();
                DOMSource source = new DOMSource(doc);
                StreamResult result = new StreamResult(new File(filepath));
                transformer.transform(source, result);
            } catch (TransformerException | SAXException | ParserConfigurationException ex) {
                Logger.getLogger(CheckersPoint.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
package repositories.core;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by astolarski on 14.01.17.
 */
public class ReadLangXMLFile {

    private HashMap<String, String> readFile(String path, String file){

        HashMap<String, String> messages = new HashMap<>();

        try {

            messages = readXML(path, file);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return messages;

    }

    private HashMap<String, String> readXML(String path, String file)
            throws ParserConfigurationException, SAXException, IOException {

        HashMap<String, String> messages = new HashMap<>();
        File fXmlFile = new File(path + file);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(fXmlFile);

        doc.getDocumentElement().normalize();

        NodeList nList = doc.getElementsByTagName("message");

        for (int temp = 0; temp < nList.getLength(); temp++) {

            Node nNode = nList.item(temp);;

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                Element eElement = (Element) nNode;

                messages.put(
                        eElement.getElementsByTagName("name").item(0).getTextContent(),
                        eElement.getElementsByTagName("value").item(0).getTextContent()
                );

            }
        }

        return messages;
    }

}

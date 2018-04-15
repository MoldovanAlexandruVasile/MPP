package ro.ubb.LabProb.Repository.XMLRepository.Reader;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import ro.ubb.socket.common.Domain.Problem;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class XMLReaderProblem {
    private String fileName;

    public XMLReaderProblem(String fileName) {
        this.fileName = fileName;
    }

    public List<Problem> loadEntities() {
        List<Problem> entities = new ArrayList<>();
        try {
            File fXmlFile = new File(fileName);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("Problem");
            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);
                Element element = (Element) nNode;
                Long nr = Long.valueOf(element.getElementsByTagName("ID").item(0).getTextContent());
                Problem s = new Problem(element.getElementsByTagName("Description").item(0).getTextContent());
                s.setId(nr);
                entities.add(s);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return entities;
    }


}
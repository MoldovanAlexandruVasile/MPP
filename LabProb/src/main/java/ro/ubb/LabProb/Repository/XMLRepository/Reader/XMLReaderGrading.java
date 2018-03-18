package ro.ubb.LabProb.Repository.XMLRepository.Reader;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import ro.ubb.LabProb.Domain.Grading;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class XMLReaderGrading {
    private String fileName;

    public XMLReaderGrading(String fileName) {
        this.fileName = fileName;
    }

    public List<Grading> loadEntities() {
        List<Grading> entities = new ArrayList<>();
        try {
            File fXmlFile = new File(fileName);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("Grading");
            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);
                Element element = (Element) nNode;
                Long nr = Long.valueOf(element.getElementsByTagName("ID").item(0).getTextContent());
                Grading s = new Grading(element.getElementsByTagName("AID").item(0).getTextContent(), Integer.parseInt(element.getElementsByTagName("Grade").item(0).getTextContent()));
                s.setId(nr);
                entities.add(s);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return entities;
    }


}
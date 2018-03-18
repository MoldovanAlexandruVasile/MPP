package ro.ubb.LabProb.Repository.XMLRepository.Reader;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import ro.ubb.LabProb.Domain.Student;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class XMLReaderStudent{
    private String fileName;

    public XMLReaderStudent(String fileName) {
        this.fileName = fileName;
    }

    public List<Student> loadEntities() {
        List<Student> entities = new ArrayList<>();
        try
        {
            File fXmlFile = new File(fileName);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("Student");
            for(int i = 0; i<nList.getLength(); i++)
            {
                Node nNode = nList.item(i);
                Element element = (Element) nNode;
                Long nr = Long.valueOf(element.getElementsByTagName("ID").item(0).getTextContent() );
                Student s = new Student(element.getElementsByTagName("SerialNumber").item(0).getTextContent(),element.getElementsByTagName("Name").item(0).getTextContent());
                s.setId(nr);
                entities.add(s);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return entities;
    }


}
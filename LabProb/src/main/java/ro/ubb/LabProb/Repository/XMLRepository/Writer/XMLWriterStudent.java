package ro.ubb.LabProb.Repository.XMLRepository.Writer;

import org.w3c.dom.*;
import org.xml.sax.SAXException;
import ro.ubb.socket.common.Domain.Student;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

public class XMLWriterStudent {
    private String fileName;

    public XMLWriterStudent(String fileName) {
        this.fileName = fileName;
    }

    public void save(Student std) {
        try {
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(fileName);
            Element root = document.getDocumentElement();

            Node studentNode = createStudentNode(document, root, std);
            root.appendChild(studentNode);

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(new DOMSource(document), new StreamResult(new File(fileName)));
        } catch (IOException | TransformerException | ParserConfigurationException | SAXException e) {
            e.printStackTrace();
        }
    }

    private Node createStudentNode(Document document, Element root, Student student) {
        Node studentNode = document.createElement("Student");

        appendStudentChildNode(document, studentNode, "ID", student.getId().toString());
        appendStudentChildNode(document, studentNode, "SerialNumber", student.getSerialNumber());
        appendStudentChildNode(document, studentNode, "Name", student.getName());

        return studentNode;
    }

    private static void appendStudentChildNode(Document document, Node parent, String tagName, String text) {
        Node node = document.createElement(tagName);
        node.setTextContent(text);

        parent.appendChild(node);
    }

}
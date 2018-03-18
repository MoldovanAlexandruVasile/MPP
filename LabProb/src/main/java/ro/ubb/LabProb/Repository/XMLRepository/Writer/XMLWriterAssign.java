package ro.ubb.LabProb.Repository.XMLRepository.Writer;

import org.w3c.dom.*;
import org.xml.sax.SAXException;
import ro.ubb.LabProb.Domain.Assign;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

public class XMLWriterAssign {
    private String fileName;

    public XMLWriterAssign(String fileName) {
        this.fileName = fileName;
    }

    public void save(Assign assign) {
        try {
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(fileName);
            Element root = document.getDocumentElement();

            Node assignNode = createAssignNode(document, root, assign);
            root.appendChild(assignNode);

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(new DOMSource(document), new StreamResult(new File(fileName)));
        } catch (IOException | TransformerException | ParserConfigurationException | SAXException e) {
            e.printStackTrace();
        }
    }

    private Node createAssignNode(Document document, Element root, Assign grading) {
        Node assignNode = document.createElement("Assign");

        appendAssignChildNode(document, assignNode, "ID", grading.getId().toString());
        appendAssignChildNode(document, assignNode, "SID", grading.getSID());
        appendAssignChildNode(document, assignNode, "PID", grading.getPID());

        return assignNode;
    }

    private static void appendAssignChildNode(Document document, Node parent, String tagName, String text) {
        Node node = document.createElement(tagName);
        node.setTextContent(text);

        parent.appendChild(node);
    }

}
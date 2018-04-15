package ro.ubb.LabProb.Repository.XMLRepository.Writer;

import org.w3c.dom.*;
import org.xml.sax.SAXException;
import ro.ubb.socket.common.Domain.Grading;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

public class XMLWriterGrading {
    private String fileName;

    public XMLWriterGrading(String fileName) {
        this.fileName = fileName;
    }

    public void save(Grading grading) {
        try {
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(fileName);
            Element root = document.getDocumentElement();

            Node gradingNode = createGradingNode(document, root, grading);
            root.appendChild(gradingNode);

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(new DOMSource(document), new StreamResult(new File(fileName)));
        } catch (IOException | TransformerException | ParserConfigurationException | SAXException e) {
            e.printStackTrace();
        }
    }

    private Node createGradingNode(Document document, Element root, Grading grading) {
        Node gradingNode = document.createElement("Grading");

        appendGradingChildNode(document, gradingNode, "ID", grading.getId().toString());
        appendGradingChildNode(document, gradingNode, "AID", grading.getAID());
        appendGradingChildNode(document, gradingNode, "Grade", String.valueOf(grading.getGrade()));

        return gradingNode;
    }

    private static void appendGradingChildNode(Document document, Node parent, String tagName, String text) {
        Node node = document.createElement(tagName);
        node.setTextContent(text);

        parent.appendChild(node);
    }

}
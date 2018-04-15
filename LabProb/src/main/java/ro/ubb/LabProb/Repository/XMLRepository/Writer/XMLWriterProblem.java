package ro.ubb.LabProb.Repository.XMLRepository.Writer;

import org.w3c.dom.*;
import org.xml.sax.SAXException;
import ro.ubb.socket.common.Domain.Problem;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

public class XMLWriterProblem {
    private String fileName;

    public XMLWriterProblem(String fileName) {
        this.fileName = fileName;
    }

    public void save(Problem problem) {
        try {
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(fileName);
            Element root = document.getDocumentElement();

            Node problemNode = createProblemNode(document, root, problem);
            root.appendChild(problemNode);

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(new DOMSource(document), new StreamResult(new File(fileName)));
        } catch (IOException | TransformerException | ParserConfigurationException | SAXException e) {
            e.printStackTrace();
        }
    }

    private Node createProblemNode(Document document, Element root, Problem problem) {
        Node problemNode = document.createElement("Problem");

        appendProblemChildNode(document, problemNode, "ID", problem.getId().toString());
        appendProblemChildNode(document, problemNode, "Description", problem.getDescription());

        return problemNode;
    }

    private static void appendProblemChildNode(Document document, Node parent, String tagName, String text) {
        Node node = document.createElement(tagName);
        node.setTextContent(text);

        parent.appendChild(node);
    }

}
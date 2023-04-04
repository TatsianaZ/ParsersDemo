package com.it_academy.parsers.dom;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.IOException;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class DOMParserUtils {

    public static DocumentBuilder createDocumentBuilder() {
        DocumentBuilderFactory factory =
                DocumentBuilderFactory.newInstance();
        try {
            return factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            System.out.println("ParserConfigurationException log");
            return null;
        }
    }

    public static NodeList getNodeList(Document document) {
        return document.getDocumentElement().getChildNodes();
    }

    public static Stream<Node> getNodeListStream(NodeList nodeList) {
        return IntStream.range(0, nodeList.getLength())
                .mapToObj(nodeList::item);
    }

    public static Node getXmlNodeByXPath(String xpath, Document document) {
        XPath xPath = XPathFactory.newInstance().newXPath();
        try {
            return (Node) xPath.compile(xpath).evaluate(document, XPathConstants.NODE);
        } catch (XPathExpressionException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static void transformXMLAfterUpdate(Document document, File file) {
        try {
            Transformer xmlFormer = TransformerFactory.newInstance().newTransformer();
            xmlFormer.transform(new DOMSource(document), new StreamResult(file));
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
    }

    public static Document parseXMLDocument(File file) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
            return builder.parse(file);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}

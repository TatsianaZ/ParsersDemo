package com.it_academy.parsers.dom;

import com.it_academy.entity.Employee;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DOMParserDemo {

    private static final String XML_PATH = "employee.xml";
    private static List<Employee> employees = new ArrayList<>();

    private static Employee setEmployeeWithXMLChildNodeValues(Employee employee, Node node) {
        String content = node
                .getLastChild()
                .getTextContent()
                .trim();
        switch (node.getNodeName()) {
            case "firstName":
                employee.setFirstName(content);
                break;
            case "lastName":
                employee.setLastName(content);
                break;
            case "location":
                employee.setLocation(content);
                break;
        }
        return employee;
    }

    private static void setEmployeesWithXMLNodeValues(NodeList nodeList) {
        DOMParserUtils.getNodeListStream(nodeList).forEach(node -> {
            if (node instanceof Element) {
                Employee employee = new Employee();
                employee.setId(node.getAttributes().
                        getNamedItem("id").getNodeValue());

                NodeList childNodes = node.getChildNodes();
                DOMParserUtils.getNodeListStream(childNodes).forEach(childNode -> {
                    if (childNode instanceof Element) {
                        setEmployeeWithXMLChildNodeValues(employee, childNode);
                    }
                });
                employees.add(employee);
            }
        });
    }

    public static void main(String[] args) {
        File file = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\employee.xml");
        Document document = DOMParserUtils.parseXMLDocument(file);
        NodeList nodeList = DOMParserUtils.getNodeList(document);
        printNodeNames(nodeList);

    /*    Node node = DOMParserUtils.getXmlNodeByXPath("//employees/employee[@id='1']", document);
        NodeList nodeListOfEmployeeOne = node.getChildNodes();

        printNodeNames(nodeListOfEmployeeOne);
*/
        setEmployeesWithXMLNodeValues(nodeList);
        System.out.println(employees.toString());
    }

    public static void printNodeNames(NodeList nodeList) {
        List<String> nodeNames = new ArrayList<>();
        DOMParserUtils.getNodeListStream(nodeList).forEach(node ->
                nodeNames.add(node.getNodeName())
        );
        nodeNames.forEach(System.out::println);
    }
}

package com.belkatechnologies.utils;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.stream.Format;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.ByteArrayOutputStream;

/**
 * Author: Nikita Khvorov
 * Date: 14.03.13
 */
public class XMLUtil {
    public static String serialize(Object object, Format format) throws Exception {
        Serializer serializer = new Persister(format);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        serializer.write(object, baos);
        return baos.toString();
    }

    public static String serialize(Object object) throws Exception {
        Format format = new Format(0);
        return serialize(object, format);
    }


    public static NodeList getNodesByName(Node node, String name) {
        NodeList nodeList;
        try {
            nodeList = ((Element) node).getElementsByTagName(name);
        } catch (Exception e) {
            return null;
        }
        return nodeList;
    }

    public static String getAttribute(Node node, String attributeName) {
        String attribute = "";

        try {
            Node namedItem = node.getAttributes().getNamedItem(attributeName);
            attribute = namedItem.getTextContent().trim();
        } catch (Exception e) {
            return "";
        }

        return attribute;
    }

    public static String getDataFromNode(NodeList node) throws Exception {
        Element e = (Element) node.item(0);
        if (e == null)
            return "";
        Node child = e.getFirstChild();
        if (child instanceof CharacterData) {
            CharacterData cd = (CharacterData) child;
            return cd.getData();
        }
        return "";
    }

}

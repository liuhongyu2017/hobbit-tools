package org.hobbit.core.tool.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.springframework.lang.Nullable;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * xpath解析xml
 *
 * @author lhy
 * @version 1.0.0 2023/9/3
 */
public class XmlUtil {

  private final XPath path;
  private final Document doc;

  private XmlUtil(InputSource inputSource)
      throws ParserConfigurationException, SAXException, IOException {
    DocumentBuilderFactory dbf = getDocumentBuilderFactory();
    DocumentBuilder db = dbf.newDocumentBuilder();
    doc = db.parse(inputSource);
    path = getXPathFactory().newXPath();
  }

  /**
   * 创建工具类
   *
   * @param inputSource inputSource
   * @return XmlUtil
   */
  private static XmlUtil create(InputSource inputSource) {
    try {
      return new XmlUtil(inputSource);
    } catch (ParserConfigurationException | SAXException | IOException e) {
      throw Exceptions.unchecked(e);
    }
  }

  /**
   * 转换工具类
   *
   * @param inputStream inputStream
   * @return XmlUtil
   */
  public static XmlUtil of(InputStream inputStream) {
    InputSource inputSource = new InputSource(inputStream);
    return create(inputSource);
  }

  /**
   * 转换工具类
   *
   * @param xmlStr xmlStr
   * @return XmlUtil
   */
  public static XmlUtil of(String xmlStr) {
    StringReader sr = new StringReader(xmlStr.trim());
    InputSource inputSource = new InputSource(sr);
    XmlUtil xmlUtil = create(inputSource);
    IoUtil.closeQuietly(sr);
    return xmlUtil;
  }

  /**
   * 转换路径
   *
   * @param expression 表达式
   * @param item       实体
   * @param returnType 返回类型
   * @return Object
   */
  private Object evalXPath(String expression, @Nullable Object item, QName returnType) {
    item = null == item ? doc : item;
    try {
      return path.evaluate(expression, item, returnType);
    } catch (XPathExpressionException e) {
      throw Exceptions.unchecked(e);
    }
  }

  /**
   * 获取String
   *
   * @param expression 路径
   * @return {String}
   */
  public String getString(String expression) {
    return (String) evalXPath(expression, null, XPathConstants.STRING);
  }

  /**
   * 获取Boolean
   *
   * @param expression 路径
   * @return {String}
   */
  public Boolean getBoolean(String expression) {
    return (Boolean) evalXPath(expression, null, XPathConstants.BOOLEAN);
  }

  /**
   * 获取Number
   *
   * @param expression 路径
   * @return {Number}
   */
  public Number getNumber(String expression) {
    return (Number) evalXPath(expression, null, XPathConstants.NUMBER);
  }

  /**
   * 获取某个节点
   *
   * @param expression 路径
   * @return {Node}
   */
  public Node getNode(String expression) {
    return (Node) evalXPath(expression, null, XPathConstants.NODE);
  }

  /**
   * 获取子节点
   *
   * @param expression 路径
   * @return NodeList
   */
  public NodeList getNodeList(String expression) {
    return (NodeList) evalXPath(expression, null, XPathConstants.NODESET);
  }


  /**
   * 获取String
   *
   * @param node       节点
   * @param expression 相对于node的路径
   * @return {String}
   */
  public String getString(Object node, String expression) {
    return (String) evalXPath(expression, node, XPathConstants.STRING);
  }

  /**
   * 获取
   *
   * @param node       节点
   * @param expression 相对于node的路径
   * @return {String}
   */
  public Boolean getBoolean(Object node, String expression) {
    return (Boolean) evalXPath(expression, node, XPathConstants.BOOLEAN);
  }

  /**
   * 获取
   *
   * @param node       节点
   * @param expression 相对于node的路径
   * @return {Number}
   */
  public Number getNumber(Object node, String expression) {
    return (Number) evalXPath(expression, node, XPathConstants.NUMBER);
  }

  /**
   * 获取某个节点
   *
   * @param node       节点
   * @param expression 路径
   * @return {Node}
   */
  public Node getNode(Object node, String expression) {
    return (Node) evalXPath(expression, node, XPathConstants.NODE);
  }

  /**
   * 获取子节点
   *
   * @param node       节点
   * @param expression 相对于node的路径
   * @return NodeList
   */
  public NodeList getNodeList(Object node, String expression) {
    return (NodeList) evalXPath(expression, node, XPathConstants.NODESET);
  }

  /**
   * 针对没有嵌套节点的简单处理
   *
   * @return map集合
   */
  public Map<String, String> toMap() {
    Element root = doc.getDocumentElement();
    Map<String, String> params = new HashMap<>(16);

    // 将节点封装成map形式
    NodeList list = root.getChildNodes();
    for (int i = 0; i < list.getLength(); i++) {
      Node node = list.item(i);
      if (node instanceof Element) {
        params.put(node.getNodeName(), node.getTextContent());
      }
    }
    return params;
  }

  private static volatile boolean preventedXXE = false;

  private static DocumentBuilderFactory getDocumentBuilderFactory()
      throws ParserConfigurationException {
    DocumentBuilderFactory dbf = XmlHelperHolder.documentBuilderFactory;
    if (!preventedXXE) {
      preventXXE(dbf);
    }
    return dbf;
  }

  /**
   * preventXXE
   */
  private static void preventXXE(DocumentBuilderFactory dbf) throws ParserConfigurationException {
    // This is the PRIMARY defense. If DTDs (doctypes) are disallowed, almost all XML entity attacks
    // are prevented
    // Xerces 2 only - http://xerces.apache.org/xerces2-j/features.html#disallow-doctype-decl
    dbf.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);

    // If you can't completely disable DTDs, then at least do the following:
    // Xerces 1 - http://xerces.apache.org/xerces-j/features.html#external-general-entities
    // Xerces 2 - http://xerces.apache.org/xerces2-j/features.html#external-general-entities

    // JDK7+ - http://xml.org/sax/features/external-general-entities
    dbf.setFeature("http://xml.org/sax/features/external-general-entities", false);

    // Xerces 1 - http://xerces.apache.org/xerces-j/features.html#external-parameter-entities
    // Xerces 2 - http://xerces.apache.org/xerces2-j/features.html#external-parameter-entities

    // JDK7+ - http://xml.org/sax/features/external-parameter-entities
    dbf.setFeature("http://xml.org/sax/features/external-parameter-entities", false);

    // Disable external DTDs as well
    dbf.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);

    // and these as well, per Timothy Morgan's 2014 paper: "XML Schema, DTD, and Entity Attacks"
    dbf.setXIncludeAware(false);
    dbf.setExpandEntityReferences(false);
    preventedXXE = true;
  }

  private static XPathFactory getXPathFactory() {
    return XmlHelperHolder.xPathFactory;
  }

  /**
   * 内部类单例
   */
  private static class XmlHelperHolder {

    private final static DocumentBuilderFactory documentBuilderFactory =
        DocumentBuilderFactory.newInstance();
    private final static XPathFactory xPathFactory = XPathFactory.newInstance();
  }
}

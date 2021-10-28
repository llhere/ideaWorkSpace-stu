package me;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.servlet.Servlet;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WebClassResources extends DefaultHandler {

    //Servlet集合
    Map<String,Object> servletsClassResourse = new HashMap<>();

    //Servlet映射
    Map<String,String> servletMapping = new HashMap<>();

    //xmlPath
    String xmlPath;

    /**
     * 加载XML文件
     * @param xmlPath
     * @return
     */
    public WebXml loadXml(String xmlPath) throws ParserConfigurationException, SAXException, IOException {

        //设置xml文件路径
        setXmlPath(xmlPath);

        //处理xml
        SAXParserFactory.newInstance().newSAXParser().parse(xmlPath,this);

        //处理结果给WebXml
        WebXml webXml = new WebXml(servletsClassResourse,servletMapping);

        return webXml;

    }

    String currentServlet = null;
    String currentServletMapping = null;
    String currentParam = null;
    String qName = null;

    // 开始解析文档，即开始解析XML根元素时调用该方法
    @Override
    public void startDocument() throws SAXException {
        System.out.println("--开始解析: " + this.xmlPath);
    }

    // 开始解析每个元素时都会调用该方法
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        // 判断正在解析的元素是不是开始解析的元素
        this.qName = qName;
    }

    // 解析到每个元素的内容时会调用此方法
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String currentValue = new String(ch, start, length);
        // 如果内容不为空和空格，也不是换行符则将该元素名和值和存入map中
        if (currentValue == null || currentValue.trim().equals("") || currentValue.trim().equals("\n")) {
            return;
        }
        if ("servlet-name".equals(qName)) {
            currentServlet = currentValue;
            currentServletMapping = currentValue;
        } else if (qName.equals("servlet-class")) {
            // servlet信息
            String servletClass = currentValue;
            servletsClassResourse.put(currentServlet, servletClass);
        } else if (qName.equals("param-name")) {
            currentParam = currentValue;
        } else if (qName.equals("param-value")) {
            String paramValue = currentValue;
            // servlet param 参数
            HashMap<String, String> params = new HashMap<>();
            params.put(currentParam, paramValue);
            // servletParam.put(currentServlet, params);
        } else if ("servlet-name".equals(qName)) {
            currentServletMapping = currentValue;
        } else if (qName.equals("url-pattern")) {
            String urlPattern = currentValue;
            // url映射
            this.servletMapping.put(urlPattern, currentServletMapping);
        }
    }

    // 每个元素结束的时候都会调用该方法
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
    }

    // 结束解析文档，即解析根元素结束标签时调用该方法
    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }


    public Map<String, Object> getServletsClassResourse() {
        return servletsClassResourse;
    }

    public void setServletsClassResourse(Map<String, Object> servletsClassResourse) {
        this.servletsClassResourse = servletsClassResourse;
    }

    public Map<String, String> getServletMapping() {
        return servletMapping;
    }

    public void setServletMapping(Map<String, String> servletMapping) {
        this.servletMapping = servletMapping;
    }

    public String getXmlPath() {
        return xmlPath;
    }

    public void setXmlPath(String xmlPath) {
        this.xmlPath = xmlPath;
    }

}

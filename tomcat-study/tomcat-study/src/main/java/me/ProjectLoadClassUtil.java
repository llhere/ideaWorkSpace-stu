package me;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.servlet.Servlet;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ProjectLoadClassUtil extends DefaultHandler {


    /**
     * 加载资源文件（xml，servvlet）
     * @param projectPath
     * @return
     * @throws IOException
     * @throws SAXException
     * @throws ParserConfigurationException
     * @throws ClassNotFoundException
     */
    public static Map<String,WebXml> load(String projectPath) throws IOException, SAXException, ParserConfigurationException, ClassNotFoundException, InstantiationException, IllegalAccessException {

        Map<String,WebXml> webXmlRescourse = new HashMap<>();

        //获取所有项目路径
        File[] projectDirectiory = new File(projectPath + "\\webapps").listFiles(file -> file.isDirectory());

        //项目资源文件加载进JVM
        for (File file: projectDirectiory) {

            //读取xml
            WebXml webXml = new WebClassResources().loadXml(file.getPath() + "\\WEB-INF\\web.xml");

            //加载servlet进JVM
            webXml.setProjectPath(file.getPath());
            webXml.loadClass();

            webXmlRescourse.put(file.getName(),webXml);
        }

        return webXmlRescourse;
    }



}
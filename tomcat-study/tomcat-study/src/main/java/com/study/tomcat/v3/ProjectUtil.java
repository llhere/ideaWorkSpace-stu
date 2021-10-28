package com.study.tomcat.v3;

import javax.servlet.Servlet;
import java.io.File;
import java.io.FileFilter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;

public class ProjectUtil {
    /**
     * 加载所有项目
     */
    public static Map<String, WebXml> load() throws Exception {
        Map<String, WebXml> configInfo = new HashMap<>();

        String webapps = "D:\\tomcat-tony\\webapps";

        // 0、 war自动解压
        // 1、 webapps子文件夹算是一个项目
        File[] projects = new File(webapps).listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.isDirectory();
            }
        });
        for (File projectFile : projects) {
            // 2、读取每个项目XML文件
            WebXml webXml = new WebXmlConfigUtil().loadXml(projectFile.getPath() + "\\WEB-INF\\web.xml");
            webXml.projectPath = projectFile.getPath();
            // 3、 缺少~~~ 不能调用servlet
            webXml.loadServlet();
            configInfo.put(projectFile.getName(), webXml);
        }
        return configInfo;
    }

    public class WebXml {
        /**
         * 项目文件夹地址
         */
        public String projectPath = null;
        /**
         * Servlet定义
         */
        public Map<String, Object> servlets = new HashMap<>();
        /**
         * servlet 映射
         */
        public Map<String, String> servletMapping = new HashMap<>();
        /**
         * servlet集合
         */
        public Map<String, Servlet> servletInstances = new HashMap<>();

        public void loadServlet() throws Exception {
            // 每一个项目，类加载器，去加载指定位置的class信息
            URL classUrl = new URL("file:" + projectPath + "\\WEB-INF\\classes\\");
            URLClassLoader servletClassLoader = new URLClassLoader(new URL[]{classUrl});

            // 加载该项目中的servlet class
            for (Map.Entry<?, ?> entry : this.servlets.entrySet()) {
                String servletClassName = entry.getValue().toString();
                String servletName = entry.getKey().toString();
                // 1、加载到JVM
                Class<?> servletClass = servletClassLoader.loadClass(servletClassName);
                // 2、 实例化
                Servlet servlet = (Servlet) servletClass.newInstance();
                // 3、 保存起来，以后要用
                servletInstances.put(servletName, servlet);
            }
        }

    }
}

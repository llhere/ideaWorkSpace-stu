package me;

import javax.servlet.Servlet;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class WebXml {

    //Servlet集合
    Map<String,Object> servletsClassResourse = new HashMap<>();

    //Servlet映射
    Map<String,String> servletMapping = new HashMap<>();

    //Servlet实例
    Map<String,Servlet> servletInstance = new HashMap<>();

    //xmlPath
    String projectPath;

    /**
     * 把Servlet加载进JVM
     */
    public void loadClass() throws MalformedURLException, ClassNotFoundException, IllegalAccessException, InstantiationException {

        //获取class文件地址
        URLClassLoader urlClassLoader = new URLClassLoader(new URL[]{new URL("file:" + projectPath + "\\WEB-INF\\classes\\")});

        //加载进JVM
        Iterator<Map.Entry<String, Object>> iterator = servletsClassResourse.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String, Object> next = iterator.next();
            String servletClassName = next.getKey();               //servletClassName
            String servletName = next.getValue().toString();      //servletName

            //loadClass至JVM，并实例化保存至map
            Class<?> loadServletClass = urlClassLoader.loadClass(servletName);
            Servlet servlet = (Servlet) loadServletClass.newInstance();
            servletInstance.put(servletClassName,servlet);

        }
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

    public String getProjectPath() {
        return projectPath;
    }

    public void setProjectPath(String projectPath) {
        this.projectPath = projectPath;
    }

    public WebXml(Map<String,Object> servletsClassResourse, Map<String,String> servletMapping){
        this.servletsClassResourse = servletsClassResourse;
        this.servletMapping = servletMapping;
    }

    public Map<String, Servlet> getServletInstance() {
        return servletInstance;
    }

    public void setServletInstance(Map<String, Servlet> servletInstance) {
        this.servletInstance = servletInstance;
    }
}

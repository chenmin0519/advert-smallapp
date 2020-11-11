package com.advert.smallapp;

import com.advert.smallapp.utils.ClassUtil;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.util.StringUtils;

import javax.persistence.Id;
import java.io.File;
import java.io.FileFilter;
import java.lang.reflect.Field;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class TestGannerSqlUtil {
    public static void main(String[] args) {
        System.out.println("开始");
        Set<Class<?>> clazzs = TestGannerSqlUtil.getClassSet("com.advert.smallapp.gannersql");
        clazzs.forEach(clazz -> {
            String sssql = "drop table if exists ";
            String sql = "CREATE TABLE ";
            String className = clazz.getName();
            className = className.split("\\.")[className.split("\\.").length - 1];
            String tableName = fomatHumpName(className);
            sssql += tableName+";";
            sql += "`" + tableName + "` (";
//            System.out.println(tableName);
            Field[] parentFields =  clazz.getSuperclass().getDeclaredFields();
            Field[] chrildFields = clazz.getDeclaredFields();
            Field[] fields = ArrayUtils.addAll(parentFields,chrildFields);
            String pramrikey = null;
            for(Field field : fields){
                String fieldNmae = fomatHumpName(field.getName());
                if(field.getType().equals(Integer.class)){
                    sql += "`" + fieldNmae + "` int(11) DEFAULT NULL,";
                }else if(field.getType().equals(String.class)){
                    sql += "`" + fieldNmae + "` varchar(255) DEFAULT NULL,";
                }else if(field.getType().equals(Long.class)){
                    if(field.getAnnotation(Id.class) != null){
                        sql += "`" + fieldNmae + "`  bigint(20) NOT NULL AUTO_INCREMENT,";
                        pramrikey = fieldNmae;
                    }else{
                        sql += "`" + fieldNmae + "`  bigint(20) NOT NULL,";
                    }
                }else if(field.getType().equals(Boolean.class)){
                    sql += "`" + fieldNmae + "`  tinyint(1) DEFAULT NULL,";
                }else if(field.getType().equals(Date.class)){
                    if(fieldNmae.equals("create_time")){
                        sql += "`" + fieldNmae + "`  timestamp NULL DEFAULT CURRENT_TIMESTAMP,";
                    }else{
                        sql += "`" + fieldNmae + "`  timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,";
                    }

                }
            }

            if(pramrikey != null){
                sql += "PRIMARY KEY (`" + pramrikey + "`)";
            }else{
                sql = sql.substring(0,sql.length()-1);
            }
            sql += ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;";
//            System.out.println(sql);
            System.out.println(sssql);
        });
    }

    /**
     * 格式化驼峰命名为下划线
     * @param className
     * @return
     */
    private static String fomatHumpName(String className) {
        char[] classNameChar = className.toCharArray();
        String tableName = "";
        for(int i = 0;i <classNameChar.length;i ++){
            if (Character.isUpperCase(classNameChar[i])) {
                if(i == 0){
                    tableName += classNameChar[i];
                    tableName = tableName.toLowerCase();
                }else{
                    String UpperCaseChar = classNameChar[i] + "";
                    tableName += "_" + UpperCaseChar.toLowerCase();
                }
            }else{
                tableName += classNameChar[i];
            }
        }
        return tableName;
    }

    /**
     * 获取指定包名下的所有类
     */
    public static Set<Class<?>> getClassSet(String packageName) {
        Set<Class<?>> classSet = new HashSet<Class<?>>();
        try {
            Enumeration<URL> urls = getClassLoader().getResources(packageName.replace(".", "/"));
            while (urls.hasMoreElements()) {
                URL url = urls.nextElement();
                if (url != null) {
                    String protocol = url.getProtocol();
                    if (protocol.equals("file")) {
                        String packagePath = url.getPath().replaceAll("%20", " ");
                        addClass(classSet, packagePath, packageName);
                    } else if (protocol.equals("jar")) {
                        JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();
                        if (jarURLConnection != null) {
                            JarFile jarFile = jarURLConnection.getJarFile();
                            if (jarFile != null) {
                                Enumeration<JarEntry> jarEntries = jarFile.entries();
                                while (jarEntries.hasMoreElements()) {
                                    JarEntry jarEntry = jarEntries.nextElement();
                                    String jarEntryName = jarEntry.getName();
                                    if (jarEntryName.endsWith(".class")) {
                                        String className = jarEntryName.substring(0, jarEntryName.lastIndexOf(".")).replaceAll("/", ".");
                                        doAddClass(classSet, className);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return classSet;
    }

    private static void addClass(Set<Class<?>> classSet, String packagePath, String packageName) {
        File[] files = new File(packagePath).listFiles(new FileFilter()  {
            public boolean accept(File file) {
                return (file.isFile() && file.getName().endsWith(".class")) || file.isDirectory();
            }
        });
        for (File file : files) {
            String fileName = file.getName();
            if (file.isFile()) {
                String className = fileName.substring(0, fileName.lastIndexOf("."));
                if (!StringUtils.isEmpty(packageName)) {
                    className = packageName + "." + className;
                }
                doAddClass(classSet, className);
            } else {
                String subPackagePath = fileName;
                if (!StringUtils.isEmpty(packagePath)) {
                    subPackagePath = packagePath + "/" + subPackagePath;
                }
                String subPackageName = fileName;
                if (!StringUtils.isEmpty(packageName)) {
                    subPackageName = packageName + "." + subPackageName;
                }
                addClass(classSet, subPackagePath, subPackageName);
            }
        }
    }

    private static void doAddClass(Set<Class<?>> classSet, String className) {
        Class<?> cls = loadClass(className, false);
        classSet.add(cls);
    }
    /**
     * 获取类加载器
     */
    public static ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    /**
     * 加载类
     */
    public static Class<?> loadClass(String className, boolean isInitialized) {
        Class<?> cls;
        try {
            cls = Class.forName(className, isInitialized, getClassLoader());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return cls;
    }

    /**
     * 加载类（默认将初始化类）
     */
    public static Class<?> loadClass(String className) {
        return loadClass(className, true);
    }

}

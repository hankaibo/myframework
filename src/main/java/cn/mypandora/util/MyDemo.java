/**
 * Copyright © 2015.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */package cn.mypandora.util;

import org.apache.commons.configuration.PropertiesConfiguration;

/**
 * Created by kaibo on 2015/5/26.
 * desc
 */
/**
 * <p>User: kaibo
 * <p>Date: 2015/7/17
 * <p>Version: 1.0
 */
public class MyDemo {

    public static void main(String[] args) throws Exception{
        PropertiesConfiguration  config=new PropertiesConfiguration("D:/captcha.properties");
        String foo="dc=汉字啊,dc=北京，dc=中国";
        String bar=foo.replaceAll("，",",");

        config.setProperty("aaa",bar);
        config.setProperty("bbb","中文啊");

        String[] aaaArr=config.getStringArray("aaa");
        String bbb=config.getString("bbb");
        String ccc=config.getString("ccc");

//        config.setEncoding("UTF-8");
        config.save();

        System.out.println(bbb);
        StringBuffer aaa=new StringBuffer();
        for(String s:aaaArr){
            aaa.append(s);
            aaa.append(",");
        }
        System.out.println(aaa.toString().substring(0, aaa.length() - 1));
        System.out.println(ccc);


//        Set<String> set1=new HashSet<>();
//        Set<String> set2=new HashSet<>();
//
//        set1.add("1");
//        set1.add("2");
//        set1.add("3");
//
//        set2.add("3");
//        set2.add("4");
//
//        set1.removeAll(set2);
//
//        for (String foo:set1){
//            System.out.println(foo);
//        }
//
//        for (String foo:set2){
//            System.out.println(foo);
//        }
    }
}

package com.ms.rough;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestProperties {

    public static void main(String[] args) throws FileNotFoundException, IOException {

        String prop = System.getProperty("user.dir");
        System.out.println(prop);
        Properties config = new Properties();
        Properties OR = new Properties();
        FileInputStream fis = new FileInputStream(prop+"\\src\\test\\properties\\config.properties");
        config.load(fis);
        fis = new FileInputStream(prop+"\\src\\test\\properties\\OR.properties");
        OR.load(fis);










    }
}

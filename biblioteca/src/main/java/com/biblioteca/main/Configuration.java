package com.biblioteca.main;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuration {

    private Properties properties;

    public Configuration() {
        try {
            InputStream input = new FileInputStream("config.properties");
            properties = new Properties();
            properties.load(input);
        } catch (IOException e){
            throw new IllegalStateException("Impossibile caricare il file di configurazione");
        }
    }

    public Properties getProperties() {
        return properties;
    }
}
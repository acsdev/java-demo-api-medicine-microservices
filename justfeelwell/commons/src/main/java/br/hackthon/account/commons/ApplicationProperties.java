package br.hackthon.account.commons;

import java.io.InputStream;
import java.util.Properties;

public class ApplicationProperties {

    public static Properties getProperties() throws Exception {

        InputStream resource = DataSource.class.getClassLoader().getResourceAsStream("module.properties");
        Properties properties = new Properties();
        properties.load( resource );

        return properties;
    }
}

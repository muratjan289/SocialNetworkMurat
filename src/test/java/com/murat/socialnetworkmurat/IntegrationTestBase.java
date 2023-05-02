package com.murat.socialnetworkmurat;


import com.murat.socialnetworkmurat.initializer.Postgres;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Sql("/sql/role.sql")
@ActiveProfiles("test")
@SpringBootApplication
@ContextConfiguration(initializers = {
        Postgres.Initializer.class
})
@Transactional
public abstract class IntegrationTestBase {

    @BeforeAll
    static void init(){
//        String configFile = "/home/murat/IdeaProjects/SocialNetworkMurat/src/test/resources/application-test.yaml";
//        // загрузка файла конфигурации
//        Properties props = new Properties();
//        try (InputStream is = new FileInputStream(configFile)) {
//            props.load(is);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        // установка параметров конфигурации
//        System.setProperty("param1", props.getProperty("param1"));
//        System.setProperty("param2", props.getProperty("param2"));
        Postgres.container.start();
    }


}

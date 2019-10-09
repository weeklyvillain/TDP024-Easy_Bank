package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import se.liu.ida.tdp024.account.data.impl.db.facade.PersonEntityFacadeDB;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;


@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
        app.setDefaultProperties(Collections
           .singletonMap("server.port", "8060"));
        app.run(args);


        System.out.println("running");
        List<String> personList = new ArrayList<String>();
        personList.add("Jakob Pogulis");
        personList.add("Xena");
        personList.add("Marcus Bendtsen");
        personList.add("Zorro");
        personList.add("Q");
        for (String person : personList) {
            PersonEntityFacadeDB.create(person);
        }
    }
}

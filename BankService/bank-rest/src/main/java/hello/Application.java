package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import se.liu.ida.tdp024.bank.data.impl.db.facade.BankEntityFacadeDB;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
        app.setDefaultProperties(Collections
           .singletonMap("server.port", "8070"));
        app.run(args);

        List<String> bankList = new ArrayList<String>();
        bankList.add("SWEDBANK");
        bankList.add("IKANOBANKEN");
        bankList.add("JPMORGAN");
        bankList.add("NORDEA");
        bankList.add("CITIBANK");
        bankList.add("HANDELSBANKEN");
        bankList.add("SBAB");
        bankList.add("HSBC");
        bankList.add("NORDNET");
        for (String bank : bankList) {
            BankEntityFacadeDB.create(bank);
        }
    }
}

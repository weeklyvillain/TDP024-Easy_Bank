package hello;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import se.liu.ida.tdp024.bank.data.api.entity.Bank;
import se.liu.ida.tdp024.bank.data.impl.db.facade.BankEntityFacadeDB;
import se.liu.ida.tdp024.bank.logic.api.facade.BankLogicFacade;
import se.liu.ida.tdp024.bank.logic.impl.facade.BankLogicFacadeImpl;
import java.util.List;
import com.google.gson.Gson;



@RestController
@RequestMapping("bank")
public class BankController {
    // --- Here we choose the implementations of the logic and data layer --- //
    private final BankLogicFacade bankLogicFacade =
            new BankLogicFacadeImpl(new BankEntityFacadeDB());
    //----------------------------------------------------------------------- //

// SKA TAS BORT
/*
    @RequestMapping(path="/create", produces="application/json")
    public ResponseEntity create(@RequestParam String name) {
      System.out.println("Name: " + name);
      long key = bankLogicFacade.create(name);
      String json = new Gson().toJson(key);
      return new ResponseEntity(json, HttpStatus.OK);
    }
    */

    @RequestMapping(path="/list", produces="application/json")
    public ResponseEntity list() {
      List<Bank> bankList = bankLogicFacade.list();

      String json = new Gson().toJson(bankList);
      return new ResponseEntity(json,HttpStatus.OK);
    }

    @RequestMapping(path="/find", produces = "application/json", params={"name"})
    public ResponseEntity find(@RequestParam String name) {
      Bank bank = bankLogicFacade.find(name);
      String json = new Gson().toJson(bank);
      return new ResponseEntity(json, HttpStatus.OK);
    }

    @RequestMapping(path="/find", produces = "application/json", params={"key"})
    public ResponseEntity find(@RequestParam long key) {
      Bank bank = bankLogicFacade.find(key);
      String json = new Gson().toJson(bank);
      return new ResponseEntity(json, HttpStatus.OK);
    }



}

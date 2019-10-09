package hello;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import se.liu.ida.tdp024.account.data.api.entity.Person;
import se.liu.ida.tdp024.account.data.impl.db.facade.PersonEntityFacadeDB;
import se.liu.ida.tdp024.account.logic.api.facade.PersonLogicFacade;
import se.liu.ida.tdp024.account.logic.impl.facade.PersonLogicFacadeImpl;
import java.util.List;
import com.google.gson.Gson;



@RestController
@RequestMapping("person")
public class PersonController {
    // --- Here we choose the implementations of the logic and data layer --- //
    private final PersonLogicFacade personLogicFacade =
            new PersonLogicFacadeImpl(new PersonEntityFacadeDB());
    //----------------------------------------------------------------------- //

// SKA TAS BORT
/*
    @RequestMapping(path="/create", produces="application/json")
    public ResponseEntity create(@RequestParam String name) {
      System.out.println("Name: " + name);
      long key = personLogicFacade.create(name);
      String json = new Gson().toJson(key);
      return new ResponseEntity(json, HttpStatus.OK);
    }
    */

    @RequestMapping(path="/list", produces="application/json")
    public ResponseEntity list() {
      List<Person> personList = personLogicFacade.list();

      String json = new Gson().toJson(personList);
      return new ResponseEntity(json,HttpStatus.OK);
    }

    @RequestMapping(path="/find", produces = "application/json", params={"name"})
    public ResponseEntity find(@RequestParam String name) {
      List<Person> personList = personLogicFacade.find(name);
      String json = new Gson().toJson(personList);
      return new ResponseEntity(json, HttpStatus.OK);
    }

    @RequestMapping(path="/find", produces = "application/json", params={"key"})
    public ResponseEntity find(@RequestParam long key) {
      Person person = personLogicFacade.find(key);
      String json = new Gson().toJson(person);
      return new ResponseEntity(json, HttpStatus.OK);
    }



}

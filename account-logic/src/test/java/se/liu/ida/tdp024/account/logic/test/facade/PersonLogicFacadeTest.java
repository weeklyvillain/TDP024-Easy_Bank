package se.liu.ida.tdp024.account.logic.test.facade;

import org.junit.After;
import org.junit.Test;
import se.liu.ida.tdp024.account.data.api.util.StorageFacade;
import se.liu.ida.tdp024.account.logic.api.facade.PersonLogicFacade;

public class PersonLogicFacadeTest {


    //--- Unit under test ---//
    public PersonLogicFacade personLogicFacade;
    public StorageFacade storageFacade;

    @After
    public void tearDown() {
      if (storageFacade != null)
        storageFacade.emptyStorage();
    }



    @Test
    public void testCreate() {
    }
}

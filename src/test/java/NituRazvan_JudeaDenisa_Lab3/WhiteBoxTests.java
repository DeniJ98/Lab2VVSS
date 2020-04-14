package NituRazvan_JudeaDenisa_Lab3;

import static org.junit.Assert.assertEquals;


import NituRazvan_JudeaDenisa_Lab3.domain.Tema;
import NituRazvan_JudeaDenisa_Lab3.repository.NotaXMLRepo;
import NituRazvan_JudeaDenisa_Lab3.repository.StudentXMLRepo;
import NituRazvan_JudeaDenisa_Lab3.repository.TemaXMLRepo;
import NituRazvan_JudeaDenisa_Lab3.service.Service;
import NituRazvan_JudeaDenisa_Lab3.validation.NotaValidator;
import NituRazvan_JudeaDenisa_Lab3.validation.StudentValidator;
import NituRazvan_JudeaDenisa_Lab3.validation.TemaValidator;
import NituRazvan_JudeaDenisa_Lab3.validation.parameters.ParametersValidator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * @author Xps 9560
 */
public class WhiteBoxTests {
    StudentValidator studentValidator = new StudentValidator();
    TemaValidator temaValidator = new TemaValidator();
    String filenameStudent = "files/Studenti.xml";
    String filenameTema = "files/Teme.xml";
    String filenameNota = "files/Note.xml";


    StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
    TemaXMLRepo temaXMLRepository;
    NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
    NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);
    ParametersValidator parametersValidator = new ParametersValidator();
    Service service;

    String nrTema;
    String descriere;
    String deadline;
    String primire;

    @Before
    public void init(){
        nrTema = "8931";
        descriere = "descriere";
        deadline = "5";
        primire = "10";
        temaXMLRepository =  new TemaXMLRepo(filenameTema);
        service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator, parametersValidator);
    }

    @After
    public void reset()
    {
        if (service.findTema(nrTema) != null)
            service.deleteTema(nrTema);
    }


    // WHITE-BOX TESTING - FOR LAB3 In Class!!!!
    @Test
    public void Deadline_TC_1()
    {
        deadline= "7";
        assertEquals(TestTema(), true);
    }

    @Test
    public void Deadline_TC_2()
    {
        deadline = "18";
        assertEquals(TestTema(), false);
    }


    private Boolean TestTema()
    {
        service.addTema(nrTema, descriere, deadline, primire);
        Tema ret = service.findTema(nrTema);
        if(ret == null)
            return false;

        return ret.getID().equals(nrTema) && ret.getDescriere().equals(descriere) &&
                Integer.toString(ret.getDeadline()).equals(deadline) && Integer.toString(ret.getPrimire()).equals(primire) ;
    }

}

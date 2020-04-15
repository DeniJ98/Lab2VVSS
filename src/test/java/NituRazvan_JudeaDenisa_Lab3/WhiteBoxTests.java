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
        if(nrTema != null && !nrTema.equals("")) {
            if (service.findTema(nrTema) != null)
                service.deleteTema(nrTema);
        }
    }


    // WHITE-BOX TESTING - FOR LAB3 In Class!!!!

    @Test
    public void TC_1()  // Deadline-ul trebuie sa fie un numar!
    {
        deadline = "17";
        assertEquals(TestTema(), false);
    }
    @Test
    public void TC_2() // Primirea trebuie sa fie un numar!
    {
        primire = "3d";
        assertEquals(TestTema(), false);
    }
    @Test
    public void TC_3() // Numar tema invalid! (empty string)
    {
        nrTema = "";
        assertEquals(TestTema(), false);
    }
    @Test
    public void TC_4() // Numar tema invalid! (null)
    {
        nrTema = null;
        assertEquals(TestTema(), false);
    }
    @Test
    public void TC_5()  // Descriere invalida!
    {
        descriere = "";
        assertEquals(TestTema(), false);
    }

    @Test
    public void TC_6() // Deadline-ul trebuie sa fie intre 1-14! (mai mare ca 14)
    {
        deadline = "35";
        assertEquals(TestTema(), false);
    }
    @Test
    public void TC_7() // Deadline-ul trebuie sa fie intre 1-14! (mai mic ca 1)
    {
        deadline = "0";
        assertEquals(TestTema(), false);
    }

    @Test
    public void TC_8()  // Saptamana primirii trebuie sa fie intre 1-14. (mai mare ca 14)
    {
        primire = "17";
        assertEquals(TestTema(), false);
    }
    @Test
    public void TC_9()  // Saptamana primirii trebuie sa fie intre 1-14. (mai mic ca 1)
    {
        primire = "0";
        assertEquals(TestTema(), false);
    }
    @Test
    public void TC_10() // exista deja tema
    {
        service.addTema(nrTema, descriere, deadline, primire);
        assertEquals(TestTema(), false);
    }
    @Test
    public void TC_11() // tema corecta adaugata
    {
        assertEquals(TestTema(), true);
    }






    private Boolean TestTema()
    {
        Tema ret = service.addTema(nrTema, descriere, deadline, primire);
        return ret != null;
    }

}

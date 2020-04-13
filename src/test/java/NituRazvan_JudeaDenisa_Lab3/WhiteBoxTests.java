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


    StudentXMLRepo studentXMLRepository;
    TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
    NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
    NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);
    ParametersValidator parametersValidator = new ParametersValidator();
    Service service;
    Tema tema = new Tema("8931", "descriere", 5, 10);

    @Before
    public void init(){
        studentXMLRepository = new StudentXMLRepo(filenameStudent);
        service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator, parametersValidator);
    }

    @After
    public void reset()
    {
        if (service.findTema(tema.getID()) != null)
            service.deleteTema(tema.getID());
    }


    // WHITE-BOX TESTING - FOR LAB3 In Class!!!!
    @Test
    public void Deadline_TC_1()
    {
        tema.setDeadline(7);
        assertEquals(TestTema(tema), true);
    }

    @Test
    public void Deadline_TC_2()
    {
        tema.setDeadline(18);
        assertEquals(TestTema(tema), false);
    }


    private Boolean TestTema(Tema tema)
    {
        service.addTema(tema);
        return service.findTema(tema.getID()) == tema;
    }

}

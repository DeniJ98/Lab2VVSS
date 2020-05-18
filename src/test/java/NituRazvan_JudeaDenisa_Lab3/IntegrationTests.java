package NituRazvan_JudeaDenisa_Lab3;

import NituRazvan_JudeaDenisa_Lab3.domain.Nota;
import NituRazvan_JudeaDenisa_Lab3.domain.Student;
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

import java.time.LocalDate;

import static org.junit.Assert.assertNotEquals;

public class IntegrationTests {
    StudentValidator studentValidator = new StudentValidator();
    TemaValidator temaValidator = new TemaValidator();

    String filenameStudent = "files/Studenti.xml";
    String filenameTema = "files/Teme.xml";
    String filenameNota = "files/Note.xml";


    StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
    TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);

    NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
    NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);

    ParametersValidator parametersValidator = new ParametersValidator();
    Service service;

    String idStudent1 = "nrie1111";
    String idStudent2 = "nrie2222";
    String idStudent3 = "nrie3333";

    String idTema1 = "1111";
    String idTema2 = "2222";
    String idTema3  = "3333";

    String idNota1 = "1111";
    String idNota2 = "2222";
    String idNota3  = "3333";

    String numeStudent = "numeStudent";
    String descriere = "descriere";
    String deadline = "14";
    String primire = "13";
    String grupa = "934";
    String email = "jdac2345@scs.ubbcluj.ro";
    double nota;
    @Before
    public void init(){

        studentXMLRepository = new StudentXMLRepo(filenameStudent);
        temaXMLRepository =  new TemaXMLRepo(filenameTema);
        notaXMLRepository =  new NotaXMLRepo(filenameNota);
        service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator, parametersValidator);
    }

    @After
    public void reset()
    {
        if (service.findTema(idTema1) != null)
            service.deleteTema(idTema1);
        if (service.findTema(idTema2) != null)
            service.deleteTema(idTema2);
        if (service.findTema(idTema3) != null)
            service.deleteTema(idTema3);

        if (service.findTema(idStudent1) != null)
            service.deleteTema(idStudent1);
        if (service.findTema(idStudent2) != null)
            service.deleteTema(idStudent2);
        if (service.findTema(idStudent3) != null)
            service.deleteTema(idStudent3);

        if (service.findTema(idNota1) != null)
            service.deleteTema(idNota1);
        if (service.findTema(idNota2) != null)
            service.deleteTema(idNota2);
        if (service.findTema(idNota3) != null)
            service.deleteTema(idNota3);
    }
    @Test
    public void TestAddStudent()
    {
        Student retStudent = service.addStudent(idStudent1, numeStudent, grupa, email);

        assertNotEquals(retStudent, null);

    }


    @Test
    public void TestAddNota()
    {
        Tema retTema = service.addTema(idTema1, descriere, deadline, primire);
        assertNotEquals(retTema, null);
    }

    @Test
    public void TestAddTema()
    {
        Student retStudent = service.addStudent(idStudent2, numeStudent, grupa, email);
        Tema retTema = service.addTema(idTema2, descriere, deadline, primire);
        Nota notaObj = new Nota(idNota2, retStudent.getID(), idTema2, nota, LocalDate.now());
        Nota retNota = service.addNota(notaObj, "");
        assertNotEquals(retNota, null);
    }

    @Test
    public void TestAll()
    {
        Student retStudent = service.addStudent(idStudent2, numeStudent, grupa, email);
        Tema retTema = service.addTema(idTema2, descriere, deadline, primire);
        Nota notaObj = new Nota(idNota2, retStudent.getID(), idTema2, nota, LocalDate.now());
        Nota retNota = service.addNota(notaObj, "");
        assertNotEquals(retNota, null);
    }
}



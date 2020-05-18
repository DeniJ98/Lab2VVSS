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

    String nrTema;
    String descriere;
    String deadline;
    String primire;

    String idStudent;
    String nume;
    String grupa;
    String email;

    String idNota;
    String idStudentNota;
    String idTema;
    double nota;
    LocalDate  data;

    @Before
    public void init(){
        nrTema = "8931";
        descriere = "descriere";
        deadline = "5";
        primire = "10";

        idStudent = "nrie2378";
        nume = "Razvan Nitu";
        grupa = "935";
        email = "mrie2378@scs.ubbcluj.ro";

        idNota = "2";
        idStudentNota = "nota1234";
        idTema = "tema1234";
        nota = 3;
        data = LocalDate.now();

        studentXMLRepository = new StudentXMLRepo(filenameStudent);
        temaXMLRepository =  new TemaXMLRepo(filenameTema);
        notaXMLRepository =  new NotaXMLRepo(filenameNota);
        service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator, parametersValidator);
        service.addTema("tema1234", "Intr-o zi ti-am daruit o portocala", "4", "6");
    }

    @After
    public void reset()
    {
        if(nrTema != null && !nrTema.equals("")) {
            if (service.findTema(nrTema) != null)
                service.deleteTema(nrTema);
        }
        if(idStudent != null && !idStudent.equals("")) {
            if (service.findTema(idStudent) != null)
                service.deleteTema(idStudent);
        }
        if(idNota != null && !idNota.equals("")) {
            if (service.findTema(idNota) != null)
                service.deleteTema(idNota);
        }
    }
    @Test
    public void TestAddStudent()
    {
        Student retStudent = service.addStudent(idStudent, nume, grupa, email);
        assertNotEquals(retStudent, null);

    }


    @Test
    public void TestAddNota()
    {
        Nota notaObj = new Nota(idNota, "maxi1003", "1", nota, data);
        Nota retNota = service.addNota(notaObj, "");
        assertNotEquals(retNota, null);
    }

    @Test
    public void TestAddTema()
    {
        Tema retTema = service.addTema(nrTema, descriere, deadline, primire);
        assertNotEquals(retTema, null);
    }

    @Test
    public void TestAll()
    {
        Student retStudent = service.addStudent(idStudent, nume, grupa, email);
        assertNotEquals(retStudent, null);
        Tema retTema = service.addTema(nrTema, descriere, deadline, primire);
        assertNotEquals(retTema, null);
        Nota notaObj = new Nota(idNota, "maxi1003", "1", nota, data);
        Nota retNota = service.addNota(notaObj, "");
        assertNotEquals(retNota, null);
    }
}



package NituRazvan_JudeaDenisa_Lab3;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import NituRazvan_JudeaDenisa_Lab3.domain.Student;
import NituRazvan_JudeaDenisa_Lab3.repository.NotaXMLRepo;
import NituRazvan_JudeaDenisa_Lab3.repository.StudentXMLRepo;
import NituRazvan_JudeaDenisa_Lab3.repository.TemaXMLRepo;
import NituRazvan_JudeaDenisa_Lab3.service.Service;
import NituRazvan_JudeaDenisa_Lab3.validation.NotaValidator;
import NituRazvan_JudeaDenisa_Lab3.validation.StudentValidator;
import NituRazvan_JudeaDenisa_Lab3.validation.TemaValidator;
import org.junit.Test;

import java.util.Random;
import java.util.stream.StreamSupport;

/**
 * Unit test for simple App.
 */
public class AppTest
{
    StudentValidator studentValidator = new StudentValidator();
    TemaValidator temaValidator = new TemaValidator();
    String filenameStudent;
    String filenameTema = "C:\\Users\\Xps 9560\\Documents\\PPD\\NituRazvan_JudeaDenisa_Lab3\\src\\test\\java\\NituRazvan_JudeaDenisa_Lab3\\Teme.xml";
    String filenameNota = "C:\\Users\\Xps 9560\\Documents\\PPD\\NituRazvan_JudeaDenisa_Lab3\\src\\test\\java\\NituRazvan_JudeaDenisa_Lab3\\Note.xml";

    StudentXMLRepo studentXMLRepository;
    TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
    NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
    NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);
    Service service;

    /**
     * Rigorous Test :-)
     */
    @Test
    public void Group_TC_1()
    {
        filenameStudent = "C:\\Users\\Xps 9560\\Documents\\PPD\\NituRazvan_JudeaDenisa_Lab3\\src\\test\\java\\NituRazvan_JudeaDenisa_Lab3\\Studenti.xml";
        studentXMLRepository = new StudentXMLRepo(filenameStudent);
        service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        Random r = new Random();
        int id = r.nextInt((9999 - 1000) + 1) + 1000;
        String idStudent = "mrie" + id;
        String numeStudent = "Razvan Nitu";
        int grupa = 685;
        String email = "mrie2378@scs.ubbcluj.ro";
        Student student = new Student(idStudent, numeStudent, grupa, email);

        long initialNumber = 0;
        Iterable<Student> data = service.getAllStudenti();

        for (Object i : data) {
            initialNumber++;
        }

//        assertEquals(3,initialNumber);
        service.addStudent(student);

        long currentNumber = StreamSupport.stream(service.getAllStudenti().spliterator(), false).count();
//        assertEquals(initialNumber + 1,currentNumber);
        assertEquals(student, service.findStudent(idStudent));
    }

    @Test
    public void Group_TC_2()
    {
        filenameStudent = "C:\\Users\\Xps 9560\\Documents\\PPD\\NituRazvan_JudeaDenisa_Lab3\\src\\test\\java\\NituRazvan_JudeaDenisa_Lab3\\StudentiTC2.xml";
        studentXMLRepository = new StudentXMLRepo(filenameStudent);
        service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        Random r = new Random();
        int id = r.nextInt((9999 - 1000) + 1) + 1000;
        String idStudent = "mrie" + id;
        String numeStudent = "Razvan Nitu";
        int grupa = 99;
        String email = "mrie2378@scs.ubbcluj.ro";
        Student student = new Student(idStudent, numeStudent, grupa, email);

        long initialNumber = 0;
        Iterable<Student> data = service.getAllStudenti();

        for (Object i : data) {
            initialNumber++;
        }

//        assertEquals(3,initialNumber);
        service.addStudent(student);

        long currentNumber = StreamSupport.stream(service.getAllStudenti().spliterator(), false).count();
//        assertEquals(initialNumber+1,currentNumber);
        assertEquals(student, service.findStudent(idStudent));
    }
}

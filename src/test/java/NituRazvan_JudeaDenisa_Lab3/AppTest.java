package NituRazvan_JudeaDenisa_Lab3;

import static org.junit.Assert.assertEquals;
import NituRazvan_JudeaDenisa_Lab3.domain.Student;
import NituRazvan_JudeaDenisa_Lab3.repository.NotaXMLRepo;
import NituRazvan_JudeaDenisa_Lab3.repository.StudentXMLRepo;
import NituRazvan_JudeaDenisa_Lab3.repository.TemaXMLRepo;
import NituRazvan_JudeaDenisa_Lab3.service.Service;
import NituRazvan_JudeaDenisa_Lab3.validation.NotaValidator;
import NituRazvan_JudeaDenisa_Lab3.validation.StudentValidator;
import NituRazvan_JudeaDenisa_Lab3.validation.TemaValidator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


/**
 * Unit test for simple App.
 */

public class AppTest {
    StudentValidator studentValidator = new StudentValidator();
    TemaValidator temaValidator = new TemaValidator();
    String filenameStudent = "files/Studenti.xml";
    String filenameTema = "files/Teme.xml";
    String filenameNota = "files/Note.xml";

    StudentXMLRepo studentXMLRepository;
    TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
    NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
    NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);
    Service service;
    Student student = new Student("nrie2378", "Razvan Nitu", 935, "mrie2378@scs.ubbcluj.ro");

    @BeforeEach
    public void init()
    {
        studentXMLRepository = new StudentXMLRepo(filenameStudent);
        service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);
    }

    @AfterEach
    public void reset()
    {
        if (service.findStudent(student.getID()) != null)
            service.deleteStudent(student.getID());
    }

    @ParameterizedTest
    @CsvSource({"935,true", "99,false"})
    public void Group_TC1_TC2(int grupa, boolean expected){
        student.setGrupa(grupa);
        assertEquals(Test(student), expected);
    }

    @ParameterizedTest
    @CsvSource({"Razvan Nitu,true", "j,false","Razvan123,false"})
    public void Name_TC6_TC7_TC11(String nume, boolean expected){
        student.setNume(nume);
        assertEquals(Test(student), expected);
    }


    @ParameterizedTest
    @CsvSource({"denisa@scs.ro,true", "denisa.scs.ro,false"})
    public void Email_TC12_TC15(String email, boolean expected){
        student.setEmail(email);
        assertEquals(Test(student), expected);
    }

    @ParameterizedTest
    @CsvSource({"jdie2338,true", "23dfg23sdf,false"})
    public void Id_TC16_TC17(String id, boolean expected){
        student.setID(id);
        assertEquals(Test(student), expected);
    }

    // BLACK-BOX TESTING
//    @Test
//    public void Group_TC_1() {
//        student.setGrupa(935);
//        assertEquals(Test(student), true);
//    }
//
//    @Test
//    public void Group_TC_2() {
//        student.setGrupa(99);
//        assertEquals(Test(student), false);
//    }

//    @Test
//    public void Name_TC_6() {
//        student.setNume("Razvan Nitu");
//        assertEquals(Test(student), true);
//    }
//
//    @Test
//    public void Name_TC_7() {
//        student.setNume("j");
//        assertEquals(Test(student), false);
//    }

//    @Test
//    public void Name_TC_11() {
//        student.setNume("Razvan123");
//        assertEquals(Test(student), false);
//    }

//    @Test
//    public void Email_TC_12() {
//        student.setEmail("denisa@scs.ro");
//        assertEquals(Test(student), true);
//    }
//
//    @Test
//    public void Email_TC_15() {
//        student.setEmail("denisa.scs.ro");
//        assertEquals(Test(student), false);
//    }

//    @Test
//    public void Id_TC_16() {
//        student.setID("jdie2338");
//        assertEquals(Test(student), true);
//    }
//
//    @Test
//    public void Id_TC_17() {
//        student.setID("23dfg23sdf");
//        assertEquals(Test(student), false);
//    }


    private Boolean Test(Student student)
    {
        service.addStudent(student);
        return service.findStudent(student.getID()) == student;
    }


}

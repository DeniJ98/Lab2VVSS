package NituRazvan_JudeaDenisa_Lab3;

import static org.junit.Assert.assertEquals;
import NituRazvan_JudeaDenisa_Lab3.domain.Student;
import NituRazvan_JudeaDenisa_Lab3.domain.Tema;
import NituRazvan_JudeaDenisa_Lab3.repository.NotaXMLRepo;
import NituRazvan_JudeaDenisa_Lab3.repository.StudentXMLRepo;
import NituRazvan_JudeaDenisa_Lab3.repository.TemaXMLRepo;
import NituRazvan_JudeaDenisa_Lab3.service.Service;
import NituRazvan_JudeaDenisa_Lab3.validation.NotaValidator;
import NituRazvan_JudeaDenisa_Lab3.validation.StudentValidator;
import NituRazvan_JudeaDenisa_Lab3.validation.TemaValidator;
import org.junit.Test;


/**
 * Unit test for simple App.
 */

public class AppTest {
    StudentValidator studentValidator = new StudentValidator();
    TemaValidator temaValidator = new TemaValidator();
    String filenameStudent;
    String filenameTema = "files/Teme.xml";
    String filenameNota = "files/Note.xml";

    StudentXMLRepo studentXMLRepository;
    TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
    NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
    NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);
    Service service;
    Student student = new Student("nrie2378", "Razvan Nitu", 935, "mrie2378@scs.ubbcluj.ro");
    Tema tema = new Tema("8931", "desciere", 5, 10);

    // WHITE-BOX TESTING
    @Test
    public void Deadline_TC_1()
    {
        RefreshData();
        tema.setDeadline(7);
        assertEquals(TestTema(tema), true);
        DeleteTema(tema);
    }
    @Test
    public void Deadline_TC_2()
    {
        RefreshData();
        tema.setDeadline(18);
        assertEquals(TestTema(tema), false);
        DeleteTema(tema);
    }

    // BLACK-BOX TESTING
    @Test
    public void Group_TC_1() {
        RefreshData();
        student.setGrupa(935);
        assertEquals(Test(student), true);
        DeleteData(student);
    }

    @Test
    public void Group_TC_2() {
        RefreshData();
        student.setGrupa(99);
        assertEquals(Test(student), false);
        DeleteData(student);
    }

    @Test
    public void Name_TC_6() {
        RefreshData();
        student.setNume("Razvan Nitu");
        assertEquals(Test(student), true);
        DeleteData(student);
    }
    @Test
    public void Name_TC_7() {
        RefreshData();
        student.setNume("j");
        assertEquals(Test(student), false);
        DeleteData(student);
    }
    @Test
    public void Name_TC_11() {
        RefreshData();
        student.setNume("Razvan123");
        assertEquals(Test(student), false);
        DeleteData(student);

    }
    @Test
    public void Email_TC_12() {
        RefreshData();
        student.setEmail("denisa@scs.ro");
        assertEquals(Test(student), true);
        DeleteData(student);

    }
    @Test
    public void Email_TC_15() {
        RefreshData();
        student.setEmail("denisa.scs.ro");
        assertEquals(Test(student), false);
        DeleteData(student);

    }

    @Test
    public void Id_TC_16() {
        RefreshData();
        student.setID("jdie2338");
        assertEquals(Test(student), true);
        DeleteData(student);

    }

    @Test
    public void Id_TC_17() {
        RefreshData();
        student.setID("23dfg23sdf");
        assertEquals(Test(student), false);
        DeleteData(student);

    }


    public void RefreshData()
    {
        String fisierStudenti = "files/Studenti.xml";
        String fisierTeme = "files/Teme.xml";
        String fisierNote = "files/Note.xml";
        filenameTema = fisierTeme;
        filenameNota =  fisierNote;
        filenameStudent = fisierStudenti;
        studentXMLRepository = new StudentXMLRepo(filenameStudent);
        service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);
    }

    public void DeleteTema(Tema tema)
    {
        if (service.findTema(tema.getID()) != null)
            service.deleteTema(tema.getID());
    }

    public void DeleteData(Student student)
    {
        if (service.findStudent(student.getID()) != null)
            service.deleteStudent(student.getID());
    }

    public Boolean Test(Student student)
    {
        service.addStudent(student);
        return service.findStudent(student.getID()) == student;
    }

    public Boolean TestTema(Tema tema)
    {
        service.addTema(tema);
        return service.findTema(tema.getID()) == tema;
    }
}

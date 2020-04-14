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
import NituRazvan_JudeaDenisa_Lab3.validation.parameters.ParametersValidator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * Unit test for simple App.
 */

public class AppTest {
    private StudentValidator studentValidator = new StudentValidator();
    private TemaValidator temaValidator = new TemaValidator();
    private String filenameStudent = "files/Studenti.xml";
    private String filenameTema = "files/Teme.xml";
    private String filenameNota = "files/Note.xml";

    private StudentXMLRepo studentXMLRepository;
    private TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
    private NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
    private NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);
    private ParametersValidator parametersValidator = new ParametersValidator();
    private Service service;

    private String id;
    private String nume ;
    private String grupa;
    private String email;

    @Before
    public void init()
    {
        id = "nrie2378";
        nume = "Razvan Nitu";
        grupa = "935";
        email = "mrie2378@scs.ubbcluj.ro";
        studentXMLRepository = new StudentXMLRepo(filenameStudent);
        service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator, parametersValidator);
    }

    @After
    public void reset()
    {
        if (service.findStudent(id) != null)
            service.deleteStudent(id);
    }

//    @ParameterizedTest
//    @CsvSource({"935,true", "99,false"})
//    public void Group_TC1_TC2(String grupa, boolean expected){
//        this.grupa = grupa;
//        assertEquals(Test(), expected);
//    }
//
//    @ParameterizedTest
//    @CsvSource({"Razvan Nitu,true", "j,false","Razvan123,false"})
//    public void Name_TC6_TC7_TC11(String nume, boolean expected){
//        this.nume=nume;
//        assertEquals(Test(), expected);
//    }
//
//
//    @ParameterizedTest
//    @CsvSource({"denisa@scs.ro,true", "denisa.scs.ro,false"})
//    public void Email_TC12_TC15(String email, boolean expected){
//        this.email = email;
//        assertEquals(Test(), expected);
//    }
//
//    @ParameterizedTest
//    @CsvSource({"jdie2338,true", "23dfg23sdf,false"})
//    public void Id_TC16_TC17(String id, boolean expected){
//        this.id = id;
//        assertEquals(Test(), expected);
//    }
//

    @Test
    public void Group_TC_1() {
        this.grupa = Integer.toString(935);
        assertEquals(Test(), true);
    }

    @Test
    public void Group_TC_2() {
        this.grupa = Integer.toString(99);
        assertEquals(Test(), false);
    }

    @Test
    public void Name_TC_6() {
        this.nume = "Razvan Nitu";
        assertEquals(Test(), true);
    }
    @Test
    public void Name_TC_7() {
        this.nume = "j";
        assertEquals(Test(), false);
    }
    @Test
    public void Name_TC_11() {
        this.nume = "Razvan123";
        assertEquals(Test(), false);
    }
    @Test
    public void Email_TC_12() {
        this.email ="denisa@scs.ro";
        assertEquals(Test(), true);
    }
    @Test
    public void Email_TC_15() {
        this.email ="denisa.scs.ro";
        assertEquals(Test(), false);

    }

    @Test
    public void Id_TC_16() {
       this.id = "jdie2338";
        assertEquals(Test(), true);
    }

    @Test
    public void Id_TC_17() {
        this.id = "23dfg23sdf";
        assertEquals(Test(), false);
    }

    private Boolean Test()
    {
        service.addStudent(id, nume, grupa, email);
        Student ret = service.findStudent(id);
        if(ret == null)
            return false;

        return ret.getID().equals(id) && ret.getNume().equals(nume) &&
                Integer.toString(ret.getGrupa()).equals(grupa) && ret.getEmail().equals(email);
    }


}

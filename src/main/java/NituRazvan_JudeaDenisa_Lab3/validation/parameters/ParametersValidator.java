package NituRazvan_JudeaDenisa_Lab3.validation.parameters;

import NituRazvan_JudeaDenisa_Lab3.domain.Student;
import NituRazvan_JudeaDenisa_Lab3.domain.Tema;
import NituRazvan_JudeaDenisa_Lab3.validation.ValidationException;

public class ParametersValidator {
    public Student validateParametersStudent(String idStudent, String nume, String grupa, String email)
    {
        int intGrupa = -1;
        try
        {
            intGrupa = Integer.parseInt(grupa);

        }catch (Exception ex)
        {
            throw new ValidationException("Grupa trebuie sa fie un numar!");
        }
        return new Student(idStudent, nume, intGrupa, email);

    }
    public Tema validateParametersTema(String nrTema, String descriere, String deadline, String primire) {
        int intDeadline = -1;
        int intPrimire = -1;
        try {
            intDeadline = Integer.parseInt(deadline);

        } catch (Exception ex) {
            throw new ValidationException("Deadline-ul trebuie sa fie un numar!");
        }
        try {
            intPrimire = Integer.parseInt(primire);

        } catch (Exception ex) {
            throw new ValidationException("Primirea trebuie sa fie un numar!");
        }
        return new Tema(nrTema, descriere, intDeadline, intPrimire);
    }
}

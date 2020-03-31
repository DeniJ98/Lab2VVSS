package NituRazvan_JudeaDenisa_Lab3.validation;

import NituRazvan_JudeaDenisa_Lab3.domain.Student;
;

public class StudentValidator implements Validator<Student> {

    /**
     * Valideaza un student
     * @param entity - studentul pe care il valideaza
     * @throws ValidationException - daca studentul nu e valid
     */
    @Override
    public void validate(Student entity) throws ValidationException {
        String regexDigits = "\\d+";
        String regexLetters = "^[a-z0-9_\\-]+$";
        String regexEmail = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
        String regexAnyDigit = "(.)*(\\d)(.)*";

        //tests ID
        if(entity.getID().equals("")){
            throw new ValidationException("Id incorect!");
        }
        if(entity.getID() == null){
            throw new ValidationException("Id incorect!");
        }
        if (entity.getID().length() != 8)
            throw new ValidationException("Id-ul trebuie sa fie de exact 8 caractere!");
        if (!entity.getID().substring(0, 4).matches(regexLetters) || !entity.getID().substring(entity.getID().length() - 4).matches(regexDigits))
            throw new ValidationException("Id-ul trebuie sa fie de forma cccciiii (de ex: ndfa4567)!");

        //tests grupa
        if(entity.getGrupa() < 100 || entity.getGrupa() > 999) {
            throw new ValidationException("Grupa ar trebui sa fie in intervalul [100,999]!");
        }

        //tests email
        if(entity.getEmail() == null){
            throw new ValidationException("Email-ul nu trebuie sa fie null!");
        }
        if(entity.getEmail().equals("")){
            throw new ValidationException("Email-ul nu trebuie sa fie un string gol!");
        }
        if (!entity.getEmail().matches(regexEmail))
            throw new ValidationException("Email-ul trebuie sa fie de forma mail@domain.something!");

        // tests nume
        if(entity.getNume() == null){
            throw new ValidationException("Nume nu trebuie sa fie null!");
        }
        if(entity.getNume().equals("")){
            throw new ValidationException("Nume nu trebuie sa fie un string gol!");
        }
        if (entity.getNume().length() < 2)
            throw new ValidationException("Numele ar trebui sa fie de minim 2 caractere!");

        if (entity.getNume().matches(regexAnyDigit))
            throw new ValidationException("Numele nu trebuie sa contina cifre!");

    }
}

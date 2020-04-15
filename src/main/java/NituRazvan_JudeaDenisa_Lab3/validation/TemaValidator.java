package NituRazvan_JudeaDenisa_Lab3.validation;

import NituRazvan_JudeaDenisa_Lab3.domain.Tema;

public class TemaValidator implements Validator<Tema> {

    /**
     * Valideaza o tema
     * @param entity - tema pe care o valideaza
     * @throws ValidationException daca tema nu e valida
     */
    @Override
    public void validate(Tema entity) throws ValidationException {
        try{
            if(entity.getID().equals("") || entity.getID() == null) {
                throw new ValidationException("Numar tema invalid!");
            }}
        catch(NullPointerException ex)
        {
            throw new ValidationException("Numar tema null!");
        }
        if(entity.getDescriere().equals("")){
            throw new ValidationException("Descriere invalida!");
        }
        if(entity.getDeadline() < 1 || entity.getDeadline() > 14) {
            throw new ValidationException("Deadline-ul trebuie sa fie intre 1-14.");
        }
        if(entity.getPrimire() < 1 || entity.getPrimire() > 14) {
            throw new ValidationException("Saptamana primirii trebuie sa fie intre 1-14.");
        }
    }
}

package NituRazvan_JudeaDenisa_Lab3.validation;

public interface Validator<E> {
    /**
     * valideaza o entitate
     * @param entity - entitatea pe care o valideaza
     * @throws ValidationException daca entitatea nu e valida
     */
    void validate(E entity) throws ValidationException;
}
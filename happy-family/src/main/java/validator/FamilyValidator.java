package validator;

import exception.FamilyOverflowException;
import people.Family;

/**
 * description
 *
 * @author Alexander Isai on 11.04.2024.
 */
public class FamilyValidator {

    public FamilyValidator() {
    }
    public void checkSizeFamily(Family family, int max){
        if(family.countFamily() >= max){
            throw new FamilyOverflowException("Родина має забагато членів");
        }
    }
}

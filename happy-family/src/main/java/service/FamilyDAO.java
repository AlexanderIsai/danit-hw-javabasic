package service;

import people.Family;

import java.util.List;

/**
 * description
 *
 * @author Alexander Isai on 29.03.2024.
 */
public interface FamilyDAO {

    List<Family> getAllFamilies();
    Family getFamilyByIndex(int index);
    boolean deleteFamily(int index);
    boolean deleteFamily(Family family);
    void saveFamily (Family family);
    void initFamilyList();
}

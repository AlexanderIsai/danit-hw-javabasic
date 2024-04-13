package service;

import people.Family;

import java.io.*;
import java.util.List;

/**
 * description
 *
 * @author Alexander Isai on 12.04.2024.
 */
public class FamilyLoader2 {

    public FamilyLoader2() {
    }

    public void loadData(List<Family> families) {

        File file = new File("family.bin");
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(families);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Family> getDataFromFile() {
        File file = new File("family.bin");
        List<Family> familyList;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            familyList = (List<Family>) ois.readObject();
        } catch (IOException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }
        return familyList;
    }
}
package service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import people.Family;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * description
 *
 * @author Alexander Isai on 11.04.2024.
 */
public class FamilyLoader {
    private File file = new File("families.json");
    private ObjectMapper objectMapper = new ObjectMapper();
    public FamilyLoader() {
    }

    public void loadData(List<Family> familyList) {
        try {
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
            objectMapper.setDefaultPropertyInclusion(JsonInclude.Include.NON_DEFAULT);
            objectMapper.writeValue(file, familyList);
            System.out.println("Колекція родин збережена");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public List<Family> getDataFromFile() {
        try {
            objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
            objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            objectMapper.enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);
            List<Family> familyList = objectMapper.readValue(file, new TypeReference<List<Family>>() {});
            System.out.println("Перелік родин успішно завантажений");
            return familyList;
        } catch (IOException e) {
            System.out.println("Не вдалося завантажити дані з файлу");
            e.printStackTrace();
        }
        return null;
    }

}

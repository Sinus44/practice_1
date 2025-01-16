package dictionary;

import java.util.Map;

public interface Dictionary {
    void loadFromFile(String filePath);
    void saveToFile(String filePath);
    void addEntry(String key, String value);
    void removeEntry(String key);
    String findEntry(String key);
    Map<String, String> getAllEntries();
}


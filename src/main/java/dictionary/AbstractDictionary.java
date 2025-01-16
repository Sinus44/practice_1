package dictionary;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractDictionary implements Dictionary {
    protected Map<String, String> dictionary;

    public AbstractDictionary() {
        this.dictionary = new HashMap<>();
    }

    @Override
    public void loadFromFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("->");
                if (parts.length == 2) {
                    String key = parts[0].trim();
                    String value = parts[1].trim();
                    if (isValidKey(key)) {
                        dictionary.put(key, value);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveToFile(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Map.Entry<String, String> entry : dictionary.entrySet()) {
                writer.write(entry.getKey() + " -> " + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addEntry(String key, String value) {
        if (isValidKey(key)) {
            dictionary.put(key, value);
        } else {
            throw new IllegalArgumentException("Invalid key format.");
        }
    }

    @Override
    public void removeEntry(String key) {
        dictionary.remove(key);
    }

    @Override
    public String findEntry(String key) {
        return dictionary.get(key);
    }

    @Override
    public Map<String, String> getAllEntries() {
        return new HashMap<>(dictionary);
    }

    protected abstract boolean isValidKey(String key);
}

package dictionary;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RegexDictionary implements Dictionary {
    private final Map<String, String> dictionary;
    private final String regex;
    private final String filePath;

    public RegexDictionary(String regex, String filePath) {
        this.dictionary = new HashMap<>();
        this.regex = regex;
        this.filePath = filePath;
        load();
    }

    @Override
    public void load() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("->");
                if (parts.length == 2) {
                    String key = parts[0].trim();
                    String value = parts[1].trim();
                    if (key.matches(regex)) {
                        dictionary.put(key, value);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save() {
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
        if (key.matches(regex)) {
            dictionary.put(key, value);
            save();
        } else {
            throw new IllegalArgumentException("Invalid key format.");
        }
    }

    @Override
    public void removeEntry(String key) {
        dictionary.remove(key);
        save();
    }

    @Override
    public String findEntry(String key) {
        return dictionary.get(key);
    }

    @Override
    public Map<String, String> getAllEntries() {
        return new HashMap<>(dictionary);
    }
}

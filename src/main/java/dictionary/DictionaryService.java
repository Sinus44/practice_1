package dictionary;

import java.util.Map;

public class DictionaryService {
    private final Dictionary dictionary;

    public DictionaryService(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public void load() {
        dictionary.load();
    }

    public void save() {
        dictionary.save();
    }

    public void addEntry(String key, String value) {
        dictionary.addEntry(key, value);
    }

    public void removeEntry(String key) {
        dictionary.removeEntry(key);
    }

    public String findEntry(String key) {
        return dictionary.findEntry(key);
    }

    public Map<String, String> getAllEntries() {
        return dictionary.getAllEntries();
    }
}


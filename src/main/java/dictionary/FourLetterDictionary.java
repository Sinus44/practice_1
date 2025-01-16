package dictionary;

public class FourLetterDictionary extends AbstractDictionary {
    @Override
    protected boolean isValidKey(String key) {
        return key.matches("[A-Za-z]{4}");
    }
}


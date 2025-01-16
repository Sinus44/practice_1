package dictionary;

public class FiveDigitDictionary extends AbstractDictionary {
    @Override
    protected boolean isValidKey(String key) {
        return key.matches("\\d{5}");
    }
}

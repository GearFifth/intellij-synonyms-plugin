package intellijsynonymsplugin.splitters.types;

import intellijsynonymsplugin.splitters.interfaces.IdentifierSplitter;

import java.util.ArrayList;
import java.util.List;

public class PascalCaseSplitter implements IdentifierSplitter {
    @Override
    public List<String> splitIdentifier(String identifier) {
        List<String> words = new ArrayList<>();
        StringBuilder currentWord = new StringBuilder();

        for (char c : identifier.toCharArray()) {
            if (Character.isUpperCase(c)) {
                if (!currentWord.isEmpty()) {
                    words.add(currentWord.toString());
                    currentWord.setLength(0);
                }
            }
            currentWord.append(c);
        }

        if (!currentWord.isEmpty()) {
            words.add(currentWord.toString());
        }

        return words;
    }
}

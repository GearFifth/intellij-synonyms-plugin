package intellijsynonymsplugin.splitters.types;

import intellijsynonymsplugin.splitters.interfaces.IdentifierSplitter;

import java.util.ArrayList;
import java.util.List;

public class PascalCaseSplitter implements IdentifierSplitter {
    @Override
    public List<String> splitIdentifier(String identifier) {
        List<String> words = new ArrayList<>();
        StringBuilder currentWord = new StringBuilder();

        for (int i = 0; i < identifier.length(); i++) {
            char currentChar = identifier.charAt(i);

            if (Character.isUpperCase(currentChar)) {

                if (!currentWord.isEmpty() && (isLastCharacter(i, identifier) || isNextCharacterUpperCase(i, identifier))) {
                    currentWord.append(currentChar);
                } else {
                    //Start a new word
                    if (!currentWord.isEmpty()) {
                        words.add(currentWord.toString());
                        currentWord.setLength(0);
                    }
                    currentWord.append(currentChar);
                }
            } else {
                currentWord.append(currentChar);
            }
        }

        if (!currentWord.isEmpty()) {
            words.add(currentWord.toString());
        }

        return words;
    }

    private boolean isNextCharacterUpperCase(int currentIndex, String identifier) {
        return currentIndex + 1 < identifier.length() && Character.isUpperCase(identifier.charAt(currentIndex + 1));
    }

    private boolean isLastCharacter(int currentIndex, String identifier) {
        return currentIndex == identifier.length() - 1;
    }
}

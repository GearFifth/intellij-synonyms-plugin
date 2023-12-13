package intellijsynonymsplugin.splitters.types;

import intellijsynonymsplugin.splitters.interfaces.IdentifierSplitter;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CamelCaseSplitter implements IdentifierSplitter {
    @Override
    public List<String> splitIdentifier(String identifier) {
        List<String> result = new ArrayList<>();

        if (identifier == null || identifier.isEmpty()) {
            return result;
        }

        StringBuilder currentWord = new StringBuilder();
        currentWord.append(identifier.charAt(0));

        for (int i = 1; i < identifier.length(); i++) {
            char currentChar = identifier.charAt(i);

            if (Character.isUpperCase(currentChar)) {
                result.add(currentWord.toString());
                currentWord = new StringBuilder();
            }

            currentWord.append(currentChar);
        }

        result.add(currentWord.toString());

        return result;
    }
}

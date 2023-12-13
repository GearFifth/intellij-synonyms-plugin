package intellijsynonymsplugin.splitters.types;

import intellijsynonymsplugin.splitters.interfaces.IdentifierSplitter;

import java.util.Arrays;
import java.util.List;

public class SnakeCaseSplitter implements IdentifierSplitter {
    @Override
    public List<String> splitIdentifier(String identifier) {
        return Arrays.asList(identifier.split("_"));
    }
}

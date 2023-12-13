package intellijsynonymsplugin.splitters.interfaces;

import java.util.List;

public interface IdentifierSplitter {
    List<String> splitIdentifier(String identifier);
}

package intellijsynonymsplugin.splitters;

import intellijsynonymsplugin.splitters.interfaces.IdentifierSplitter;
import intellijsynonymsplugin.splitters.types.*;
import intellijsynonymsplugin.utils.CaseType;

public class SplitterFactory {
    public static IdentifierSplitter createSplitter(CaseType type) {
        return switch (type) {
            case CAMEL_CASE -> new CamelCaseSplitter();
            case SNAKE_CASE -> new SnakeCaseSplitter();
            case PASCAL_CASE -> new PascalCaseSplitter();
            case UPPER_CASE -> new UpperCaseSplitter();
            default -> throw new IllegalArgumentException("Unsupported splitter type: " + type);
        };
    }
}

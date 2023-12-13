package intellijsynonymsplugin.utils;

public class CaseDetector {
    public enum CaseType {
        CAMEL_CASE, KEBAB_CASE, PASCAL_CASE, SNAKE_CASE, UPPER_CASE, UNKNOWN
    }

    public static CaseType detectCase(String identifier) {
        if (identifier.matches("[a-z]+([A-Z][a-z]*)*")) {
            return CaseType.CAMEL_CASE;
        } else if (identifier.matches("[a-z]+(-[a-z]+)*")) {
            return CaseType.KEBAB_CASE;
        } else if (identifier.matches("[A-Z][a-zA-Z]*")) {
            return CaseType.PASCAL_CASE;
        } else if (identifier.matches("[a-z]+(_[a-z]+)*")) {
            return CaseType.SNAKE_CASE;
        } else if (identifier.matches("[A-Z_]+")) {
            return CaseType.UPPER_CASE;
        } else {
            return CaseType.UNKNOWN;
        }
    }

}

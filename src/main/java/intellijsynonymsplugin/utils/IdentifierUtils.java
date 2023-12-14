package intellijsynonymsplugin.utils;

import intellijsynonymsplugin.splitters.SplitterFactory;
import intellijsynonymsplugin.splitters.interfaces.IdentifierSplitter;

import java.util.ArrayList;
import java.util.List;

public class IdentifierUtils {
    public static List<String> splitIdentifier(String identifier){
        CaseType caseType = CaseDetector.detectCase(identifier);
        if(caseType == CaseType.UNKNOWN){
            return new ArrayList<>();
        }
        IdentifierSplitter splitter = SplitterFactory.createSplitter(caseType);
        return splitter.splitIdentifier(identifier);
    }
}

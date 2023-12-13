package intellijsynonymsplugin.repositories;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ThesaurusRepository {
    private static final String JSON_FILE_PATH = "/thesaurus.json";

    public List<String> getSynonymsForWord(String targetWord) throws IOException {
        List<String> synonyms = new ArrayList<>();
        boolean processingTargetWord = false;
        boolean stopProcessing = false;

        try (InputStream inputStream = getClass().getResourceAsStream(JSON_FILE_PATH)) {
            if (inputStream != null) {
                JsonParser parser = new JsonFactory().createParser(inputStream);
                while (parser.nextToken() != null && !stopProcessing) {
                    if (parser.getCurrentToken() == JsonToken.START_OBJECT) {
                        processObject(parser, targetWord, synonyms, processingTargetWord, stopProcessing);
                    }
                }
            } else {
                throw new IOException("Unable to load thesaurus.json");
            }
        }

        return synonyms;
    }

    private void processObject(JsonParser parser, String targetWord, List<String> synonyms, boolean processingTargetWord, boolean stopProcessing) throws IOException {
        while (parser.nextToken() != JsonToken.END_OBJECT) {
            if (parser.getCurrentToken() == JsonToken.FIELD_NAME) {
                String fieldName = parser.getCurrentName();
                parser.nextToken();

                if ("word".equals(fieldName)) {
                    boolean oldProcessingTargetWord = processingTargetWord;
                    processingTargetWord = targetWord.equals(parser.getText());

                    if(oldProcessingTargetWord && !processingTargetWord){
                        stopProcessing = true;
                    }
                }

                if (processingTargetWord) {
                    processSynonyms(parser, synonyms);
                } else {
                    parser.skipChildren();
                }
            }
        }
    }

    private void processSynonyms(JsonParser parser, List<String> synonyms) throws IOException {
        while (parser.nextToken() != JsonToken.END_OBJECT) {
            if ("synonyms".equals(parser.getCurrentName())) {
                parser.nextToken();
                while (parser.nextToken() != JsonToken.END_ARRAY) {
                    if (parser.getCurrentToken() == JsonToken.VALUE_STRING) {
                        synonyms.add(parser.getText());
                    }
                }
            } else {
                parser.skipChildren();
            }
        }
    }
}

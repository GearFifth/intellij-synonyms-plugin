package intellijsynonymsplugin.services;

import intellijsynonymsplugin.repositories.ThesaurusRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static intellijsynonymsplugin.utils.IdentifierUtils.splitIdentifier;


public class ThesaurusService {
    private final ThesaurusRepository thesaurusRepository;

    public ThesaurusService(ThesaurusRepository thesaurusRepository) {
        this.thesaurusRepository = thesaurusRepository;
    }

    public List<String> getSynonyms(String identifier) throws IOException {
        List<String> mixedSynonyms = new ArrayList<>();
        List<List<String>> synonymsList = new ArrayList<>();

        boolean foundSynonyms = false;

        for (String word : splitIdentifier(identifier)) {
            List<String> synonymsForWord = thesaurusRepository.getSynonymsForWord(word.toLowerCase());
            if (!synonymsForWord.isEmpty()) {
                synonymsList.add(synonymsForWord);
                foundSynonyms = true;
            } else {
                synonymsList.add(List.of(word));
            }
        }

        //If there are no synonyms just return empty ArrayList
        if(!foundSynonyms){
            return mixedSynonyms;
        }

        generateSynonymCombinations(synonymsList, 0, new ArrayList<>(), mixedSynonyms);
        return mixedSynonyms;
    }

    private void generateSynonymCombinations(List<List<String>> synonymsList, int index, List<String> currentCombination, List<String> result) {
        if (index == synonymsList.size()) {
            result.add(String.join(" ", currentCombination));
            return;
        }

        List<String> currentSynonyms = synonymsList.get(index);

        for (String synonym : currentSynonyms) {
            List<String> newCombination = new ArrayList<>(currentCombination);
            newCombination.add(synonym);
            generateSynonymCombinations(synonymsList, index + 1, newCombination, result);
        }
    }

}

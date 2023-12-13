package intellijsynonymsplugin.services;

import intellijsynonymsplugin.repositories.ThesaurusRepository;

import java.io.IOException;
import java.util.List;


public class ThesaurusService {
    private final ThesaurusRepository thesaurusRepository;

    public ThesaurusService(ThesaurusRepository thesaurusRepository) {
        this.thesaurusRepository = thesaurusRepository;
    }

    public List<String> getSynonyms(String word) throws IOException {
        return thesaurusRepository.getSynonymsForWord(word);
    }
}

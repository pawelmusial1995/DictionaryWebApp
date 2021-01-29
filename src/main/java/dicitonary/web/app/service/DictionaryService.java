package dicitonary.web.app.service;

import java.util.List;

import dicitonary.web.app.form.DictionaryForm;
import dicitonary.web.app.model.Dictionary;

public interface DictionaryService {

	void createNewDictionary(Dictionary dictionary);

	List<Dictionary> getAllDictionaries();

	Dictionary findDictionaryById(Long dictionary_id);

	void editDictionary(DictionaryForm dictionaryForm);

	void disactivateDicitonary(Long dictionary_id);

}

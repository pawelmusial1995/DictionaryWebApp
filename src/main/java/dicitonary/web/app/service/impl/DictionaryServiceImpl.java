package dicitonary.web.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import dicitonary.web.app.client.DictionaryClient;
import dicitonary.web.app.form.DictionaryForm;
import dicitonary.web.app.model.Dictionary;
import dicitonary.web.app.service.DictionaryService;
import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class DictionaryServiceImpl implements DictionaryService {

	@Autowired
	DictionaryClient dictionaryClient;

	@Override
	public void createNewDictionary(Dictionary dictionary) {
		try {
			dictionaryClient.addDictonary(dictionary);
		} catch (Exception e) {
			log.error(e);
		}

	}

	@Override
	public List<Dictionary> getAllDictionaries() {
		ResponseEntity<? extends ArrayList<Dictionary>> dictionaryArrayList = dictionaryClient.getAllDictionaries();

		return (List<Dictionary>) dictionaryArrayList.getBody();
	}

	@Override
	public Dictionary findDictionaryById(Long dictionary_id) {
		DictionaryForm dictionaryForm = new DictionaryForm();
		dictionaryForm.setDictionaryId(dictionary_id);
		Dictionary dictionary = dictionaryClient.findDictionaryById(dictionaryForm);
		return dictionary;
	}

	@Override
	public void editDictionary(DictionaryForm dictionaryForm) {

		if (dictionaryForm.getDictionaryId() != null) {
			dictionaryClient.editDictionary(dictionaryForm);
		} else {
			log.error("Errror durring edit dicitonary dictionaryId null");
		}

	}

	@Override
	public void disactivateDicitonary(Long dictionary_id) {

		if (dictionary_id != null) {
			DictionaryForm dictionaryForm = new DictionaryForm();
			dictionaryForm.setDictionaryId(dictionary_id);
			dictionaryForm.setDictionaryActive(false);
			dictionaryClient.disactivateDictionary(dictionaryForm);
		} else {
			log.error("Error durring disactivate Dictionary dictionary not found");
		}

	}

}

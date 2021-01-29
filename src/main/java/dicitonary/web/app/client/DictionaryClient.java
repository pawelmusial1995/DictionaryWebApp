package dicitonary.web.app.client;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;

import dicitonary.web.app.constants.AppConstants;
import dicitonary.web.app.form.DictionaryForm;
import dicitonary.web.app.model.Dictionary;

@Component
public class DictionaryClient {

	@Autowired
	private RestOperations restOperations;

	private final String url;

	@Autowired
	public DictionaryClient(@Value("${dictionaries.url}") final String url) {
		this.url = url;
	}

	public ResponseEntity<? extends ArrayList<Dictionary>> getAllDictionaries() {

		Class<? extends ArrayList<Dictionary>> dictionaryList = (Class<? extends ArrayList<Dictionary>>) ArrayList.class;
		ResponseEntity<? extends ArrayList<Dictionary>> responseEntity = restOperations.getForEntity(url,
				dictionaryList);

		return responseEntity;

	}

	public void addDictonary(Dictionary dictionary) {

		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		HttpEntity<Dictionary> requestEntity = new HttpEntity<Dictionary>(dictionary, requestHeaders);
		restOperations.exchange(AppConstants.ADD_DICTIONARY_URL, HttpMethod.POST, requestEntity,
				Dictionary.class);

	}

	public Dictionary findDictionaryById(DictionaryForm dictionaryForm) {
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		HttpEntity<DictionaryForm> requestEntity = new HttpEntity<DictionaryForm>(dictionaryForm, requestHeaders);
		ResponseEntity<Dictionary> dictionaryEntity = restOperations.exchange(AppConstants.GET_DICTIONARY_URL,
				HttpMethod.POST, requestEntity, Dictionary.class);
		Dictionary dictionary = dictionaryEntity.getBody();
		return dictionary;
	}

	public void editDictionary(DictionaryForm dictionaryForm) {
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		HttpEntity<DictionaryForm> requestEntity = new HttpEntity<DictionaryForm>(dictionaryForm, requestHeaders);
		restOperations.exchange(AppConstants.EDIT_DICTIONARY_URL, HttpMethod.POST, requestEntity,
				Dictionary.class);
		
	}

	public void disactivateDictionary(DictionaryForm dictionaryForm) {
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		HttpEntity<DictionaryForm> requestEntity = new HttpEntity<DictionaryForm>(dictionaryForm, requestHeaders);
		restOperations.exchange(AppConstants.DEACTIVATE_DICTIONARY_URL, HttpMethod.POST, requestEntity,
				Dictionary.class);
	}

}

package dicitonary.web.app.client;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;

import dicitonary.web.app.constants.AppConstants;
import dicitonary.web.app.form.DictionaryItemForm;
import dicitonary.web.app.model.DictionaryItem;

@Component
public class DictionaryItemClient {

	@Autowired
	private RestOperations restOperations;

	public ResponseEntity<? extends ArrayList<DictionaryItem>> getAllDictionaryItems() {

		Class<? extends ArrayList<DictionaryItem>> dictionaryItemList = (Class<? extends ArrayList<DictionaryItem>>) ArrayList.class;
		ResponseEntity<? extends ArrayList<DictionaryItem>> responseEntity = restOperations
				.getForEntity(AppConstants.GETALL_DICTIONARY_ITEM_URL, dictionaryItemList);
		return responseEntity;

	}

	public void disactivateDictionaryItem(DictionaryItemForm dictionaryItemForm) {
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		HttpEntity<DictionaryItemForm> requestEntity = new HttpEntity<DictionaryItemForm>(dictionaryItemForm, requestHeaders);
		restOperations.exchange(AppConstants.DEACTIVATE_DICTIONARY_ITEM_URL, HttpMethod.POST, requestEntity,
				DictionaryItem.class);
	}

	public void addNewDictionaryItem(DictionaryItem dictionaryItem) {
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		HttpEntity<DictionaryItem> requestEntity = new HttpEntity<DictionaryItem>(dictionaryItem, requestHeaders);
		restOperations.exchange(AppConstants.ADD_DICTIONARY_ITEM_URL, HttpMethod.POST, requestEntity,
				DictionaryItem.class);

	}

	public DictionaryItem findDictionaryById(DictionaryItem dictionaryItem) {
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		HttpEntity<DictionaryItem> requestEntity = new HttpEntity<DictionaryItem>(dictionaryItem, requestHeaders);
		ResponseEntity<DictionaryItem> dictionaryEntity = restOperations.exchange(
				AppConstants.GET_DICTIONARY_ITEM_URL, HttpMethod.POST, requestEntity,
				DictionaryItem.class);
		DictionaryItem dictionaryItemDB = dictionaryEntity.getBody();
		return dictionaryItemDB;
	}

	public void editDictionaryItem(DictionaryItemForm dictionaryItemForm) {
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		HttpEntity<DictionaryItemForm> requestEntity = new HttpEntity<DictionaryItemForm>(dictionaryItemForm, requestHeaders);
		restOperations.exchange(AppConstants.EDIT_DICTIONARY_ITEM_URL, HttpMethod.POST, requestEntity,
				DictionaryItem.class);

	}

	public void removeDictionaryItem(DictionaryItemForm dictionaryItemForm) {
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		HttpEntity<Long> requestEntity = new HttpEntity<Long>(dictionaryItemForm.getDicItemId(), requestHeaders);
		restOperations.exchange(AppConstants.REMOVE_DICTIONARY_ITEM_URL, HttpMethod.POST, requestEntity,
				DictionaryItem.class);
		
	}

}

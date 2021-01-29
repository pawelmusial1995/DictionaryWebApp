package dicitonary.web.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import dicitonary.web.app.client.DictionaryItemClient;
import dicitonary.web.app.form.DictionaryItemForm;
import dicitonary.web.app.model.DictionaryItem;
import dicitonary.web.app.service.DictionaryItemService;
import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class DictionaryItemServiceImpl implements DictionaryItemService {

	@Autowired
	private DictionaryItemClient dictionaryItemClient;

	@Override
	public List<DictionaryItem> findAllDictionaryItems() {
		ResponseEntity<? extends ArrayList<DictionaryItem>> dictionaryItemArrayList = dictionaryItemClient
				.getAllDictionaryItems();
		return (List<DictionaryItem>) dictionaryItemArrayList.getBody();
	}

	@Override
	public void disactivateDictionaryItem(Long dictionaryItem_id) {

		if (dictionaryItem_id != null) {
			DictionaryItemForm dictionaryItemForm = new DictionaryItemForm();
			dictionaryItemForm.setDicItemId(dictionaryItem_id);
			dictionaryItemForm.setDicItemActive(false);
			dictionaryItemClient.disactivateDictionaryItem(dictionaryItemForm);
		} else {
			log.error("Error durring disactivate dictionaryItem not found");
		}

	}

	@Override
	public void createNewDictionaryItem(DictionaryItem dictionaryItem) {

		try {
			dictionaryItemClient.addNewDictionaryItem(dictionaryItem);
		} catch (Exception e) {
			log.error(e);
		}

	}

	@Override
	public DictionaryItem findDictionaryItemById(Long dicItemId) {
		DictionaryItem dictionaryItemForm = new DictionaryItem();
		dictionaryItemForm.setDicItemId(dicItemId);
		DictionaryItem dictionaryItem = dictionaryItemClient.findDictionaryById(dictionaryItemForm);
		return dictionaryItem;
	}

	@Override
	public void editDictionaryItem(DictionaryItemForm dictionaryItemForm) {

		if (dictionaryItemForm.getDicItemId() != null) {
			dictionaryItemClient.editDictionaryItem(dictionaryItemForm);
		} else {
			log.error("Error durring edit Dictionary Item dictionaryitem Id null");
		}
	}

	@Override
	public void removeDictionaryItem(Long dicItemId) {
		if (dicItemId != null) {
			DictionaryItemForm dictionaryItemForm = new DictionaryItemForm();
			dictionaryItemForm.setDicItemId(dicItemId);
			dictionaryItemForm.setDicItemActive(false);
			dictionaryItemClient.removeDictionaryItem(dictionaryItemForm);
		} else {
			log.error("Error durring remove dictionaryItem not found");
		}

	}

}

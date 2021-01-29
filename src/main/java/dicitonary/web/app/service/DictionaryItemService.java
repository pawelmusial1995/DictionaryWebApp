package dicitonary.web.app.service;

import java.util.List;

import dicitonary.web.app.form.DictionaryItemForm;
import dicitonary.web.app.model.DictionaryItem;

public interface DictionaryItemService {

	List<DictionaryItem> findAllDictionaryItems();

	void disactivateDictionaryItem(Long dictionaryItem_id);

	void createNewDictionaryItem(DictionaryItem dictionaryItem);

	DictionaryItem findDictionaryItemById(Long dicItemId);

	void editDictionaryItem(DictionaryItemForm dictionaryItemForm);

	void removeDictionaryItem(Long dicItemId);

}

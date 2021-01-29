package dicitonary.web.app.form;



import dicitonary.web.app.model.Dictionary;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class DictionaryItemForm {

	private Long dicItemId;
	private String dicItemName;
	private String dicItemDefinition;
	private Boolean dicItemActive;

	private Dictionary dictionary;
}

package dicitonary.web.app.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DictionaryItem {

	private Long dicItemId;

	private String dicItemName;

	private String dicItemDefinition;

	private Boolean dicItemActive;

	private Dictionary dictionary;
}

package dicitonary.web.app.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dicitonary.web.app.form.DictionaryItemForm;
import dicitonary.web.app.model.DictionaryItem;
import dicitonary.web.app.service.DictionaryItemService;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class DictionaryItemController {

	@Autowired
	private DictionaryItemService dictionaryItemService;

	@GetMapping("/dictionaryItem")
	public String getDictionaryItemList(Model model) {
		List<DictionaryItem> dicitonaryItemlist = dictionaryItemService.findAllDictionaryItems();

		model.addAttribute("dicitonaryItemlist", dicitonaryItemlist);

		return "dictionaryItem/dictionaryItemList";
	}

	@GetMapping("/dictionaryItem/{dictionary_id}/details")
	public String getDictionaryItemList(@PathVariable Long dictionary_id, Model model, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		List<DictionaryItem> dicitonaryItemlist = dictionaryItemService.findAllDictionaryItems();

		model.addAttribute("dicitonaryItemlist", dicitonaryItemlist);

		return "dictionaryItem/dictionaryItemList";
	}

	@GetMapping("/dictionaryItem/disactivate/{dicItemId}")
	public String disactivateDictionaryItem(@PathVariable Long dicItemId, Model model, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {

		dictionaryItemService.disactivateDictionaryItem(dicItemId);
		return "redirect:/dictionaryItem";
	}

	@GetMapping(value = "/dictionaryItem/add")
	public String getDictionaryItemAdd(Model model) {

		model.addAttribute("dictionaryItem", new DictionaryItem());

		return "dictionaryItem/add";
	}

	@PostMapping("/dictionaryItem/add")
	public String addNewDictionaryItemAction(@ModelAttribute(value = "dictionaryItem") DictionaryItem dictionaryItem,
			BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {

		if (dictionaryItem != null) {
			dictionaryItemService.createNewDictionaryItem(dictionaryItem);
		} else {
			log.error("Error durring add new Dictionary item");
		}

		return "redirect:/dictionary";
	}

	@GetMapping("/dictionaryItem/{dicItemId}/edit")
	public String getEditDictionaryItemEdit(@PathVariable Long dicItemId, Model model, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {

		DictionaryItem dictionaryItem = dictionaryItemService.findDictionaryItemById(dicItemId);
		model.addAttribute("dictionaryItemForm", new DictionaryItemForm());
		model.addAttribute("dictionaryItem", dictionaryItem);
		return "dictionaryItem/edit";
	}

	@PostMapping("/dictionaryItem/edit/{dictionary_id}")
	public String editDictionaryItemAction(
			@ModelAttribute(name = "dictionaryItemForm") DictionaryItemForm dictionaryItemForm,
			BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {

		dictionaryItemService.editDictionaryItem(dictionaryItemForm);

		return "redirect:/dictionary";
	}

	@GetMapping("/dictionaryItem/remove/{dicItemId}")
	public String removeDictionaryItemAction(@PathVariable Long dicItemId, Model model, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		dictionaryItemService.removeDictionaryItem(dicItemId);
		return "redirect:/dictionaryItem";
	}

}

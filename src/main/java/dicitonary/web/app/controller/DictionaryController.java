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

import dicitonary.web.app.form.DictionaryForm;
import dicitonary.web.app.model.Dictionary;
import dicitonary.web.app.service.DictionaryService;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class DictionaryController {

	@Autowired
	private DictionaryService dictionaryService;

	@GetMapping("/dictionary")
	public String showDictionaryForm(Model model) {

		List<Dictionary> dictionaryList = dictionaryService.getAllDictionaries();
		model.addAttribute("dictionaryList", dictionaryList);
		return "dictionary/dictionaryList";
	}

	@GetMapping(value = "/dictionary/add")
	public String getDictionaryAddView(Model model) {

		model.addAttribute("dictionary", new Dictionary());

		return "dictionary/add";
	}

	@PostMapping("/dictionary/add")
	public String addDictionaryAction(@ModelAttribute(value = "dictionary") Dictionary dictionary,
			BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {

		if (dictionary != null) {
			dictionaryService.createNewDictionary(dictionary);

		} else {
			log.error("Error durring add dictionary");
		}

		return "redirect:/dictionary";
	}

	@GetMapping("/dictionary/{dictionary_id}/edit")
	public String getEditDictionary(@PathVariable Long dictionary_id, Model model, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {

		Dictionary dictionary = dictionaryService.findDictionaryById(dictionary_id);
		model.addAttribute("dictionaryForm", new DictionaryForm());
		model.addAttribute("dictionary", dictionary);

		return "dictionary/edit";
	}

	@PostMapping("/dictionary/edit/{dictionary_id}")
	public String editDictionaryAction(@ModelAttribute(name = "dictionaryForm") DictionaryForm dictionaryForm,
			BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {

		if (dictionaryForm != null) {
			dictionaryService.editDictionary(dictionaryForm);
		} else {
			log.error("Error durring edit Dictionary , data not valid");
		}
		return "redirect:/dictionary";
	}

	@GetMapping("/dictionary/disactivate/{dictionary_id}")
	public String disactivateDictionary(@PathVariable Long dictionary_id, Model model, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		if (dictionary_id != null) {
			dictionaryService.disactivateDicitonary(dictionary_id);
		} else {
			log.error("Error durring disactivate Dictionary , dictionaryId null");
		}

		return "redirect:/dictionary";
	}

}

package com.example.BookStore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.BookStore.domain.Category;
import com.example.BookStore.domain.CategoryRepository;



@Controller
public class CategoryController {
	
	@Autowired
	private CategoryRepository crepo;
	
	@GetMapping({"/categorylist"})
	public String booklistGet(Model model) {
		model.addAttribute("cates", crepo.findAll());
		return "categorylist";
	}
	
	@GetMapping("/addcategory")
	public String addbookGet(Model model) {
		model.addAttribute("cate", new Category());
		return "addcategory";
	}
	
	@PostMapping("/savecategory")
	public String savebookPost(Category cate) {
		crepo.save(cate);
		return "redirect:categorylist";
	}
}

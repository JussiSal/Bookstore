package com.example.BookStore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.BookStore.domain.Book;
import com.example.BookStore.domain.BookRepository;

@Controller
public class BookController {
	
	@Autowired
	private BookRepository brepo;
	
	@GetMapping("/index")
	public String getIndex() {
		return "index";
	}
	
	@GetMapping({"/", "/booklist"})
	public String booklistGet(Model model) {
		model.addAttribute("books", brepo.findAll());
		return "booklist";
	}
	
	@GetMapping("/delete/{id}")
	public String bookDelete(@PathVariable("id") Long id, Model model) {
		brepo.deleteById(id);
		return "redirect:../booklist";
	}
	
	@GetMapping("/addbook")
	public String addbookGet(Model model) {
		model.addAttribute("book", new Book());
		return "addbook";
	}
	
	@PostMapping("/savebook")
	public String savebookPost(Book book) {
		brepo.save(book);
		return "redirect:booklist";
	}
	
	@GetMapping("/editbook/{id}")
	public String editbookGet(@PathVariable("id") Long id, Model model) {
		Book book = brepo.findById(id).get();
		model.addAttribute("book", book);
		return "editbook";
	}
}

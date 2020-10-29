package com.example.BookStore.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.BookStore.domain.Book;
import com.example.BookStore.domain.BookRepository;
import com.example.BookStore.domain.CategoryRepository;

@Controller
public class BookController {
	
	@Autowired
	private BookRepository brepo;
	@Autowired
	private CategoryRepository crepo;
	
	@GetMapping("/index")
	public String getIndex() {
		return "index";
	}
	
	@RequestMapping(value="/login")
    public String login() {	
        return "login";
    }
	
	@GetMapping({"/", "/booklist"})
	public String booklistGet(Model model) {
		model.addAttribute("books", brepo.findAll());
		return "booklist";
	}
	
	@GetMapping("/delete/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String bookDelete(@PathVariable("id") Long id, Model model) {
		brepo.deleteById(id);
		return "redirect:../booklist";
	}
	
	@GetMapping("/addbook")
	public String addbookGet(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("cates", crepo.findAll());
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
		model.addAttribute("cates", crepo.findAll());
		return "editbook";
	}
	
	//Restiä
	@GetMapping("/books")
    public @ResponseBody List<Book> booksRest() {	
        return (List<Book>) brepo.findAll();
    }
    
    @GetMapping(value="/book/{id}")
    public @ResponseBody Book findBookRest(@PathVariable("id") Long id) {	
    	return brepo.findById(id).get();
    }
}

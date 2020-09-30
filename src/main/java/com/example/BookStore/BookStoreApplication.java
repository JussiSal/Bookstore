package com.example.BookStore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.BookStore.domain.Book;
import com.example.BookStore.domain.BookRepository;
import com.example.BookStore.domain.Category;
import com.example.BookStore.domain.CategoryRepository;

@SpringBootApplication
public class BookStoreApplication {

	//Tällä voidaan logittaa omatekoisia logeja
		private static final Logger log = LoggerFactory.getLogger(BookStoreApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(BookStoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner bookstoreDemo(BookRepository brepo, CategoryRepository crepo) {
		return (args) -> {
			Category cate1  = new Category("Tiede");
			Category cate2  = new Category("Fiktio");
			crepo.save(cate1);
			crepo.save(cate2);
			
			brepo.save(new Book("Sanakirja", "Joku Jokunen", 1987, "951-98548-9-1", 59.99, cate1));
			brepo.save(new Book("Hieno kirja", "Kalle Hieno", 2010, "951-98548-9-2", 79.99, cate2));
			brepo.save(new Book("Huono kirja", "Matti Huono", 2020, "951-98548-9-3", 2.99, cate1));
			log.info("Toimii");
			
			for (Category cate : crepo.findAll()) {
				log.info(cate.toString());
			}
			for (Book book : brepo.findAll()) {
				log.info(book.toString());
			}
			log.info("Päästään tännekkin asti");
		};
	}

}

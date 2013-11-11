package learningtest.org.springframework.cache.book.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import learningtest.org.springframework.cache.book.domain.Book;
import learningtest.org.springframework.cache.book.domain.ISBN;

@Service
public class SlowServiceImpl implements SlowService {

	// @Cacheable("books")
	@Cacheable(value = "books", key = "#isbn")
	public Book find(ISBN isbn, boolean checkWarehouse, boolean includeUsed) {
		return new Book();
	}

}

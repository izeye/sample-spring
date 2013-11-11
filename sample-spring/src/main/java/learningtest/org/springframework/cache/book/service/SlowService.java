package learningtest.org.springframework.cache.book.service;

import learningtest.org.springframework.cache.book.domain.Book;
import learningtest.org.springframework.cache.book.domain.ISBN;

public interface SlowService {

	Book find(ISBN isbn, boolean checkWarehouse, boolean includeUsed);

}

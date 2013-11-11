package learningtest.org.springframework.cache.book;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;
import learningtest.org.springframework.cache.book.domain.Book;
import learningtest.org.springframework.cache.book.domain.ISBN;
import learningtest.org.springframework.cache.book.service.SlowService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class CacheTest {

	@Autowired
	SlowService slowService;

	@Test
	public void test() {
		ISBN isbn = new ISBN();
		boolean checkWarehouse = false;
		boolean includeUsed = false;

		Book foundBook = slowService.find(isbn, checkWarehouse, includeUsed);

		assertThat(slowService.find(isbn, checkWarehouse, includeUsed),
				is(sameInstance(foundBook)));

		checkWarehouse = true;
		// assertThat(slowService.find(isbn, checkWarehouse, includeUsed),
		// is(not(sameInstance(foundBook))));
		assertThat(slowService.find(isbn, checkWarehouse, includeUsed),
				is(sameInstance(foundBook)));
	}

}

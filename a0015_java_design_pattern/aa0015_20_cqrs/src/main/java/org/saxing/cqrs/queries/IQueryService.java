package org.saxing.cqrs.queries;

import org.saxing.cqrs.dto.Author;
import org.saxing.cqrs.dto.Book;

import java.math.BigInteger;
import java.util.List;

/**
 * This interface represents the query methods of the CQRS pattern
 *
 * @author saxing 2018/12/1 21:51
 */
public interface IQueryService {

    Author getAuthorByUsername(String username);

    Book getBook(String title);

    List<Book> getAuthorBooks(String username);

    BigInteger getAuthorBooksCount(String username);

    BigInteger getAuthorsCount();

}

package org.saxing.cqrs.queries;

import org.saxing.cqrs.dto.Author;
import org.saxing.cqrs.dto.Book;

import java.math.BigInteger;
import java.util.List;

/**
 * This class is an implementation of {@link IQueryService}. It uses Hibernate native queries to return DTOs from the
 *  * database.
 *
 * @author saxing 2018/12/1 21:51
 */
public class QueryServiceImpl implements IQueryService {
    @Override
    public Author getAuthorByUsername(String username) {
        return null;
    }

    @Override
    public Book getBook(String title) {
        return null;
    }

    @Override
    public List<Book> getAuthorBooks(String username) {
        return null;
    }

    @Override
    public BigInteger getAuthorBooksCount(String username) {
        return null;
    }

    @Override
    public BigInteger getAuthorsCount() {
        return null;
    }
}

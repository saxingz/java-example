package org.saxing.cqrs;

import org.saxing.cqrs.commands.CommandServiceImpl;
import org.saxing.cqrs.commands.ICommandService;
import org.saxing.cqrs.dto.Author;
import org.saxing.cqrs.dto.Book;
import org.saxing.cqrs.queries.IQueryService;
import org.saxing.cqrs.queries.QueryServiceImpl;
import org.saxing.cqrs.util.HibernateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigInteger;
import java.util.List;

/**
 * main
 *
 * @author saxing 2018/12/1 21:55
 */
//@SpringBootApplication
public class Aa001520CqrsApplication {

//    public static void main(String[] args) {
//        SpringApplication.run(Aa001520CqrsApplication.class, args);
//    }

    private static final Logger LOGGER = LoggerFactory.getLogger(Aa001520CqrsApplication.class);

    public static void main(String[] args) {
        ICommandService commands = new CommandServiceImpl();

        // Create Authors and Books using CommandService
        commands.authorCreated("eEvans", "Eric Evans", "eEvans@email.com");
        commands.authorCreated("jBloch", "Joshua Bloch", "jBloch@email.com");
        commands.authorCreated("mFowler", "Martin Fowler", "mFowler@email.com");

        commands.bookAddedToAuthor("Domain-Driven Design", 60.08, "eEvans");
        commands.bookAddedToAuthor("Effective Java", 40.54, "jBloch");
        commands.bookAddedToAuthor("Java Puzzlers", 39.99, "jBloch");
        commands.bookAddedToAuthor("Java Concurrency in Practice", 29.40, "jBloch");
        commands.bookAddedToAuthor("Patterns of Enterprise Application Architecture", 54.01, "mFowler");
        commands.bookAddedToAuthor("Domain Specific Languages", 48.89, "mFowler");
        commands.authorNameUpdated("eEvans", "Eric J. Evans");

        IQueryService queries = new QueryServiceImpl();

        // Query the database using QueryService
        Author nullAuthor = queries.getAuthorByUsername("username");
        Author eEvans = queries.getAuthorByUsername("eEvans");
        BigInteger jBlochBooksCount = queries.getAuthorBooksCount("jBloch");
        BigInteger authorsCount = queries.getAuthorsCount();
        Book dddBook = queries.getBook("Domain-Driven Design");
        List<Book> jBlochBooks = queries.getAuthorBooks("jBloch");

        LOGGER.info("Author username : {}", nullAuthor);
        LOGGER.info("Author eEvans : {}", eEvans);
        LOGGER.info("jBloch number of books : {}", jBlochBooksCount);
        LOGGER.info("Number of authors : {}", authorsCount);
        LOGGER.info("DDD book : {}", dddBook);
        LOGGER.info("jBloch books : {}", jBlochBooks);

        HibernateUtil.getSessionFactory().close();

    }

}

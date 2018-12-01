package org.saxing.cqrs.commands;

/**
 *
 * @author saxing 2018/12/1 21:24
 */
public class CommandServiceImpl implements ICommandService  {


    @Override
    public void authorCreated(String username, String name, String email) {

    }

    @Override
    public void bookAddedToAuthor(String title, double price, String username) {

    }

    @Override
    public void authorNameUpdated(String username, String name) {

    }

    @Override
    public void authorUsernameUpdated(String oldUsername, String newUsername) {

    }

    @Override
    public void authorEmailUpdated(String username, String email) {

    }

    @Override
    public void bookTitleUpdated(String oldTitle, String newTitle) {

    }

    @Override
    public void bookPriceUpdated(String title, double price) {

    }
}

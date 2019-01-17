package org.saxing.frontcontroller;

/**
 * FrontController is the handler class that takes in all the requests and renders the correct
 * response.
 *
 * @author saxing 2019/1/17 22:58
 */
public class FrontController {

    public void handleRequest(String request){
        Command command = getCommand(request);
        command.process();
    }

    private Command getCommand(String request){
        Class<?> commandClass = getCommandClass(request);
        try {
            return (Command) commandClass.newInstance();
        } catch (Exception e) {
            throw new ApplicationException(e);
        }
    }

    private static Class<?> getCommandClass(String request){
        Class<?> result;
        try{
            result = Class.forName("org.saxing.frontcontroller." + request + "Command");
        }catch (ClassNotFoundException e){
            result = UnknownCommand.class;
        }
        return result;
    }

}

package de.gothaer;

public class ConsoleAppender implements Appender{
    @Override
    public void write(String message) {
        System.out.println(message);
    }
}

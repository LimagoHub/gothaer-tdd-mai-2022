package de.gothaer;

public class Logger {

    private final Appender appender;

    public Logger(Appender appender) {
        this.appender = appender;
    }


    public void log(String message){
        String time = "Prefix";

        appender.write(time.toString() + " " + message);
    }
}

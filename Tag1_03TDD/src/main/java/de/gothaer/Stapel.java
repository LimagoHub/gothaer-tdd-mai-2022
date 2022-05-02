package de.gothaer;

public class Stapel {

    private boolean empty = true;
    public boolean isEmpty() {
        return empty;
    }

    public void push(Object o) {
        empty = false;
    }
}

package com.nathanstruhs.namemangler;

public class MangledName {

    private String name;
    private String randomWord;

    public MangledName(String name, String randomWord) {
        this.name = name;
        this.randomWord = randomWord;
    }

    public String toString() {
        return this.name + " " + this.randomWord;
    }
}

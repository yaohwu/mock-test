package com.yaoh.test;


import java.util.Random;

/**
 * @author yaoh.wu
 */
public class Job {
    private String name;

    public static String generateID(String args) {
        return args + "" + new Random().nextInt(1000);
    }

    public Job(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

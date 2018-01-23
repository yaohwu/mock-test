package com.yaoh.test;


/**
 * @author yaoh.wu
 */
public class Person {

    private int id;
    private String name;
    private Job job;

    public Person() {
        this(1, "noname", new Job("nojob"));
    }

    public Person(int id, String name, Job job) {
        this.id = id;
        this.name = name;
        this.job = job;
    }

    public String getJobName() {
        return job.getName();
    }

    public String getJobID() {
        return Job.generateID(this.name);
    }

    public String getJobNamePrefixID() {
        return getJobID() + getJobName();
    }

    @Override
    public String toString() {
        return "#" + id + ": " + name;
    }
}

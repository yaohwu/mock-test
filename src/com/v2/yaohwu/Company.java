package com.v2.yaohwu;

import com.v2.yaohwu.gov.Manager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.StringJoiner;
import java.util.UUID;

public class Company {

    private String id;
    private String name;

    private Date createTime;

    private List<Singer> singers = new ArrayList<>();


    public Company(String name) {

        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.createTime = new Date();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Singer> getSingers() {
        return Collections.unmodifiableList(singers);
    }

    public void registerSinger(Singer singer) {

        Manager.register(this, singer);

        singers.add(singer);

    }

    public void discharge(Singer singer) {

        Manager.discharge(this, singer);

        singers.remove(singer);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Company.class.getSimpleName() + "[", "]")
                .add("id='" + id + "'")
                .add("name='" + name + "'")
                .add("createTime=" + createTime)
                .toString();
    }
}

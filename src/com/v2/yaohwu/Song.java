package com.v2.yaohwu;

import java.util.StringJoiner;

public class Song {

    private Singer singer;
    private Company company;

    public Song(Singer singer, Company company) {
        this.singer = singer;
        this.company = company;
    }


    @Override
    public String toString() {
        return new StringJoiner(", ", Song.class.getSimpleName() + "[", "]")
                .add("singer=" + singer)
                .add("company=" + company)
                .toString();
    }
}

package com.v2.yaohwu.activity;

import com.v2.yaohwu.Singer;

public class VocalConcert {

    private Singer singer;

    public VocalConcert() {
    }

    public void setSinger(Singer singer) {
        this.singer = singer;
    }

    public String show() {
        if (singer != null) {
            return "Vocal Concert Show: " + singer.show();
        } else {
            return "Vocal Concert Show: ...";
        }
    }
}

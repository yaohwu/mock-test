package com.v2.yaohwu;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringJoiner;

public class Singer {
    private String name;
    private Date birthday;

    public Singer(String name, Date birthday) {
        this.name = name;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String show() {
        return "Now it's time to show, let's welcome the singer: \n" + this;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Singer.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("birthday=" + new SimpleDateFormat("yyyy-MM-dd").format(birthday))
                .toString();
    }
}

package com.v2.yaohwu.gov;

import com.v2.yaohwu.Company;
import com.v2.yaohwu.Singer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Manager {

    private static final List<Record> RECORDS = new ArrayList<>();

    static {
        System.out.println("Manager Init");
    }


    public static void register(Company company, Singer singer) {
        operate(company, singer, Operate.REGISTER);
    }

    public static void discharge(Company company, Singer singer) {
        operate(company, singer, Operate.DISCHARGE);
    }

    public static List<Record> getAllRecord() {
        return Collections.unmodifiableList(RECORDS);
    }

    private static void operate(Company company, Singer singer, Operate operate) {
        if (check()) {
            record(
                    new Record(System.currentTimeMillis(), company, singer, operate)
            );
        } else {
            throw new RuntimeException("unqualified");
        }
    }

    private static void record(Record record) {
        RECORDS.add(record);
    }

    private static boolean check() {
        // do something check
        return true;
    }


    private static class Record {
        private long timestamp;
        private Company company;
        private Singer singer;
        private Operate operate;

        public Record(long timestamp, Company company, Singer singer, Operate operate) {
            this.timestamp = timestamp;
            this.company = company;
            this.singer = singer;
            this.operate = operate;
        }

        public long getTimestamp() {
            return timestamp;
        }

        public Company getCompany() {
            return company;
        }

        public Singer getSinger() {
            return singer;
        }

        public Operate getOperate() {
            return operate;
        }
    }

    private enum Operate {
        REGISTER,
        DISCHARGE
    }
}



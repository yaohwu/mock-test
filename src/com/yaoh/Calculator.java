package com.yaoh;

import com.yaoh.opreator.Add;

/**
 * @author yaoh.wu
 */
public class Calculator {

    private Add adder;

    public int add(int x, int y) {

        //do something special

        return adder.add(x, y);
    }

    public void setAdder(Add adder) {
        this.adder = adder;
    }

}

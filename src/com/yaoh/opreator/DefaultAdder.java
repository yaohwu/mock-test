package com.yaoh.opreator;

import com.yaoh.operate.Add;

/**
 * @author yaoh.wu
 */
public class DefaultAdder implements Add {

    @Override
    public int add(int x, int y) {
        return x + y;
    }
}

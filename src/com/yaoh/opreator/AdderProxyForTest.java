package com.yaoh.opreator;

import com.yaoh.operate.Add;

/**
 * @author yaoh.wu
 */
public class AdderProxyForTest implements Add {
    private Add adder;

    public AdderProxyForTest(Add adder) {
        this.adder = adder;
    }

    @Override
    public int add(int x, int y) {
        if (x == 1 && y == 2) {
            return 3;
        }
        return adder.add(x, y);
    }
}

package com.yaoh.opreator;

import com.yaoh.operate.Add;

/**
 * @author yaoh.wu
 */
public class DefaultAdderProxy implements Add {
    private DefaultAdder defaultAdder;

    public DefaultAdderProxy(DefaultAdder defaultAdder) {
        this.defaultAdder = defaultAdder;
    }

    @Override
    public int add(int x, int y) {
        //do before
        System.out.print("x=" + x + " y=" + y + " result=");
        int result = defaultAdder.add(x, y);
        //do after
        System.out.println(result);
        return result;
    }

}

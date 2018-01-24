package com.yaoh;

import com.yaoh.operate.Add;
import com.yaoh.opreator.AdderProxyForTest;
import org.junit.Test;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.junit.Assert.assertEquals;

/**
 * @author yaoh.wu
 */
public class CalculatorTest {


    /**
     * 一般的测试方法
     */
    @Test
    public void useCommon() {
        Calculator calculator = new Calculator();

        //给一个Add的实现类
        calculator.setAdder(new Add() {
            @Override
            public int add(int x, int y) {
                return x + y;
            }
        });

        assertEquals(3, calculator.add(1, 2));
    }


    @Test
    public void useStaticProxy() {
        AdderProxyForTest adderProxyForTest = new AdderProxyForTest(new Add() {
            @Override
            public int add(int x, int y) {
                //随意写，因为我们是用来测试的，注定走不到被代理类的方法。
                return 0;
            }
        });

        Calculator calculator = new Calculator();
        calculator.setAdder(adderProxyForTest);

        assertEquals(3, calculator.add(1, 2));

    }

    @Test
    public void useDynamicProxy() {


    }

    /**
     * 使用Mock的测试方法
     */
    @Test
    public void useMock() {
        Calculator calculator = new Calculator();

        Add adder = createMock(Add.class);
        calculator.setAdder(adder);

        expect(adder.add(1, 2)).andReturn(3).anyTimes();
        replay(adder);

        assertEquals(3, calculator.add(1, 2));

    }

}

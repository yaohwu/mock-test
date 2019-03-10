package com.v2.yaohwu.gov;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.core.classloader.annotations.SuppressStaticInitializationFor;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import java.util.ArrayList;
import java.util.List;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Manager.class})
@SuppressStaticInitializationFor("com.v2.yaohwu.gov.Manager")
public class ManagerTest {

    @Test
    public void test() {
        // 正常测试
        List expected = new ArrayList();
        PowerMock.mockStatic(Manager.class);
        //noinspection unchecked
        EasyMock.expect(Manager.getAllRecord()).andReturn(expected).anyTimes();

        PowerMock.replayAll();
        List result = Manager.getAllRecord();
        System.out.println(result.size());

        // 获取变量 RECORDS 的值
        System.out.println((String) Whitebox.getInternalState(Manager.class, "RECORDS"));
        // 对 Manager 中的私有变量进行赋值
        List expected2 = new ArrayList();
        try {

            // 私有变量 RECORDS 中加入 一个 Manager 私有内部类 Record 的一个实例
            //noinspection unchecked
            expected2.add(Whitebox.newInstance(Whitebox.getInnerClassType(Manager.class, "Record")));
        } catch (ClassNotFoundException e) {
            Assert.fail(e.getMessage());
        }
        // 将变量 RECORDS 赋值
        Whitebox.setInternalState(Manager.class, "RECORDS", expected2);
        // 获取变量 RECORDS 的值
        System.out.println(((List) Whitebox.getInternalState(Manager.class, "RECORDS")).size());
    }
}

package com.v2.yaohwu;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Test;

public class SingerTest3 {

    @Test
    public void test() {
        Singer singer =
                EasyMock.partialMockBuilder(Singer.class)
                        .addMockedMethod("getName")
                        .createMock();

        EasyMock.expect(singer.getName()).andReturn("yaohwu");
        EasyMock.replay(singer);

        System.out.println(singer.getName());
        Assert.assertNull(singer.getBirthday());
    }
}

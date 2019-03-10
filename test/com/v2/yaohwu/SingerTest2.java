package com.v2.yaohwu;

import com.v2.yaohwu.Singer;
import org.easymock.EasyMock;
import org.easymock.EasyMockRunner;
import org.easymock.Mock;
import org.easymock.MockType;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;

@RunWith(EasyMockRunner.class)
public class SingerTest2 {

    @Mock
    private Singer defaultSinger;

    @Mock(MockType.STRICT)
    private Singer strictSinger;

    @Mock(MockType.NICE)
    private Singer niceSinger;

    @Test
    public void test() {
        // default
        EasyMock.expect(defaultSinger.getName()).andReturn("yaohwu").once();
        EasyMock.expect(defaultSinger.getBirthday()).andReturn(new Date()).once();
        EasyMock.expect(defaultSinger.getName()).andReturn("new yaohwu").once();

        EasyMock.replay(defaultSinger);

        System.out.println(defaultSinger.getBirthday());
        System.out.println(defaultSinger.getName());
        System.out.println(defaultSinger.getName());

        EasyMock.verify(defaultSinger);

        // strict
        EasyMock.expect(strictSinger.getName()).andReturn("yaohwu").once();
        EasyMock.expect(strictSinger.getBirthday()).andReturn(new Date()).once();
        EasyMock.expect(strictSinger.getName()).andReturn("new yaohwu").once();

        EasyMock.replay(strictSinger);

        System.out.println(strictSinger.getName());
        System.out.println(strictSinger.getBirthday());
        System.out.println(strictSinger.getName());

        EasyMock.verify(strictSinger);

        // nice
        EasyMock.expect(niceSinger.getName()).andReturn("yaohwu").once();
        //EasyMock.expect(niceSinger.getBirthday()).andReturn(new Date()).once();
        //EasyMock.expect(niceSinger.getName()).andReturn("new yaohwu").once();

        EasyMock.replay(niceSinger);

        System.out.println(niceSinger.getBirthday());
        System.out.println(niceSinger.getName());
        System.out.println(niceSinger.getName());

        EasyMock.verify(niceSinger);
    }
}

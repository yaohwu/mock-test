package com.v2.yaohwu;

import org.easymock.EasyMock;
import org.easymock.EasyMockRunner;
import org.easymock.Mock;
import org.easymock.MockType;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Calendar;
import java.util.GregorianCalendar;

@RunWith(EasyMockRunner.class)
public class SingerTest {

    @Mock(MockType.STRICT)
    private Singer strictSingerAnnotation;

    @Mock(MockType.NICE)
    private Singer niceSingerAnnotation;

    @Mock
    private Singer defaultSingerAnnotation;


    @Test
    public void testShow() {

        GregorianCalendar calendar =
                new GregorianCalendar();
        calendar.set(1993, Calendar.JANUARY, 1);


        String expected = new Singer("a", calendar.getTime()).show();

        Singer defaultSinger = EasyMock.mock(Singer.class);
        Singer strictSinger = EasyMock.strictMock(Singer.class);
        Singer niceSinger = EasyMock.niceMock(Singer.class);

        EasyMock.expect(defaultSinger.show()).andReturn(expected).once();
        EasyMock.expect(defaultSinger.getName()).andReturn("b").once();
        defaultSinger.setName("c");
        EasyMock.expectLastCall()
                .andThrow(new RuntimeException("Error Cannot Reset Name")).once()
                .andVoid().once()
                .andThrow(new RuntimeException("Error Cannot Reset Name For More Times")).anyTimes();

        EasyMock.replay(defaultSinger);

        System.out.println(defaultSinger.show());
        try {
            defaultSinger.setName("c");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        defaultSinger.setName("c");
        try {
            defaultSinger.setName("c");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

    }
}

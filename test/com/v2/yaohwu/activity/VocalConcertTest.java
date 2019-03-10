package com.v2.yaohwu.activity;

import com.v2.yaohwu.Singer;
import org.easymock.EasyMock;
import org.easymock.EasyMockRunner;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(EasyMockRunner.class)
public class VocalConcertTest {

    @TestSubject
    private VocalConcert concert = new VocalConcert();

    @Mock
    private Singer defaultMockSinger;

    @Test
    public void testVocalConcert() {
        EasyMock.expect(defaultMockSinger.show()).andReturn("defaultMockSinger show").anyTimes();

        EasyMock.expect(defaultMockSinger.getName()).andStubReturn("");
        EasyMock.expect(defaultMockSinger.getBirthday()).andStubThrow(new RuntimeException("Error e"));

        EasyMock.replay(defaultMockSinger);

        System.out.println(concert.show());
    }
}

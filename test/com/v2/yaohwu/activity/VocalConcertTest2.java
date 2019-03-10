package com.v2.yaohwu.activity;

import com.v2.yaohwu.Singer;
import org.easymock.EasyMock;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
public class VocalConcertTest2 {

    @TestSubject
    private VocalConcert concert = new VocalConcert();

    @Mock
    private Singer defaultMockSinger;

    @Test
    public void testVocalConcert() {
        EasyMock.expect(defaultMockSinger.show()).andReturn("defaultMockSinger show").anyTimes();

        EasyMock.replay(defaultMockSinger);

        System.out.println(concert.show());
    }
}

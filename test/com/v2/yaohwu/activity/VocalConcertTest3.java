package com.v2.yaohwu.activity;

import com.v2.yaohwu.Singer;
import org.easymock.EasyMock;
import org.easymock.EasyMockRule;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Rule;
import org.junit.Test;

public class VocalConcertTest3 {

    @Rule
    public EasyMockRule mocks = new EasyMockRule(this);

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

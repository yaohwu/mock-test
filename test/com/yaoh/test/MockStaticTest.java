package com.yaoh.test;

import org.easymock.EasyMock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertEquals;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Job.class)
public class MockStaticTest {

    @Test
    public void testMethod() {
        Job job = EasyMock.createMock(Job.class);
        String jobName = "owen";
        EasyMock.expect(job.getName()).andReturn(jobName);
        EasyMock.replay(job);

        Person person = new Person(123, "testName", job);

        assertEquals(person.getJobName(), jobName);
    }

    @Test
    public void testStaticMethod() {
        Job job = EasyMock.createMock(Job.class);
        String expectID = "1234";
        PowerMock.mockStatic(Job.class);
        EasyMock.expect(Job.generateID("testName")).andReturn(expectID).anyTimes();
        PowerMock.replay(Job.class);

        Person person = new Person(123, "testName", job);

        assertEquals(person.getJobID(), expectID);
    }

    @Test
    public void testMix() {
        Job job = EasyMock.createMock(Job.class);
        String expectID = "1234";
        PowerMock.mockStatic(Job.class);
        EasyMock.expect(Job.generateID("testName")).andReturn(expectID);
        PowerMock.replay(Job.class);
        String jobName = "owen";
        EasyMock.expect(job.getName()).andReturn(jobName).anyTimes();
        EasyMock.replay(job);

        Person person = new Person(123, "testName", job);

        assertEquals(person.getJobNamePrefixID(), expectID + jobName);
    }
}

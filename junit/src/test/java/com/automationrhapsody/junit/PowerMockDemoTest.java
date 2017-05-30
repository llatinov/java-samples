package com.automationrhapsody.junit;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;

@RunWith(PowerMockRunner.class)
@PrepareForTest(PowerMockDemo.class)
public class PowerMockDemoTest {

    private PowerMockDemo powerMockDemo;
    private PowerMockDemo powerMockDemoSpy;

    @Before
    public void setUp() {
        powerMockDemo = new PowerMockDemo();
        powerMockDemoSpy = PowerMockito.spy(new PowerMockDemo());
    }

    @Test
    public void testCallPrivateMethod() throws Exception {
        Point actual = Whitebox.invokeMethod(powerMockDemo, "privateMethod", new Point(11, 11));

        assertThat(actual.getX(), is(12));
        assertThat(actual.getY(), is(12));
    }

    @Test
    public void testMockPrivateMethod() throws Exception {
        Point mockPoint = mock(Point.class);

        PowerMockito.doReturn(mockPoint).when(powerMockDemoSpy, "privateMethod", anyObject());

        Point actualMockPoint = powerMockDemoSpy.callPrivateMethod();

        assertThat(actualMockPoint, is(mockPoint));
    }

    @Test
    public void testMockNew() throws Exception {
        Point mockPoint = mock(Point.class);

        PowerMockito.whenNew(Point.class).withAnyArguments().thenReturn(mockPoint);

        Point actualMockPoint = powerMockDemo.publicMethod();

        assertThat(actualMockPoint, is(mockPoint));
    }
}

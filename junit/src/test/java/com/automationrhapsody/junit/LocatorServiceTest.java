package com.automationrhapsody.junit;

import com.automationrhapsody.junit.utils.PointUtils;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.verification.VerificationModeFactory;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Utils.class)
public class LocatorServiceTest {

    private LocatorService locatorServiceUnderTest;

    @Before
    public void setUp() {
        PowerMockito.mockStatic(Utils.class);

        locatorServiceUnderTest = new LocatorService();
    }

    @Test
    public void testGeoLocate() {
        Point input = new Point(11, 11);

        assertTrue(PointUtils.arePointsEqual(input, locatorServiceUnderTest.geoLocate(input)));
    }

    @Test
    public void testGeneratePointWithinDistance() {
        int distance = 111;
        when(Utils.randomDistance(anyInt())).thenReturn(distance);

        Point input = new Point(11, 11);
        Point expected = new Point(input.getX() + distance, input.getY() + distance);
        Point actual = locatorServiceUnderTest.generatePointWithinDistance(input, 1);

        assertTrue(PointUtils.arePointsEqual(expected, actual));
    }

    @Test
    public void testStaticMethodCall() {
        locatorServiceUnderTest.generatePointWithinDistance(new Point(11, 11), 1);
        locatorServiceUnderTest.generatePointWithinDistance(new Point(11, 11), 234);

        PowerMockito.verifyStatic(VerificationModeFactory.times(2));
        Utils.randomDistance(1);

        PowerMockito.verifyStatic(VerificationModeFactory.times(2));
        Utils.randomDistance(234);

        PowerMockito.verifyNoMoreInteractions(Utils.class);
    }
}

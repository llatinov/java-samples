package com.automationrhapsody.java8;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BasicStreamExamplesTest {

    @Test
    public void test_toLongList() {
        List<String> stringList = Arrays.asList(null, "", "aaa", "345", "123", "234", "123");

        List<Long> result = BasicStreamExamples.toLongList(stringList);

        assertEquals(3, result.size());
        assertEquals(123L, (long) result.get(0));
        assertEquals(234L, (long) result.get(1));
        assertEquals(345L, (long) result.get(2));

        List<Long> resultNoStream = BasicStreamExamples.toLongListWithoutStream(stringList);
        assertEquals(result, resultNoStream);
    }

    @Test
    public void test_toLongArray() {
        String[] stringArray = new String[] {null, "", "aaa", "123", "234"};

        Long[] result = BasicStreamExamples.toLongArray(stringArray);

        assertEquals(2, result.length);
        assertEquals(123L, (long) result[0]);
        assertEquals(234L, (long) result[1]);
    }

    @Test
    public void test_flapMap() {
        Map<String, List<String>> map = new HashMap<>();
        map.put("1", Arrays.asList("a", "b"));
        map.put("2", Arrays.asList("C", "D"));

        List<String> expectedResult = Arrays.asList("a", "b", "C", "D");

        List<String> result = BasicStreamExamples.flapMap(map);

        assertEquals(expectedResult, result);
    }

    @Test
    public void test_limitValues() {
        List<String> stringList = Arrays.asList("a", "b", "c", "d");

        List<String> result = BasicStreamExamples.limitValues(stringList, 2);

        assertEquals(2, result.size());
        assertEquals("a", result.get(0));
        assertEquals("b", result.get(1));
    }

    @Test
    public void test_skipValues() {
        List<String> stringList = Arrays.asList("a", "b", "c", "d");

        List<String> result = BasicStreamExamples.skipValues(stringList, 2);

        assertEquals(2, result.size());
        assertEquals("c", result.get(0));
        assertEquals("d", result.get(1));
    }

    @Test
    public void test_printEachElement() {
        List<String> stringList = Arrays.asList("a", "b", "c", "d");

        BasicStreamExamples.printEachElement(stringList);
    }

    @Test
    public void test_getMin() {
        List<Integer> integerList = Arrays.asList(234, 123, 345);

        Optional<Integer> result = BasicStreamExamples.getMin(integerList);

        assertEquals(123, (int) result.get());
    }

    @Test
    public void test_getMax() {
        List<Integer> integerList = Arrays.asList(234, 123, 345);

        Optional<Integer> result = BasicStreamExamples.getMax(integerList);

        assertEquals(345, (int) result.get());
    }

    @Test
    public void test_sumByReduce() {
        List<Integer> integerList = Arrays.asList(100, 200, 300);

        Optional<Integer> result = BasicStreamExamples.sumByReduce(integerList);

        assertEquals(600, (int) result.get());
    }

    @Test
    public void test_count() {
        List<Integer> integerList = Arrays.asList(234, 123, 345);

        long result = BasicStreamExamples.count(integerList);

        assertEquals(3, result);
    }

    @Test
    public void test_anyMatch_allMatch_noneMatch_allEven() {
        List<Integer> integerList = Arrays.asList(234, 124, 346, 124);

        assertFalse(BasicStreamExamples.isOddElementPresent(integerList));
        assertFalse(BasicStreamExamples.areAllElementsOdd(integerList));
        assertTrue(BasicStreamExamples.areAllElementsEven(integerList));
    }

    @Test
    public void test_anyMatch_allMatch_noneMatch_evenAndOdd() {
        List<Integer> integerList = Arrays.asList(234, 123, 345, 123);

        assertTrue(BasicStreamExamples.isOddElementPresent(integerList));
        assertFalse(BasicStreamExamples.areAllElementsOdd(integerList));
        assertFalse(BasicStreamExamples.areAllElementsEven(integerList));
    }

    @Test
    public void test_anyMatch_allMatch_noneMatch_allOdd() {
        List<Integer> integerList = Arrays.asList(233, 123, 345, 123);

        assertTrue(BasicStreamExamples.isOddElementPresent(integerList));
        assertTrue(BasicStreamExamples.areAllElementsOdd(integerList));
        assertFalse(BasicStreamExamples.areAllElementsEven(integerList));
    }

    @Test
    public void test_getFirstElementList() {
        List<Integer> integerList = Arrays.asList(234, 123, 345, 123);

        Optional<Integer> result = BasicStreamExamples.getFirstElementList(integerList);

        assertEquals(Integer.valueOf(234), result.get());
    }

    @Test
    public void test_getFirstElementSet() {
        Set<Integer> integerSet = new HashSet<>();
        integerSet.add(234);
        integerSet.add(123);
        integerSet.add(345);
        integerSet.add(123);

        Optional<Integer> result = BasicStreamExamples.getFirstElementSet(integerSet);

        assertEquals(Integer.valueOf(345), result.get());
    }

    @Test
    public void test_getGetAnyElement() {
        List<Integer> integerList = Arrays.asList(234, 123, 345, 123);

        Optional<Integer> result = BasicStreamExamples.getAnyElement(integerList);

        assertEquals(Integer.valueOf(234), result.get());
    }
}
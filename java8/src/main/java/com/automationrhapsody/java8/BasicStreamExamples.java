package com.automationrhapsody.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang.math.NumberUtils;

public class BasicStreamExamples {

    public static List<Long> toLongList(List<String> stringList) {
        return stringList.stream()
            .peek(element -> System.out.println("Before: " + element))
            .filter(NumberUtils::isNumber)
            .map(Long::valueOf)
            .distinct()
            .sorted()
            .peek(element -> System.out.println("After: " + element))
            .collect(Collectors.toList());
    }

    public static List<Long> toLongListWithoutStream(List<String> stringList) {
        List<Long> result = new ArrayList<>();
        for (String value : stringList) {
            System.out.println("Before: " + value);
            if (NumberUtils.isNumber(value)) {
                Long longValue = Long.valueOf(value);
                if (!result.contains(longValue)) {
                    result.add(longValue);
                    System.out.println("After: " + value);
                }
            }
        }
        Collections.sort(result);
        return result;
    }

    public static Long[] toLongArray(String[] stringArray) {
        return Arrays.stream(stringArray)
            .filter(NumberUtils::isNumber)
            .map(Long::valueOf)
            .toArray(Long[]::new);
    }

    public static List<String> flapMap(Map<String, List<String>> mapToProcess) {
        return mapToProcess.entrySet()
            .stream()
            .flatMap(map -> map.getValue().stream())
            .collect(Collectors.toList());
    }

    public static List<String> limitValues(List<String> stringList, long limit) {
        return stringList.stream()
            .limit(limit)
            .collect(Collectors.toList());
    }

    public static List<String> skipValues(List<String> stringList, long skip) {
        return stringList.stream()
            .skip(skip)
            .collect(Collectors.toList());
    }

    public static void printEachElement(List<String> stringList) {
        stringList.stream()
            .forEach(element -> System.out.println("Element: " + element));
    }

    public static Optional<Integer> getMin(List<Integer> stringList) {
        return stringList.stream()
            .min(Long::compare);
    }

    public static Optional<Integer> getMax(List<Integer> integers) {
        return integers.stream()
            .max(Long::compare);
    }

    public static Optional<Integer> sumByReduce(List<Integer> integers) {
        return integers.stream()
            .reduce((x, y) -> x + y);
    }

    public static long count(List<Integer> integers) {
        return integers.stream()
            .count();
    }

    public static boolean isOddElementPresent(List<Integer> integers) {
        return integers.stream()
            .anyMatch(element -> element % 2 != 0);
    }

    public static boolean areAllElementsOdd(List<Integer> integers) {
        return integers.stream()
            .allMatch(element -> element % 2 != 0);
    }

    public static boolean areAllElementsEven(List<Integer> integers) {
        return integers.stream()
            .noneMatch(element -> element % 2 != 0);
    }

    public static Optional<Integer> getFirstElementList(List<Integer> integers) {
        return integers.stream()
            .findFirst();
    }

    public static Optional<Integer> getFirstElementSet(Set<Integer> integers) {
        return integers.stream()
            .findFirst();
    }

    public static Optional<Integer> getAnyElement(List<Integer> integers) {
        return integers.stream()
            .findAny();
    }
}

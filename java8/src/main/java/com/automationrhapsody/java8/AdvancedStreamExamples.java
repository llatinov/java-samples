package com.automationrhapsody.java8;

import java.util.Arrays;
import java.util.Collections;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

public class AdvancedStreamExamples {

    public static List<PeekObject> limit_shortCircuiting(List<String> stringList, int limit) {
        return stringList.stream()
            .map(PeekObject::new)
            .limit(limit)
            .collect(Collectors.toList());
    }

    public static List<PeekObject> sorted_notShortCircuiting(List<String> stringList, int limit) {
        return stringList.stream()
            .map(PeekObject::new)
            .sorted((left, right) -> left.getMessage().compareTo(right.getMessage()))
            .limit(limit)
            .collect(Collectors.toList());
    }

    public static Map<Long, Long> splitToMap(List<String> stringsList) {
        return stringsList.stream()
            .filter(StringUtils::isNotEmpty)
            .map(line -> line.split(","))
            .filter(array -> array.length == 2 && NumberUtils.isNumber(array[0]) && NumberUtils.isNumber(array[1]))
            .collect(Collectors.toMap(array -> Long.valueOf(array[0]),
                array -> Long.valueOf(array[1]), (first, second) -> first));
    }

    public static List<String> gatherEmployeeSkills(List<Employee> employees, Position... positions) {
        positions = positions == null || positions.length == 0 ? Position.values() : positions;
        List<Position> searchPositions = Arrays.stream(positions).collect(Collectors.toList());
        return employees == null ? Collections.emptyList()
            : employees.stream()
                .filter(employee -> searchPositions.contains(employee.getPosition()))
                .flatMap(employee -> employee.getSkills().stream())
                .distinct()
                .collect(Collectors.toList());
    }

    public static String printEmployeeSkills(List<Employee> employees, Position position) {
        List<String> skills = gatherEmployeeSkills(employees, position);
        return skills.stream()
            .collect(Collectors.joining("; ", "Our " + position + "s have: ", " skills"));
    }

    public static Map<Position, IntSummaryStatistics> salaryStatistics(List<Employee> employees) {
        return employees.stream()
            .collect(Collectors.groupingBy(Employee::getPosition,
                Collectors.summarizingInt(Employee::getSalary)));
    }

    public static Position positionWithLowestAverageSalary(List<Employee> employees) {
        return salaryStatistics(employees)
            .entrySet().stream()
            .sorted((entry1, entry2) -> Double.compare(entry1.getValue().getAverage(), entry2.getValue().getAverage()))
            .findFirst()
            .get()
            .getKey();
    }

    public static Map<Position, List<Employee>> employeesPerPosition(List<Employee> employees) {
        return employees.stream()
            .collect(Collectors.groupingBy(Employee::getPosition, Collectors.toList()));
    }

    public static Map<Position, List<String>> employeeNamesPerPosition(List<Employee> employees) {
        return employees.stream()
            .collect(Collectors.groupingBy(Employee::getPosition,
                Collectors.mapping(Employee::getName, Collectors.toList())));
    }

    public static Map<Position, Long> employeesCountPerPosition(List<Employee> employees) {
        return employees.stream()
            .collect(Collectors.groupingBy(Employee::getPosition, Collectors.counting()));
    }

    public static List<Employee> employeesWithDuplicateFirstName(List<Employee> employees) {
        return employees.stream()
            .collect(Collectors.groupingBy(Employee::getFirstName, Collectors.toList()))
            .entrySet().stream()
            .filter(entry -> entry.getValue().size() > 1)
            .flatMap(entry -> entry.getValue().stream())
            .collect(Collectors.toList());
    }
}

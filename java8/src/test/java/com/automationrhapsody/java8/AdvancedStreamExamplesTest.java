package com.automationrhapsody.java8;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class AdvancedStreamExamplesTest {

    @Test
    public void test_limit_shortCircuiting() {
        System.out.println("limit_shortCircuiting");

        List<String> stringList = Arrays.asList("a", "b", "a", "c", "d", "a");

        List<PeekObject> result = AdvancedStreamExamples.limit_shortCircuiting(stringList, 2);

        assertThat(result.size(), is(2));
    }

    @Test
    public void test_sorted_notShortCircuiting() {
        System.out.println("sorted_notShortCircuiting");

        List<String> stringList = Arrays.asList("a", "b", "a", "c", "d", "a");

        List<PeekObject> result = AdvancedStreamExamples.sorted_notShortCircuiting(stringList, 2);

        assertThat(result.size(), is(2));
    }

    @Test
    public void test_splitToMap() {
        List<String> stringList = Arrays.asList("11,21", "12,21", "13,23", "13,24");

        Map<Long, Long> result = AdvancedStreamExamples.splitToMap(stringList);

        assertThat(result.size(), is(3));
        assertThat(result.get(11L), is(21L));
        assertThat(result.get(12L), is(21L));
        assertThat(result.get(13L), is(23L));
    }

    @Test
    public void test_gatherEmployeeSkills() {
        List<Employee> company = createCompany();

        List<String> skills = AdvancedStreamExamples.gatherEmployeeSkills(company);

        assertThat(skills.size(), is(25));
    }

    @Test
    public void test_printEmployeeSkills() {
        List<Employee> company = createCompany();

        String skills = AdvancedStreamExamples.printEmployeeSkills(company, Position.QA);

        assertThat(skills, is("Our employees have: Selenium; C#; Java; JavaScript; Protractor skills"));
    }

    @Test
    public void test_salaryStatistics() {
        List<Employee> company = createCompany();

        Map<Position, IntSummaryStatistics> salaries = AdvancedStreamExamples.salaryStatistics(company);

        assertThat(salaries.get(Position.DEV).getAverage(), is(114D));
        assertThat(salaries.get(Position.QA).getAverage(), is(102.5D));
        assertThat(salaries.get(Position.DEV_OPS).getAverage(), is(119.5D));
    }

    @Test
    public void test_positionWithLowestAverageSalary() {
        List<Employee> company = createCompany();

        Position position = AdvancedStreamExamples.positionWithLowestAverageSalary(company);

        assertThat(position, is(Position.QA));
    }

    @Test
    public void test_employeesPerPosition() {
        List<Employee> company = createCompany();

        Map<Position, List<Employee>> employees = AdvancedStreamExamples.employeesPerPosition(company);

        assertThat(employees.get(Position.QA).size(), is(2));
        assertThat(employees.get(Position.QA).get(0).getName(), is("Ronald Wynn"));
        assertThat(employees.get(Position.QA).get(1).getName(), is("Erich Kohn"));
    }

    @Test
    public void test_employeeNamesPerPosition() {
        List<Employee> company = createCompany();

        Map<Position, List<String>> employees = AdvancedStreamExamples.employeeNamesPerPosition(company);

        assertThat(employees.get(Position.QA).size(), is(2));
        assertThat(employees.get(Position.QA).get(0), is("Ronald Wynn"));
        assertThat(employees.get(Position.QA).get(1), is("Erich Kohn"));
    }

    @Test
    public void test_employeesCountPerPosition() {
        List<Employee> company = createCompany();

        Map<Position, Long> employees = AdvancedStreamExamples.employeesCountPerPosition(company);

        assertThat(employees.get(Position.DEV), is(6L));
        assertThat(employees.get(Position.QA), is(2L));
        assertThat(employees.get(Position.DEV_OPS), is(2L));
    }

    @Test
    public void test_employeesWithDuplicateFirstName() {
        List<Employee> company = createCompany();

        List<Employee> employees = AdvancedStreamExamples.employeesWithDuplicateFirstName(company);

        assertThat(employees.size(), is(2));
        assertThat(employees.get(0).getName(), is("John Doe"));
        assertThat(employees.get(1).getName(), is("John Smith"));
    }

    private List<Employee> createCompany() {
        Employee dev1 = new Employee("John", "Doe", Position.DEV, 110);
        dev1.setSkills("C#", "ASP.NET", "React", "AngularJS");
        Employee dev2 = new Employee("Peter", "Doe", Position.DEV, 120);
        dev2.setSkills("Java", "MongoDB", "Dropwizard", "Chef");
        Employee dev3 = new Employee("John", "Smith", Position.DEV, 115);
        dev3.setSkills("Java", "JSP", "GlassFish", "MySql");
        Employee dev4 = new Employee("Brad", "Johston", Position.DEV, 100);
        dev4.setSkills("C#", "MSSQL", "Entity Framework");
        Employee dev5 = new Employee("Philip", "Branson", Position.DEV, 140);
        dev5.setSkills("JavaScript", "React", "AngularJS", "NodeJS");
        Employee dev6 = new Employee("Nathaniel", "Barth", Position.DEV, 99);
        dev6.setSkills("Java", "Dropwizard");
        Employee qa1 = new Employee("Ronald", "Wynn", Position.QA, 100);
        qa1.setSkills("Selenium", "C#", "Java");
        Employee qa2 = new Employee("Erich", "Kohn", Position.QA, 105);
        qa2.setSkills("Selenium", "JavaScript", "Protractor");
        Employee devOps1 = new Employee("Harold", "Jess", Position.DEV_OPS, 116);
        devOps1.setSkills("CentOS", "bash", "c", "puppet", "chef", "Ansible");
        Employee devOps2 = new Employee("Karl", "Madsen", Position.DEV_OPS, 123);
        devOps2.setSkills("Ubuntu", "bash", "Python", "chef");

        return Arrays.asList(dev1, dev2, dev3, dev4, dev5, dev6, qa1, qa2, devOps1, devOps2);
    }
}

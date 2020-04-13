package Streams;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class School {
    List<Student> studentLst;
    School(int numOfStudents){
        this.studentLst = generateStudentLst(numOfStudents);
    }

    private List<Student> generateStudentLst(int numOfStudents) {
        List<Student> res = new ArrayList<>();
        while(numOfStudents-->0){
            res.add(new Student(0));
        }
        return res;
    }

    public static void main(String[] args) {
        School school = new School(20000);
        List<Integer> ageList = new ArrayList<>();
        school.studentLst.forEach((a) -> {if(a.age>=15 && a.age<=35)ageList.add(a.age);});
        List<Integer> ageListDistinc = ageList.stream().distinct().sorted((a,b)->(a-b)).collect(Collectors.toList());
        ageListDistinc.forEach(System.out::println);

    }


}

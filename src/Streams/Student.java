package Streams;

import java.util.Random;

public class Student {
    String fName;
    String lName;
    int age;

    public Student(String fName, String lName, int age) {
        this.fName = fName;
        this.lName = lName;
        this.age = age;
    }

    public Student(int i) {
        Random r = new Random();
        this.fName = generateName(r.nextInt((10-4) + 1) + 4);
        this.lName = generateName(r.nextInt((10 - 4) + 1) + 4);
        this.age = r.nextInt((100 - 15) + 1) + 15;
    }

    private String generateName(int chars) {
        StringBuilder sb = new StringBuilder("");
        Random r = new Random();
        while(chars-->0){
            char c = (char)(r.nextInt(26) + 'a');
            sb.append(c);
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return this.fName + " " + this.lName + " age: "+this.age;
    }
}

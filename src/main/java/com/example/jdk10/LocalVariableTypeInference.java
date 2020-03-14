package com.example.jdk10;

import java.util.List;

import static java.util.stream.Collectors.toUnmodifiableList;

@SuppressWarnings({"squid:S106", "squid:S1192"})
public class LocalVariableTypeInference {

    public static void main(String[] args) {
        // inferred as int
        var anInt = 42;
        System.out.println("anInt = " + anInt);

        // inferred as Integer
        var anInteger = Integer.valueOf(42);
        System.out.println("anInteger = " + anInteger);

        // inferred as float
        var aFloat = 42F;
        System.out.println("aFloat = " + aFloat);

        // inferred as double
        var aDouble = 84.0;
        System.out.println("aDouble = " + aDouble);

        // inferred as List<String>
        var names = List.of("Alice", "Bob", "Carlos", "Diane");

        // inferred as Stream<Integer>
        var lengthStream = names.stream().map(String::length);

        // inferred as List<Integer>
        var lengths = lengthStream.collect(toUnmodifiableList());
        System.out.println("lengths = " + lengths);

        // inferred as Person
        var bob = new Person("Bob", "Smith");
        System.out.println("bob = " + bob);

        // inferred as String
        var fullName = bob.getFullName();
        System.out.println("fullName = " + fullName);

        // inferred as List<Person>
        var people = List.of(
                new Person("Alice", "Jones"),
                new Person("Bob", "Smith"),
                new Person("Carlos", "Smith"),
                new Person("Diane", "Johnson")
        );
        System.out.println("people = " + people);

        // inferred as List<String>
        // In longer pipelines may want to declare explicitly if not clear
        var smithFullNames = people.stream()
                .filter(person -> "Smith".equals(person.getLastName()))
                .map(Person::getFullName)
                .sorted()
                .collect(toUnmodifiableList());
        System.out.println("smithFullNames = " + smithFullNames);
    }
}

package org.example;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        List<Person> people = readFile();
        System.out.println(people);

                // Part א
                int sum = 0;
                int count = 0;
                for (Person person : people) {
                    if ("Female".equals(person.getGender())) {
                        count++;
                        sum += person.getBirthYear();
                    }
                }
                System.out.println(count > 0 ? sum / count : "No female records found.");

                // Part ב
                List<String> names = new ArrayList<>();
                for (Person person : people) {
                    if ("Male".equals(person.getGender())) {
                        names.add(person.getFirstName());
                    }
                }
                if (!names.isEmpty()) {
                    System.out.println(Collections.max(names));
                } else {
                    System.out.println("No male records found.");
                }

                // Part ג
                int countA = 0;
                int sumA = 0;
                for (Person person : people) {
                    if (person.getFirstName().toLowerCase().contains("a")) {
                        countA++;
                        sumA += person.getBirthYear();
                    }
                }
                System.out.println(countA > 0 ? sumA / countA : "No names with 'A' found.");

                // Part ד
                int countLake = 0;
                for (Person person : people) {
                    if (person.getCity().startsWith("Lake") && person.getPrice() > 400) {
                        countLake++;
                    }
                }
                System.out.println(countLake);
            }

            public static List<Person> readFile() {
                List<Person> people = new ArrayList<>();
                try {
                    File file = new File("data.csv");
                    if (file.exists()) {
                        Scanner scanner = new Scanner(file);
                        // Optionally skip header row
                        if (scanner.hasNextLine()) {
                            scanner.nextLine(); // Skip the header
                        }
                        while (scanner.hasNextLine()) {
                            String line = scanner.nextLine();
                            String[] tokens = line.split(",");
                            if (tokens.length == 8) { // Ensure there are exactly 8 columns
                                try {
                                    Person person = new Person(
                                            Integer.parseInt(tokens[0]),
                                            tokens[1],
                                            tokens[2],
                                            tokens[3],
                                            Integer.parseInt(tokens[4]),
                                            tokens[5],
                                            tokens[6],
                                            Double.parseDouble(tokens[7])
                                    );
                                    people.add(person);
                                } catch (NumberFormatException e) {
                                    System.err.println("Skipping line due to number format error: " + line);
                                }
                            } else {
                                System.err.println("Skipping malformed line: " + line);
                            }
                        }
                        scanner.close();
                    } else {
                        System.err.println("File not found: data.csv");
                    }
                } catch (FileNotFoundException e) {
                    System.err.println("Error reading file: " + e.getMessage());
                }
                return people;
            }
        }
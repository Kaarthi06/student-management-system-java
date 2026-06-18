package com.kaarthi.student;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentService {
    public static void addStudent(ArrayList<Student> list , Scanner sc){
        int studId = ValidationService.getValidId(list,sc);
        String studName = ValidationService.getValidName(sc);
        int studAge = ValidationService.getValidAge(sc);
        String studCourse = ValidationService.getValidCourse(sc);
        String studEmail = ValidationService.getEmailValid(sc);
        String studPhone = ValidationService.getValidPhone(sc);

        list.add(new Student(studId, studName, studAge, studCourse ,studEmail,studPhone));
        System.out.println("Student Added Successfully ");
        FileService.saveToFile(list);

    }
    public static void updateStudent(ArrayList<Student> list , Scanner sc) {

        System.out.println("Enter the Student ID to update : ");
        int targetIdToUpdate = sc.nextInt();
        sc.nextLine();

        Student target = null;

        for (Student s : list) {
            if (targetIdToUpdate == s.getId()) {
                target = s;
                break;
            }
        }

        if (target == null) {
            System.out.println("Not found");
            return;
        }

        boolean updated = false;

        while (true) {
            System.out.println("\n---UPDATE MENU---");
            System.out.println("Which field you want to update....");
            System.out.println("1.Name");
            System.out.println("2.Age");
            System.out.println("3.Course");
            System.out.println("4.E-mail");
            System.out.println("5.Phone number");
            System.out.println("6.Exit");

            int updateChoice = sc.nextInt();
            sc.nextLine();

            switch (updateChoice) {
                case 1:
                    System.out.println("Current Name : " + target.getName());
                    target.setName(ValidationService.getValidName(sc));
                    updated = true;
                    break;
                case 2:
                    System.out.println("Current Age : " + target.getAge());
                    target.setAge(ValidationService.getValidAge(sc));
                    updated = true;
                    break;
                case 3:
                    System.out.println("Current Course : " + target.getCourse());
                    target.setCourse(ValidationService.getValidCourse(sc));
                    updated = true;
                    break;
                case 4:
                    System.out.println("Current Email : " + target.getEmail());
                    target.setEmail(ValidationService.getEmailValid(sc));
                    updated = true;
                    break;
                case 5:
                    System.out.println("Current Phone number : " + target.getPhone());
                    target.setPhone(ValidationService.getValidPhone(sc));
                    updated = true;
                    break;
                case 6: if (updated) {
                    System.out.println("Student Details Updated Successfully");
                    FileService.saveToFile(list);
                } else {
                    System.out.println("No changes made");
                }
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    public static void removeStudent(ArrayList<Student> list , Scanner sc){
        boolean found = false;
        System.out.println("Enter the Student ID to Remove : ");
        int targetIdToRemove = sc.nextInt();
        for (int i = 0; i < list.size(); i++) {
            if (targetIdToRemove == list.get(i).getId()) {
                list.remove(i);
                System.out.println("Student Removed Successfully");
                found = true;
                FileService.saveToFile(list);
                System.out.println();
                break;
            }
        }
        if (!found) {
            System.out.println("Not found");
        }
    }

    public static void searchStudent(ArrayList<Student> list , Scanner sc){
        System.out.println("\n--- SEARCH MENU ---");
        System.out.println("1.Search by Name");
        System.out.println("2.Search by ID");

        int choice = sc.nextInt();
        sc.nextLine();

        boolean found = false;
        switch (choice){
            case 1 :
                System.out.println("Enter the Student Name to Search : ");
                String targetNameToSearch = sc.nextLine().toLowerCase();

                for(Student s : list){
                    if(s.getName().toLowerCase().contains(targetNameToSearch)){
                        s.studentDetails();
                        found = true;

                    }
                }
                break;
            case 2 :
                System.out.println("Enter the Student ID to Search : ");
                int targetIdToSearch = sc.nextInt();
                for (Student s : list) {
                    if (s.getId() == targetIdToSearch) {
                        s.studentDetails();
                        found = true;
                        break;

                    }
                }
                break;
            default:
                System.out.println("Invalid Choice");
                return;
        }
        if(!found){
            System.out.println("Student Not Found");
        }

    }

    public static void displayStudent(ArrayList<Student> list){
        if (list.isEmpty()) {
            System.out.println("No Students Available");

        } else {
            for (Student s : list) {
                s.studentDetails();
            }
        }
    }

    public static void sortStudents(ArrayList<Student> list ,Scanner sc ) {
        if (list.isEmpty()) {
            System.out.println("No students to Sort");
            return;
        }

        System.out.println("\n--- SORT MENU ---");
        System.out.println("1.Sort by Name");
        System.out.println("2.Sort by ID");
        System.out.println("3.Sort by Age");

        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {
            case 1:
                list.sort((a, b) -> a.getName().toLowerCase().compareTo(b.getName().toLowerCase()));
                System.out.println("Sorted by Name");
                FileService.saveToFile(list);
                break;
            case 2:
                list.sort((a, b) -> Integer.compare(a.getId(), b.getId()));
                System.out.println("Sorted by ID");
                FileService.saveToFile(list);
                break;
            case 3:
                list.sort((a, b) -> Integer.compare(a.getAge(), b.getAge()));
                System.out.println("Sorted by Age");
                FileService.saveToFile(list);
                break;
            default:
                System.out.println("Invalid choice");
                return;
        }
        displayStudent(list);
    }

}

package com.kaarthi.student;

import java.util.ArrayList;
import java.util.Scanner;

public class ValidationService {

    // Method for Validating ID

    public static int getValidId(ArrayList<Student> list, Scanner sc){
        while(true){
            System.out.println("Enter ID number : ");
            int studId = sc.nextInt();
            sc.nextLine();

            boolean exists = false;

            for(Student s : list) {
                if (s.getId() == studId) {
                    exists = true;
                    break;
                }
            }
            if(!exists){
                return studId;
            }
            else{
                System.out.println("ID already exists");
            }

        }
    }

    // Method for Validating Name

    public static String getValidName(Scanner sc){
        while(true){
            System.out.println("Enter your Name : ");
            String studName = sc.nextLine();
            if(!studName.trim().isEmpty()){
                return studName;
            }
            else{
                System.out.println("Invalid Name. Try Again");
            }
        }
    }

    // Method for Validating Age

    public static int getValidAge(Scanner sc){
        while(true) {
            System.out.println("Enter your Age : ");
            int studAge = sc.nextInt();
            sc.nextLine();
            if (studAge > 0) {
                return studAge;
            } else {
                System.out.println("Invalid Age. Try Again.");
            }
        }
    }

    // Method for Validating Course

    public static String getValidCourse(Scanner sc){
        while(true){
            System.out.println("Enter your course : ");
            String studCourse = sc.nextLine();
            if(!studCourse.trim().isEmpty()){
                return studCourse;
            }
            else {
                System.out.println("Invalid Course. Try Again.");
            }
        }
    }


    // Method for Validating Email Address

    public static String getEmailValid(Scanner sc){
        while(true) {
            System.out.println("Enter your E-mail Address : ");
            String studEmail = sc.nextLine();
            if (studEmail.contains("@") && studEmail.contains(".") ) {
                return studEmail;
            } else {
                System.out.println("Invalid Email Address. Try Again.");
            }
        }
    }

    // Method for Validating Phone number

    public static String getValidPhone(Scanner sc){
        while(true){
            System.out.println("Enter your Phone number : ");
            String studPhone = sc.nextLine();

            if(studPhone.length()==10 && studPhone.matches("\\d+")){
                return studPhone;
            }
            else{
                System.out.println("Invalid Phone number. Try Again.");
            }

        }
    }

}

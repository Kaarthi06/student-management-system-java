package com.kaarthi.student;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        ArrayList<Student> list = new ArrayList<>();
        FileService.loadFromFile(list);
        String username = "";
        Scanner sc = new Scanner(System.in);

        System.out.println("1.Login");
        System.out.println("2.Register");

        int authChoice = sc.nextInt();
        sc.nextLine();

        switch (authChoice){
            case 1:
                break;

            case 2:
                AuthService.register(sc);
                break;

            default:
                System.out.println("Invalid choice");
                return;
        }

        while(true){
            System.out.println("Enter Username : ");
            username = sc.nextLine();

            if(username.trim().isEmpty()){
                System.out.println("Username cannot be empty");
                continue;
            }
            if(!AuthService.usernameExists(username)){
                System.out.println("User not found");
                continue;
            }
            if(AuthService.isUserBlocked(username)){
                System.out.println("Account Blocked");
                return;
            }
            break;
            }


        int count = 0;
        boolean isLoggedIn = false;

        while(count < 3) {
            isLoggedIn = AuthService.login(sc,username);

            if (isLoggedIn) {
                System.out.println("Login Successful!");
                break;
            }
            else {
                count++;
                System.out.println("Invalid password (" + (3 - count) + " attempts left)");
            }
        }

//FINAL CHECK
        if(!isLoggedIn){
            AuthService.blockUser(username);
            System.out.println("Account Blocked");
            return;
        }

// Choices To Perform Operations
        while (true) {
            System.out.println("1.Add Student");
            System.out.println("2.Update Student");
            System.out.println("3.Remove Student");
            System.out.println("4.Search Student");
            System.out.println("5.Display Student Details");
            System.out.println("6.Sort Students");
            System.out.println("7.Exit");

            System.out.println("Enter your choice : ");
            int choice = sc.nextInt();
// Add Student
            switch (choice) {
                case 1:
                    StudentService.addStudent(list, sc);
                    break;
                case 2:
                    StudentService.updateStudent(list, sc);
                    break;
                case 3:
                    StudentService.removeStudent(list, sc);
                    break;
                case 4:
                    StudentService.searchStudent(list, sc);
                    break;
                case 5:
                    StudentService.displayStudent(list);
                    break;
                case 6 :
                    StudentService.sortStudents(list,sc);
                    break;
                case 7:
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}

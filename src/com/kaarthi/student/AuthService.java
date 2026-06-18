package com.kaarthi.student;
import java.util.*;
import java.io.*;

public class AuthService {
    public static final String FILE_NAME = "user.txt";
    static Console console = System.console();

    //Register User
    public static void register(Scanner sc) {
            try(FileWriter fw = new FileWriter(FILE_NAME, true)){
            String username;
            while(true){
                System.out.println("Enter Username : ");
                username = sc.nextLine();
                if(username.trim().isEmpty()){
                    System.out.println("Username cannot be empty");
                    continue;
                }
                if(usernameExists(username)) {
                    System.out.println("Username already exists, Try again..");
                    continue;
                }break;
            }
            String pass = readPassword(sc);
            while (!isStrongPassword(pass)) {
                System.out.println("Weak password! Try again");
                pass = readPassword(sc);
            }
            String salt = saltGenerator();
            String hashedPassword = hashPassword(pass + salt);
            fw.write(username + "," + salt + "," + hashedPassword + ",ACTIVE\n");
            System.out.println("Registration Successful!");
        } catch (IOException e) {
            System.out.println("Error saving user ");
        }
    }

    //Login User

    public static boolean login(Scanner sc, String username) {


        String pass = readPassword(sc);

            try(BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))){
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length != 4) continue;

                if (parts[0].equalsIgnoreCase(username.trim())) {
                    String hashedInput = hashPassword(pass + parts[1]);
                    return parts[2].equals(hashedInput);
                }
            }

        } catch (IOException e) {
            System.out.println("No users found. Please register.");
        }
        return false;
    }

    public static String hashPassword(String password) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("SHA-256");

            byte[] hashedBytes = md.digest(password.getBytes());

            StringBuilder sb = new StringBuilder();

            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();

        } catch (Exception e) {
            throw new RuntimeException("Error hashing password");
        }
    }

    public static String saltGenerator() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 8);
    }

    public static String readPassword(Scanner sc) {
        String password;
        System.out.println("(Input will be hidden if supported)");
        if (console != null) {
            char[] readPass = console.readPassword();
            password = new String(readPass);
        } else {
            System.out.println("Enter Password : ");
            password = sc.nextLine();
        }
        return password;
    }

    public static boolean usernameExists(String username) {
            try(BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))){
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");

                if (parts.length < 4) continue;

                if (parts[0].equalsIgnoreCase(username.trim())) {
                    return true;
                }
            }
        } catch (IOException e) {
            return false;
        }

        return false;
    }

    public static boolean isStrongPassword(String password){
        boolean hasUpper = false;
        boolean hasLower = false;
        boolean hasDigit = false;
        boolean hasSpecialCharacter = false;

        if(password.length() < 8) return false;

        for(int i = 0 ; i < password.length() ; i++){
            char ch = password.charAt(i);

            if(Character.isUpperCase(ch)){
                hasUpper = true;
            } else if (Character.isLowerCase(ch)) {
                hasLower = true;
            }
                else if (Character.isDigit(ch)){
                    hasDigit = true;
                }
                else if(!Character.isLetter(ch) && !Character.isDigit(ch)){
                    hasSpecialCharacter = true;
                }
        }
        return hasSpecialCharacter && hasLower && hasDigit && hasUpper ;
    }

    public static void blockUser(String username){
        ArrayList<String> lines = new ArrayList<>();
            try(BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))){
            String line;

            while((line = br.readLine())!=null) {
                String[] parts = line.split(",");

                if (parts.length != 4) {
                    lines.add(line);
                    continue;
                }

                if(parts[0].equalsIgnoreCase(username.trim())){
                    parts[3]="BLOCKED";
                    line = String.join(",",parts);
                }
                lines.add(line);
            }
            try(FileWriter fw = new FileWriter(FILE_NAME)) {
                for (String l : lines) {
                    fw.write(l + "\n");
                }
            }
            System.out.println("User has been blocked!");
        } catch (IOException e) {
            System.out.println("Error blocking user");
        }
    }
    public static boolean isUserBlocked(String username){
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;

            while((line = br.readLine()) != null){
                String[] parts = line.split(",");
                if(parts.length != 4) continue;

                if(parts[0].equalsIgnoreCase(username.trim())){
                    return parts[3].equals("BLOCKED");
                }
            }

        } catch (IOException e){
            return false;
        }

        return false;
    }

}

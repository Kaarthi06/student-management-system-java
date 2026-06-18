package com.kaarthi.student;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileService {

// Method : Save To File

    public static void saveToFile(ArrayList<Student> list) {
        try {
            FileWriter fw = new FileWriter("students.txt");
            for (Student s : list) {
                fw.write(s.getId() + "," + s.getName() + "," + s.getAge()+ "," + s.getCourse() + "," + s.getEmail()+ "," + s.getPhone()+  "\n");
            }

            fw.close();
            System.out.println("Data saved to file");
        } catch (IOException e) {
            System.out.println("Error saving file");
        }

    }

// Method : Load From File

    public static void loadFromFile(ArrayList<Student> list) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("students.txt"));
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");

                if(parts.length == 6) {
                    int id = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    int age = Integer.parseInt(parts[2]);
                    String course = parts[3];
                    String email = parts[4];
                    String phone =parts[5];


                    list.add(new Student(id , name, age ,course , email , phone ));
                }
            }

            br.close();
            System.out.println("Data loaded from file");

        } catch (IOException e) {
            System.out.println("File not found, starting fresh");
        }
    }

}

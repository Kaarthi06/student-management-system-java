package com.kaarthi.student;

public class Student {
    private int id ;
    private String name ;
    private int age ;
    private String course ;
    private String email ;
    private String phone ;
// Constructor
    public Student(int id,String name,int age,String course,String email,String phone){
        this.id = id;
        this.name = name;
        this.age = age ;
        this.course = course ;
        this.email = email;
        this.phone = phone;
    }
    public void studentDetails(){
        System.out.println("ID : "+this.id);
        System.out.println("Name : "+this.name);
        System.out.println("Age : "+this.age);
        System.out.println("Course : "+this.course);
        System.out.println("E-mail Address : "+this.email);
        System.out.println("Phone number : "+this.phone);
        System.out.println();
    }

//Get Methods

    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public int getAge(){
        return age;
    }
    public String getCourse(){
        return course;
    }
    public String getEmail(){
        return email;
    }
    public String getPhone(){
        return phone;
    }

//Set Methods

    public void setId(int id){
        this.id = id;
    }
    public void setAge(int age){
        this.age = age;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setCourse(String course ){
        this.course = course;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void setPhone(String phone){
        this.phone = phone ;
    }
}

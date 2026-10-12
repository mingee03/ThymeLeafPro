package org.example.assign06.domain;

public class Student {
    private Long id;
    private String name;
    private Integer age;
    private String email;
    private String major;
    private String studentId;

    public Student() {
    }

    public Student(Long id, String name, Integer age, String email, String major, String studentId) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.major = major;
        this.studentId = studentId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
}

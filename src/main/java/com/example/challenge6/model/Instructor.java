package com.example.challenge6.model;

import javax.persistence.*;

@Entity
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    private String courseId;

    private String employeeId;

    private String pictureUrl;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id")
    private Department department;

    public Instructor() { }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
//    public String getManufacturer() {
//        return manufacturer;
//    }
//
//    public void setManufacturer(String manufacturer) {
//        this.manufacturer = manufacturer;
//    }
//
//    public String getModel() {
//        return model;
//    }
//
//    public void setModel(String model) {
//        this.model = model;
//    }
//
//    public String getYear() {
//        return year;
//    }
//
//    public void setYear(String year) {
//        this.year = year;
//    }
//
//    public double getPrice() {
//        return price;
//    }
//
//    public void setPrice(double price) {
//        this.price = price;
//    }
//
//    public Department getType() {
//        return department;
//    }
//
//    public void setType(Department department) {
//        this.department = department;
//    }


//    public String moreThan20(String text) {
//        String result="";
//        text=description;
//        int stopAt20 = 0;
//        if (text.length()>20) {
//            for (char letter : description.toCharArray()) {
//                result+=letter;
//                stopAt20++;
//                if (stopAt20>20) {
//                    result+="...";
//                    break;
//                }
//            }
//            return result;
//        }
//        else {
//            return description;
//        }
//    }
}

package com.example.challenge6.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

//    private String categorie;
    private String department;

    @OneToMany(mappedBy = "department",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    public Set<Instructor> instructor;

//    @OneToMany(mappedBy = "type",
//            cascade = CascadeType.ALL,
//            fetch = FetchType.EAGER)
//    public Set<Instructor> instructors;



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    //    public String getCategories() {
//        return categorie;
//    }
//
//    public void setCategories(String categories) {
//        this.categorie = categories;
//    }
//
//    public String getCategorie() {
//        return categorie;
//    }
//
//    public void setCategorie(String categorie) {
//        this.categorie = categorie;
//    }

    public Set<Instructor> getInstructor() {
        return instructor;
    }

    public void setInstructor(Set<Instructor> instructor) {
        this.instructor = instructor;
    }
}

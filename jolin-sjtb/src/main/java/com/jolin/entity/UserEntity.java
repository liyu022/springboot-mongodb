package com.jolin.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;

@Document(collection="User")
public class UserEntity implements  Serializable {
    @Id
    private Long id;
    private String name;
    private Integer age;
    private String gender;
    private String address;
    private LocalDateTime addtime;


    private Book book;

    public UserEntity(String name, Integer age, String gender, String address,LocalDateTime addtime,Book book) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.addtime = addtime;
        this.book = book;
    }
    public UserEntity(Long id, String name, Integer age, String gender, String address,LocalDateTime addtime,Book book) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.addtime = addtime;
        this.book = book;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDateTime  getAddtime() {
        return addtime;
    }

    public void setAddtime(LocalDateTime  addtime) {
        this.addtime = addtime;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", address='" + address + '\'' +
                ", addtime='" + addtime + '\'' +
                '}';
    }
}

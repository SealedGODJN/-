package com.java.huaweiJavaTraining;

import java.util.HashSet;
import java.util.Objects;

public class Test_Hash {
    public static void main(String[] args) {
        HashSet<Email> emailSet = new HashSet<>();
        Email email = new Email("fly.com", "Jim");
        emailSet.add(email);

        // 修改email，造成内存泄漏
        email.setAddress("run.com");
        System.out.println(emailSet.contains(email));
        emailSet.remove(email); // 此处也无法删除email
        for(Email e:emailSet) {
            System.out.println(e.getAddress());
            System.out.println(e.getName());
        }
    }
}

class Email{
    private String address;
    private String name;

    public Email(String address, String name) {
        this.address = address;
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email email = (Email) o;
        return Objects.equals(address, email.address) &&
                Objects.equals(name, email.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, name);
    }
}

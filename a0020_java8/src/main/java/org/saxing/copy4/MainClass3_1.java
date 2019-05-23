package org.saxing.copy4;


import java.io.Serializable;

/**
 * 所以使用该工具类的对象只要实现Serializable接口就可实现对象的克隆，无须继承Cloneable接口实现clone()方法。
 */
class Experience {

    private String educationBackground;
    private String skills;

    public void setExperience(String educationBackground, String skills) {
        // TODO Auto-generated constructor stub
        this.educationBackground = educationBackground;
        this.skills = skills;
    }
    public String toString() {
        return educationBackground + skills;
    }
}

class Email implements Serializable {

    private String title;
    private String content;

    public Email(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void setEmail(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "Email{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}

/* 建立类，实现Clone方法  */
class Person implements Serializable {
    private String name;  //姓名
    private Email email;

    public Person(String name, Email email) {
        this.name = name;
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(String title, String content) {
        email.setEmail(title, content);
    }

    public void displayResume() {
        System.out.println(email);
    }

}

public class MainClass3_1 {
    public static void main(String[] args) {
        //写封邮件
        Email email = new Email("请参加会议","请与今天12:30到二会议室参加会议...");

        Person person1 =  new Person("张三",email);

        Person person2 =  CloneUtils.clone(person1);
        person2.setName("李四");
        Person person3 =  CloneUtils.clone(person1);
        person3.setName("王五");
        person1.getEmail().setContent("请与今天12:00到二会议室参加会议...");

        System.out.println(person1.getName() + "的邮件内容是：" + person1.getEmail().getContent());
        System.out.println(person2.getName() + "的邮件内容是：" + person2.getEmail().getContent());
        System.out.println(person3.getName() + "的邮件内容是：" + person3.getEmail().getContent());

    }
}


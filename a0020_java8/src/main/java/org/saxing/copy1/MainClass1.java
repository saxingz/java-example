package org.saxing.copy1;

/**
 * 1、直接赋值
 * 好，下面我们先看第一种方式，直接赋值。在Java中，A a1 = a2，我们需要理解的是这实际上复制的是引用，
 * 也就是说a1和a2指向的是同一个对象。因此，当a1变化的时候，a2里面的成员变量也会跟着变化。各位，请看下面的代码吧！
 */
class Resume  {

    private String name;  //姓名
    private String sex;   //性别
    private int age;      //年龄
    private String experience; //工作经历

    public Resume (String name, String sex, int age) {
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    public int getAge() {
        return age;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }
    public String getExperience() {
        return experience;
    }

    public void displayResume() {
        System.out.println("姓名："+name+" 性别："+sex+" 年龄:"+age);
        System.out.println("工作经历："+experience);
    }
}


public class MainClass1 {
    public static void main(String[] args) {
        org.saxing.copy2.Resume zhangsan = new org.saxing.copy2.Resume("zhangsan","男",24);
        zhangsan.setExperience("2009-2013就读于家里蹲大学，精通JAVA,C,C++,C#等代码复制");
        zhangsan.displayResume();
        org.saxing.copy2.Resume zhangsan1 = zhangsan;
        zhangsan1.setExperience("2009-2013就读于家里蹲大学，精通JAVA,C,C++,C#等");
        zhangsan.displayResume();
        zhangsan1.displayResume();
    }
}
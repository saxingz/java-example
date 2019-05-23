package org.saxing.copy3;


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

/* 建立类，实现Clone方法  */
class Resume  implements Cloneable{
    private String name;  //姓名
    private String sex;   //性别
    private int age;      //年龄
    private Experience experience; //工作经历

    public Resume(String name, String sex, int age) {
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.experience = new Experience();
    }

    public void setAge(int age) {
        this.age = age;
    }
    public int getAge() {
        return age;
    }

    public Experience getExperience() {
        return experience;
    }

    public void setExperience(String educationBackground, String skills) {
        experience.setExperience(educationBackground, skills);
    }

    public void displayResume() {
        System.out.println("姓名："+name+" 性别："+sex+" 年龄:"+age);
        System.out.println("工作经历："+experience.toString());
    }

    public Object clone() {
        try {
            return (Resume)super.clone();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

public class MainClass2_1 {
    public static void main(String[] args) {
        Resume zhangsan = new Resume("zhangsan","男",24);
        zhangsan.setExperience("2009-2013就读于家里蹲大学","精通JAVA,C,C++,C#等代码拷贝和粘贴");
        zhangsan.displayResume();

        Resume zhangsan2 = (Resume)zhangsan.clone();
        zhangsan2.setExperience("2009-2013就读于家里蹲大学","精通JAVA,C,C++,C#等");
        zhangsan2.displayResume();
        zhangsan.displayResume();
        zhangsan2.displayResume();
    }
}


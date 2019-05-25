package org.saxing.a.copy2;


/**
 * 上面直接赋值的结果，有时候可能并不是我们所想要的。就像我们投简历的时候，可能会根据应聘公司的类型做出相应的调整，
 * 如果是投技术类的工作可能会偏技术一点；如果是投国企啊什么之类的，社会经历学生工作什么的可能也是很重要的一部分。
 * 所以我们不需要当我们修改一份简历的时候，所有的简历都变调。不然到时候投技术类的公司又得改回来。
 * 说了这么多，我们也就是希望，把a1赋值给a2之后，a1和a2能保持独立，不要互相影响。
 *
 * 实现上面想法之一的方法就是Object的Clone()函数了。在这里，我们需要了解clone()主要做了些什么，创建一个新对象，
 * 然后将当前对象的非静态字段复制到该新对象，如果字段是值类型的，那么对该字段执行复制；如果该字段是引用类型的话，
 * 则复制引用但不复制引用的对象。因此，原始对象及其副本引用同一个对象。
 * 好，我们先看这一段话的前一部分，如果字段是值类型，则直接复制。如下面程序所示
 */
/* 建立类，实现Clone方法  */
class Resume  implements Cloneable{
    private String name;  //姓名
    private String sex;   //性别
    private int age;      //年龄
    private String experience; //工作经历

    public Resume(String name, String sex, int age) {
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

    public Object clone() {
        try {
            return (Resume)super.clone();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}


public class MainClass2 {

    public static void main(String[] args) {
        Resume zhangsan = new Resume("zhangsan","男",24);
        zhangsan.setExperience("2009-2013就读于家里蹲大学，精通JAVA,C,C++,C#等代码拷贝和粘贴");
        zhangsan.displayResume();
        Resume zhangsan1 = (Resume)zhangsan.clone();
        zhangsan1.setAge(23);
        zhangsan1.displayResume();
        Resume zhangsan2 = (Resume)zhangsan.clone();
        zhangsan2.setExperience("2009-2013就读于家里蹲大学，精通JAVA,C,C++,C#等代码");
        zhangsan2.displayResume();
        zhangsan.displayResume();
    }

}

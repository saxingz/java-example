package org.saxing.validate.model;



import org.saxing.validate.annotation.ListNotHasNull;
import org.saxing.validate.model.view.PersonAddView;
import org.saxing.validate.model.view.PersonModifyView;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class Person {
    private long id;
    /**
     * 添加groups 属性，说明只在特定的验证规则里面起作用，不加则表示在使用Deafault规则时起作用
     */
    @NotNull(groups = {PersonAddView.class, PersonModifyView.class}, message = "添加、修改用户时名字不能为空")
    @ListNotHasNull.List({
            @ListNotHasNull(groups = {PersonAddView.class}, message = "添加上Name不能为空"),
            @ListNotHasNull(groups = {PersonModifyView.class}, message = "修改时Name不能为空")})
    private String name;

    @NotNull(groups = {PersonAddView.class}, message = "添加用户时地址不能为空")
    private String address;

    @Min(value = 18, groups = {PersonAddView.class}, message = "姓名不能低于18岁")
    @Max(value = 30, groups = {PersonModifyView.class}, message = "姓名不能超过30岁")
    private int age;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "PersonService{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", age=" + age +
                '}';
    }
}


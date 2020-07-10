package org.saxing.java8;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * list to map test
 *
 * @author saxing 2020/7/10 23:13
 */
public class ListToMapTest {

    public static void main(String[] args) {
        List<User> userList = new ArrayList<>();
        User user1 = new User();
        user1.setId(1L);
        user1.setAge("12");

        User user2 = new User();
        user2.setId(2L);
        user2.setAge("13");

        User user3 = new User();
        user3.setId(2L);
        user3.setAge("17");

        userList.add(user1);
        userList.add(user2);
        userList.add(user3);

        Map<Long, User> maps = new HashMap<>();
        for (User user : userList) {
            maps.put(user.getId(), user);
        }

        System.out.println(maps);

        Map<Long, List<User>> collect = userList.stream().collect(Collectors.groupingBy(User::getId));
        System.out.println(collect);

    }

    public static class User {
        private Long id;
        private String age;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", age='" + age + '\'' +
                    '}';
        }
    }
}

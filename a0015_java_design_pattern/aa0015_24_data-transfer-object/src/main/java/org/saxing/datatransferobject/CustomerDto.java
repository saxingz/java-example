package org.saxing.datatransferobject;

/**
 * CustomerDto
 *
 * @author saxing 2018/12/7 22:46
 */
public class CustomerDto {

    private final String id;
    private final String firstName;
    private final String lastName;

    public CustomerDto(String id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}

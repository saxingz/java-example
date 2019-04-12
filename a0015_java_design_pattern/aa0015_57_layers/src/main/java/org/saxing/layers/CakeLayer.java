package org.saxing.layers;

import javax.persistence.*;

/**
 * CakeLayer entity
 *
 * @author saxing 2019/3/6 23:34
 */
@Entity
public class CakeLayer {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private int calories;

    @ManyToOne(cascade = CascadeType.ALL)
    private Cake cake;

    public CakeLayer() {}

    public CakeLayer(String name, int calories) {
        this.setName(name);
        this.setCalories(calories);
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

    public final void setName(String name) {
        this.name = name;
    }

    public int getCalories() {
        return calories;
    }

    public final void setCalories(int calories) {
        this.calories = calories;
    }

    @Override
    public String toString() {
        return String.format("id=%s name=%s calories=%d", id, name, calories);
    }

    public Cake getCake() {
        return cake;
    }

    public void setCake(Cake cake) {
        this.cake = cake;
    }

}

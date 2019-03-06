package org.saxing.layers;

import javax.persistence.*;

/**
 * cake topping
 */
@Entity
public class CakeTopping {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private int calories;

    @OneToOne(cascade = CascadeType.ALL)
    private Cake cake;

}

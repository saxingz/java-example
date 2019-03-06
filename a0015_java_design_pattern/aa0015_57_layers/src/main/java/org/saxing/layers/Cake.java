package org.saxing.layers;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * cake
 *
 * @author saxing 2019/3/6 23:30
 */
public class Cake {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(cascade = CascadeType.REMOVE)
    private CakeTopping topping;

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private Set<CakeLayer> layers;

    public Cake() {
        setLayers(new HashSet<>());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CakeTopping getTopping() {
        return topping;
    }

    public void setTopping(CakeTopping topping) {
        this.topping = topping;
    }

    public Set<CakeLayer> getLayers() {
        return layers;
    }

    public final void setLayers(Set<CakeLayer> layers) {
        this.layers = layers;
    }

    public void addLayer(CakeLayer layer) {
        this.layers.add(layer);
    }

    @Override
    public String toString() {
        return String.format("id=%s topping=%s layers=%s", id, topping, layers.toString());
    }

}

package org.saxing.layers;

import java.util.List;

/**
 * cake baking service
 *
 * @author saxing 2019/4/10 23:48
 */
public interface CakeBakingService {


    /**
     * Bakes new cake according to parameters
     */
    void bakeNewCake(CakeInfo cakeInfo) throws CakeBakingException;

    /**
     * Get all cakes
     */
    List<CakeInfo> getAllCakes();

    /**
     * Store new cake topping
     */
    void saveNewTopping(CakeToppingInfo toppingInfo);

    /**
     * Get available cake toppings
     */
    List<CakeToppingInfo> getAvailableToppings();

    /**
     * Add new cake layer
     */
    void saveNewLayer(CakeLayerInfo layerInfo);

    /**
     * Get available cake layers
     */
    List<CakeLayerInfo> getAvailableLayers();

}

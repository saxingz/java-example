package org.saxing.layers;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * cake layer dao
 *
 * @author saxing 2019/4/12 21:15
 */
@Repository
public interface CakeLayerDao extends CrudRepository<CakeLayer, Long> {

}

package org.saxing.layers;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * cake topping
 *
 * @author saxing 2019/3/3 22:20
 */
@Repository
public interface CakeToppingDao extends CrudRepository<CakeTopping, Long> {



}

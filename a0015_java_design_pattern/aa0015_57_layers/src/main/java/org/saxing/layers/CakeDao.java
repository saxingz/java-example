package org.saxing.layers;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * cake dao
 *
 * @author saxing 2019/4/12 21:06
 */
@Repository
public interface CakeDao extends CrudRepository<Cake, Long> {



}

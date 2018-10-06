package org.saxing.abstract_document.domain;


import org.saxing.abstract_document.Document;

import java.util.Optional;

/**
 * has price
 *
 * @author saxing  2018/10/6 15:04
 */
public interface HasPrice extends Document {

    String PROPERTY = "price";

    default Optional<Number> getPrice(){
        return Optional.ofNullable((Number) get(PROPERTY));
    }

}

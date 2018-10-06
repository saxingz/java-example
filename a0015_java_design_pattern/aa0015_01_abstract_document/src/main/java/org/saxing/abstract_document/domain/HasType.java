package org.saxing.abstract_document.domain;

import org.saxing.abstract_document.Document;

import java.util.Optional;

/**
 *
 * has type
 *
 * @author saxing  2018/10/6 15:02
 */
public interface HasType extends Document {

    String PROPERTY = "type";

    default Optional<String> getType(){
        return Optional.ofNullable((String)get(PROPERTY));
    }

}

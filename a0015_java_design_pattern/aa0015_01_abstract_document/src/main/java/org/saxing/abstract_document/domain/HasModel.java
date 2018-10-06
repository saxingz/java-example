package org.saxing.abstract_document.domain;

import org.saxing.abstract_document.Document;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * has model
 * @author saxing  2018/10/6 14:59
 */
public interface HasModel extends Document {

    String PROPERTY = "model";

    default Optional<String> getModel(){
        return Optional.ofNullable((String)get(PROPERTY));
    }


}

package org.saxing.abstract_document.domain;

import org.saxing.abstract_document.Document;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * hasParts
 *
 * @author saxing  2018/10/6 15:01
 */
public interface HasParts extends Document {

    String PROPERTY = "parts";

    default Stream<Part> getParts(){
        return childRen(PROPERTY, Part::new);
    }


}

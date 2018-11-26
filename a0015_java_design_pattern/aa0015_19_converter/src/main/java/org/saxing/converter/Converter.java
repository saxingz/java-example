package org.saxing.converter;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Generic converter, thanks to Java8 features not only provides a way of generic bidirectional
 *  * conversion between coresponding types, but also a common way of converting a collection of objects
 *  * of the same type, reducing boilerplate code to the absolute minimum.
 *
 * @param <T> DTO representation's type
 * @param <U> Domain representation's type
 *
 * @author saxing  2018/11/26 23:32
 */
public class Converter<T, U> {

    private final Function<T, U> fromDto;
    private final Function<U, T> fromEntity;

    public Converter(Function<T, U> fromDto, Function<U, T> fromEntity) {
        this.fromDto = fromDto;
        this.fromEntity = fromEntity;
    }

    public final U convertFromDto(final T dto){
        return fromDto.apply(dto);
    }

    public final T convertFromEntity(final U entity){
        return fromEntity.apply(entity);
    }

    public final List<U> createFromDtos(final Collection<T> dtos){
        return dtos.stream().map(this::convertFromDto).collect(Collectors.toList());
    }

    public final List<T> createFromEntites(final Collection<U> entities){
        return entities.stream().map(this::convertFromEntity).collect(Collectors.toList());
    }
}

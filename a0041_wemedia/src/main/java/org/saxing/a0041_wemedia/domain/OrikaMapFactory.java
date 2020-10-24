package org.saxing.a0041_wemedia.domain;

import ma.glasnost.orika.Converter;
import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.metadata.Type;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 定制bean mapper
 *
 * @author saxing
 */
public class OrikaMapFactory {

    // 原始类型
    public static final String BYTE_BOOLEAN_CONVERTER = "byte_boolean_converter";

    public static final String BOOLEAN_BYTE_CONVERTER = "boolean_byte_converter";

    private static Map<String, Converter> converterMap = new HashMap<>();

    static {
        // 基本类型转换
        converterMap.put(BYTE_BOOLEAN_CONVERTER, new CustomConverter<Byte, Boolean>() {
            @Override
            public Boolean convert(Byte aByte, Type<? extends Boolean> type, MappingContext mappingContext) {
                return aByte != null && aByte.intValue() != 0;
            }
        });

        converterMap.put(BOOLEAN_BYTE_CONVERTER, new CustomConverter<Boolean, Byte>() {
            @Override
            public Byte convert(Boolean aBoolean, Type<? extends Byte> type, MappingContext mappingContext) {
                return aBoolean ? (byte) 1 : (byte) 0;
            }
        });
    }

    public static List<Converter> getPrimitiveConverters() {

        List<Converter> converters = new ArrayList<>();

        converters.add(converterMap.get(BYTE_BOOLEAN_CONVERTER));
        converters.add(converterMap.get(BOOLEAN_BYTE_CONVERTER));

        return converters;
    }

    public static List<Converter> getConverters(String... converterNames) {
        List<Converter> converters = new ArrayList<>();

        for (String converterName : converterNames) {
            converters.add(converterMap.get(converterName));
        }

        return converters;
    }

}

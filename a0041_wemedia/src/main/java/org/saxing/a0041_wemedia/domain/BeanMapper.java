package org.saxing.a0041_wemedia.domain;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.stereotype.Component;

/**
 * bean copy 配置
 *
 * @author saxing
 */
@Component
public class BeanMapper extends ConfigurableMapper {

    @Override
    protected void configureFactoryBuilder(DefaultMapperFactory.Builder factoryBuilder) {
        factoryBuilder.mapNulls(false);
    }
    @Override
    protected void configure(MapperFactory factory) {
        // 注册全局的转换器
        OrikaMapFactory.getPrimitiveConverters().forEach(factory.getConverterFactory()::registerConverter);
    }

}

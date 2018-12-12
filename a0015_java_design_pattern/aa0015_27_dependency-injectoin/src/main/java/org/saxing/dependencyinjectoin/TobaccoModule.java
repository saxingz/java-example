package org.saxing.dependencyinjectoin;

import com.google.inject.AbstractModule;

/**
 * Guice module for binding certain concrete {@link Tobacco} implementation.
 *
 * @author saxing 2018/12/12 23:04
 */
public class TobaccoModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Tobacco.class).to(RivendellTobacco.class);
    }
}

package org.saxing.dependencyinjectoin;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication

/**
 * main
 *
 * @author saxing 2018/12/12 23:11
 */
public class Aa001527DependencyInjectoinApplication {

    public static void main(String[] args) {
//        SpringApplication.run(Aa001527DependencyInjectoinApplication.class, args);

        SimpleWizard simpleWizard = new SimpleWizard();
        simpleWizard.smoke();

        AdvancedWizard advancedWizard = new AdvancedWizard(new SecondBreakfastTobacco());
        advancedWizard.smoke();

        AdvancedSorceress advancedSorceress = new AdvancedSorceress();
        advancedSorceress.setTobacco(new SecondBreakfastTobacco());
        advancedSorceress.smoke();

        Injector injector = Guice.createInjector(new TobaccoModule());
        GuiceWizard guiceWizard = injector.getInstance(GuiceWizard.class);
        guiceWizard.smoke();


    }
}

package ru.money.tracker.architecture;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

@AnalyzeClasses(packages = "ru.money.tracker")
public class LayeredArchitectureTest {

    private static final String CONTROLLER_PACKAGE = "ru.money.tracker.controller";
    private static final String SERVICE_PACKAGE = "ru.money.tracker.service";
    private static final String REPOSITORY_PACKAGE = "ru.money.tracker.repository";

    @ArchTest
    static final ArchRule layer_dependencies_are_respected = layeredArchitecture()
            .consideringAllDependencies()

            .layer("Controller").definedBy(CONTROLLER_PACKAGE + "..")
            .layer("Service").definedBy(SERVICE_PACKAGE + "..")
            .layer("Repository").definedBy(REPOSITORY_PACKAGE + "..")

            .whereLayer("Controller").mayNotBeAccessedByAnyLayer()
            .whereLayer("Service").mayOnlyBeAccessedByLayers("Controller")
            .whereLayer("Repository").mayOnlyBeAccessedByLayers("Service");

    @ArchTest
    static ArchRule controllers_should_be_suffixed_and_annotated =
            classes()
                    .that().resideInAPackage(CONTROLLER_PACKAGE + "..")
                    .and().areAnnotatedWith(RestController.class)
                    .should().haveSimpleNameEndingWith("Controller");

    @ArchTest
    static ArchRule services_should_be_suffixed_and_annotated =
            classes()
                    .that().resideInAPackage(SERVICE_PACKAGE + "..")
                    .and().areAnnotatedWith(Service.class)
                    .should().haveSimpleNameEndingWith("Service");

    @ArchTest
    static ArchRule repositories_should_be_suffixed_and_annotated =
            classes()
                    .that().resideInAPackage(REPOSITORY_PACKAGE + "..")
                    .and().areAnnotatedWith(Repository.class)
                    .should().haveSimpleNameEndingWith("Repository");

}

package ru.money.tracker.architecture;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

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
}

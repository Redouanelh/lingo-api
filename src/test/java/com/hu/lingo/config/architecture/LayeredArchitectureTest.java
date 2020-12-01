package com.hu.lingo.config.architecture;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

@AnalyzeClasses(packages = "com.hu.lingo.config")
public class LayeredArchitectureTest {
    // TODO: uncomment dit nadat de er in iedere layer een klasse zit.

    /*
    @ArchTest
    static final ArchRule dependencyRule = layeredArchitecture()
            .layer("presentation").definedBy("presentation")
            .layer("application").definedBy("application")
            .layer("domain").definedBy("domain")
            .layer("data").definedBy("data")

            .whereLayer("application").mayOnlyBeAccessedByLayers("presentation")
            .whereLayer("data").mayOnlyBeAccessedByLayers("application")
            .whereLayer("presentation").mayNotBeAccessedByAnyLayer();
     */
}

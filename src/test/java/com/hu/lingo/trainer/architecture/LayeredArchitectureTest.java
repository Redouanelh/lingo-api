package com.hu.lingo.trainer.architecture;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

@AnalyzeClasses(packages = "com.hu.lingo.trainer")
class LayeredArchitectureTest {
    @ArchTest
    static final ArchRule dependencyRule = layeredArchitecture()
            .layer("presentation").definedBy("presentation")
            .layer("application").definedBy("application")
            .layer("domain").definedBy("domain")
            .layer("data").definedBy("data")

            .whereLayer("application").mayOnlyBeAccessedByLayers("presentation")
            .whereLayer("data").mayOnlyBeAccessedByLayers("application")
            .whereLayer("presentation").mayNotBeAccessedByAnyLayer();
}

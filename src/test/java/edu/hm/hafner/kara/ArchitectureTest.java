package edu.hm.hafner.kara;

import com.tngtech.archunit.core.importer.ImportOption.DoNotIncludeTests;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import edu.hm.hafner.util.ArchitectureRules;

import static com.tngtech.archunit.core.domain.JavaAccess.Predicates.*;
import static com.tngtech.archunit.core.domain.properties.HasName.Predicates.*;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.*;

/**
 * Checks the architecture of this module.
 *
 * @author Ullrich Hafner
 */
@SuppressWarnings("hideutilityclassconstructor")
@AnalyzeClasses(packages = "edu.hm.hafner.kara", importOptions = DoNotIncludeTests.class)
class ArchitectureTest {
    /** Never create exception without any context. */
    @ArchTest
    static final ArchRule NO_FIELDS = classes().should().haveOnlyFinalFields()
            .because("Use parameters to exchange data between methods.");

    /** Prevents collections classes from being used. */
    @ArchTest
    static final ArchRule NO_FORBIDDEN_PACKAGE_USED =
            noClasses().should().dependOnClassesThat().resideInAnyPackage("java.util")
                    .because("Collections and similar advanced utilities will be part of software development 2.");

    /** Strings are part of Software Engineering 2. */
    @ArchTest
    static final ArchRule NO_STRINGS_OR_WRAPPER_CLASSES_USED =
            noClasses().should().callMethodWhere(
                            targetOwner(name("java.lang.String"))
                                    .or(targetOwner(name("java.lang.Integer")))
                                    .or(targetOwner(name("java.lang.Long"))))
                    .because("Strings and Wrapper Classes are part of software development 2.");

    @ArchTest
    static final ArchRule NO_FORBIDDEN_PACKAGE_ACCESSED = ArchitectureRules.NO_FORBIDDEN_PACKAGE_ACCESSED;

    @ArchTest
    static final ArchRule NO_FORBIDDEN_CLASSES_CALLED = ArchitectureRules.NO_FORBIDDEN_CLASSES_CALLED;

    @ArchTest
    static final ArchRule NO_FORBIDDEN_ANNOTATION_USED = ArchitectureRules.NO_FORBIDDEN_ANNOTATION_USED;

    @ArchTest
    static final ArchRule NO_EXCEPTIONS_WITH_NO_ARG_CONSTRUCTOR = ArchitectureRules.NO_EXCEPTIONS_WITH_NO_ARG_CONSTRUCTOR;
}

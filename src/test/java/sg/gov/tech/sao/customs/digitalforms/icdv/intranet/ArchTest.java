package sg.gov.tech.sao.customs.digitalforms.icdv.intranet;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {

        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("sg.gov.tech.sao.customs.digitalforms.icdv.intranet");

        noClasses()
            .that()
                .resideInAnyPackage("sg.gov.tech.sao.customs.digitalforms.icdv.intranet.service..")
            .or()
                .resideInAnyPackage("sg.gov.tech.sao.customs.digitalforms.icdv.intranet.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..sg.gov.tech.sao.customs.digitalforms.icdv.intranet.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}

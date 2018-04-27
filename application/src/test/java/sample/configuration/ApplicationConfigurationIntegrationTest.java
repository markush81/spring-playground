package sample.configuration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationConfigurationIntegrationTest {

    @Autowired
    private SubModuleConfiguration subModuleConfiguration;

    @Test
    public void testSubModuleConfigurationOverriden() {
        assertThat(subModuleConfiguration.getOverridenproperty(), equalTo("fromApplicationYaml"));
    }

    @Test
    public void testSubModuleConfiguration() {
        assertThat(subModuleConfiguration.getProperty(), equalTo("fromSubmoduleApplicationYaml"));
    }

}

package sample.configuration;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SubModuleConfiguration.class, SubModuleConfigurationTest.Configuration.class})
public class SubModuleConfigurationTest {

    @Autowired
    private SubModuleConfiguration subModuleConfiguration;

    @Test
    public void testSubModuleConfigurationOverriden() {
        assertThat(subModuleConfiguration.getOverridenproperty(), equalTo("fromSubmoduleApplicationYamlOverridden"));
    }

    @Test
    public void testSubModuleConfiguration() {
        assertThat(subModuleConfiguration.getProperty(), equalTo("fromSubmoduleApplicationYaml"));
    }

    @org.springframework.context.annotation.Configuration
    @EnableConfigurationProperties
    static class Configuration {

    }
}

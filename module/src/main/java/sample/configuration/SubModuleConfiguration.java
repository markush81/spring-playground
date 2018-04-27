package sample.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationProperties(prefix = "submodule")
@PropertySource("classpath:/submodule.yml")
public class SubModuleConfiguration {

    private String property;
    private String overridenproperty;

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getOverridenproperty() {
        return overridenproperty;
    }

    public void setOverridenproperty(String overridenproperty) {
        this.overridenproperty = overridenproperty;
    }

    @Override
    public String toString() {
        return "SubModuleConfiguration{" +
                "property='" + property + '\'' +
                ", overridenproperty='" + overridenproperty + '\'' +
                '}';
    }
}

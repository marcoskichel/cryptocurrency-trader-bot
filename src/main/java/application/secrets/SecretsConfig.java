package application.secrets;

import com.cars.framework.secrets.DockerSecretLoadException;
import com.cars.framework.secrets.DockerSecrets;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.Map;

@Configuration
public class SecretsConfig {

    @Bean(name = "secrets")
    public Map<String, String> secrets() {
        try {
            return DockerSecrets.loadFromFile("secrets");
        } catch (DockerSecretLoadException e) {
            System.out.println("Secrets Load failed : " + e.getMessage());
        }
        return Collections.emptyMap();
    }

}

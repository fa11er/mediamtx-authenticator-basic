package fa11er.mediamtx.authenticator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication(scanBasePackages = "fa11er.mediamtx.authenticator")
@EnableCaching
public class MediamtxAuthenticatorBasicApplication {
    public static final Logger LOGGER =
            LoggerFactory.getLogger(MediamtxAuthenticatorBasicApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(MediamtxAuthenticatorBasicApplication.class, args);
    }

}

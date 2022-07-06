package simplesoul.autumnboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationContextInitializedEvent;
import org.springframework.context.event.EventListener;
import simplesoul.autumnboot.rest.common.logging.LoggerInstrument;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author AC
 */
@SpringBootApplication
public class AutumnBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutumnBootApplication.class, args);
    }
}

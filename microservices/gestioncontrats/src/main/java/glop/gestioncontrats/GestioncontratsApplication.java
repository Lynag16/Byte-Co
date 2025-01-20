package glop.gestioncontrats;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class GestioncontratsApplication {
	public GestioncontratsApplication() {
	}

	public static void main(String[] args) {
		SpringApplication.run(GestioncontratsApplication.class, args);
	}

}

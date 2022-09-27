package pal.argha.smsbulk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;
import pal.argha.smsbulk.appuser.AppUser;
import pal.argha.smsbulk.appuser.AppUserRole;
import pal.argha.smsbulk.appuser.AppUserService;
import pal.argha.smsbulk.registration.RegistrationController;
import pal.argha.smsbulk.registration.RegistrationRequest;
import pal.argha.smsbulk.registration.RegistrationService;
import pal.argha.smsbulk.security.config.WebSecurityConfig;

@SpringBootApplication
@ComponentScan(basePackageClasses = {
		AppUser.class,
		AppUserService.class,
		AppUserRole.class})
@ComponentScan(basePackageClasses = {
		RegistrationController.class,
		RegistrationRequest.class,
		RegistrationService.class})
@ConfigurationPropertiesScan(basePackageClasses = WebSecurityConfig.class)

public class SmsDashboardApplication {

	public static void main(String[] args) {

		SpringApplication.run(SmsDashboardApplication.class, args);
	}

}

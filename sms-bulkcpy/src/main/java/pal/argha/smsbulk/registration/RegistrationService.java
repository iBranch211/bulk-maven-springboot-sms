package pal.argha.smsbulk.registration;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pal.argha.smsbulk.appuser.AppUser;
import pal.argha.smsbulk.appuser.AppUserRole;
import pal.argha.smsbulk.appuser.AppUserService;

@Service
@AllArgsConstructor
public class RegistrationService {
    private final AppUserService appUserService;
    private final EmailValidator emailValidator;
    public String register(RegistrationRequest request){
        boolean isValidEmail=emailValidator.test(request.getEmail());
        if(!isValidEmail){
            throw new IllegalStateException("Email not valid");
        }
        return appUserService.signUpUser(
                new AppUser(
                        request.getFirstName(),
                        request.getLastName(),
                        request.getEmail(),
                        request.getPassword(),
                        AppUserRole.USER,
                        false,
                        true
                )
        );
    }
}

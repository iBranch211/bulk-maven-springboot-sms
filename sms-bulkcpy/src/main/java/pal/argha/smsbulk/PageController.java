package pal.argha.smsbulk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pal.argha.smsbulk.registration.RegistrationRequest;
import pal.argha.smsbulk.registration.RegistrationService;

@RestController
public class PageController {

    @Value("${phoneNumber}")
    private String phoneNumber;

    @GetMapping("/")
    public ModelAndView showDashboard(){
        ModelAndView dashboard = new ModelAndView("dashboard");
        dashboard.addObject("phoneNumber", phoneNumber);
        return dashboard;
    }


        private RegistrationService registrationService;

        @PostMapping
        @RequestMapping("/api/v1/registration")
        public String register(@RequestBody RegistrationRequest request){
            return registrationService.register(request);
        }
        @Autowired
        private StudentService studentService;
        @PostMapping("/store")
        public String storeStudent(@RequestBody Student student){
            return studentService.register(student);
        }
        @PostMapping("/delete")
        public String deleteStudent(@RequestBody Student s){
            String number=s.getPhone();
            System.out.println(number);
            return studentService.deleteStudent(number);
        }




}

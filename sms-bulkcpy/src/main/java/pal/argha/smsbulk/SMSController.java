package pal.argha.smsbulk;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class SMSController {

    public static class MessageDetails {
        public List<String> numbers;
        public String message;
        public String gender;
        public String stream;
    }

    @Value("${phoneNumber}")
    private String myTwilioPhoneNumber;

    @Autowired
    public SMSController(
        @Value("${twilioAccountSid}") String twilioAccountSid,
        @Value("${twilioAuthToken}") String twilioAuthToken) {
        Twilio.init(twilioAccountSid, twilioAuthToken);
    }
    public People getPeople(@ModelAttribute("gender") String gender,@ModelAttribute("Stream") String stream){
        People people=new People();
        people.gender=gender;
        people.stream=stream;
        return people;
    }
    @Autowired
    private StudentRepository studentRepository;

    @PostMapping("/send-messages")
    @ResponseStatus(HttpStatus.ACCEPTED)

    public void sendMessages(@RequestBody MessageDetails messageDetails) {
        System.out.println(messageDetails.gender+" "+messageDetails.stream);
        if(messageDetails.stream.equals("tech")){
            messageDetails.stream="BTech";
        }
        List<Student> students=studentRepository.findAll();
        System.out.println(students);
        for(Student s: students){
            messageDetails.numbers.add(s.getPhone());
        }
        if(!(messageDetails.gender.equals("ALL")))
        {
            for(Student s: students){
                if(!(s.getGender().equals(messageDetails.gender))){
                    messageDetails.numbers.remove(s.getPhone());
                }
            }
        }
        if(!(messageDetails.stream.equals("ALL")))
        {
            for(Student s: students){
                if(!(s.getStream().equals(messageDetails.stream))){
                    messageDetails.numbers.remove(s.getPhone());
                }
            }
        }
        System.out.println(students);

        System.out.println(messageDetails.numbers);
            messageDetails.numbers.stream().forEach(
                    number -> {
                        try {
                            Message message = Message.creator(
                                    new PhoneNumber(number),
                                    new PhoneNumber(myTwilioPhoneNumber),
                                    messageDetails.message).create();
                            System.out.println("Sent message w/ sid: " + message.getSid());
                        }
                        catch(Exception e)
                        {
                            System.out.println("Could not be sent to number: "+number);
                        }

                    });


    }
}

package pal.argha.smsbulk;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    public String register(Student student){
        studentRepository.save(student);
        return "Stored!";
    }
    public List<Student> fetchStudentList(){
        return studentRepository.findAll();
    }
    public String deleteStudent(String number){
        System.out.println(number);
        List<Student> students=fetchStudentList();
        for(Student s: students){

            if(s.getPhone().equals(number)){
                System.out.println("##"+s.getPhone());
                studentRepository.deleteById(s.getId());
                return "Deleted";
            }
        }

        return "Did not find";
    }
}

package actions.controller;

import actions.entity.Student;
import actions.repository.StudentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class StudentController {

    private final StudentRepository studentRepository;

    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @GetMapping("/students/{id}")
    public Student getStudent(@PathVariable Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    @PostMapping("/students")
    public void createStudent(@RequestBody Student student) {
        studentRepository.save(student);
    }

    @Transactional
    @PutMapping("/students/{id}")
    public void updateStudent(@PathVariable Long id, @RequestBody Student student) {
        Student oldStudent = studentRepository.findById(id).orElse(null);
        if (oldStudent != null) {
            oldStudent.setName(student.getName());
        }
    }

    @DeleteMapping("/students/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentRepository.deleteById(id);
    }
}

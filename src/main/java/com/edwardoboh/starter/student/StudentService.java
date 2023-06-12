package com.edwardoboh.starter.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        List<Student> students = this.studentRepository.findAll();
        return students;
    }

    public void addNewStudent(Student student) {
        Optional<Student> existStudent = this.studentRepository.findStudentByEmail(student.getEmail());
        if(existStudent.isPresent()){
            throw new IllegalStateException("Credentials taken");
        }
        this.studentRepository.save(student);
    }

    public void deleteStudent(Long studentId){
        Optional<Student> existStudent = this.studentRepository.findById(studentId);
        if(!existStudent.isPresent()){
            throw new IllegalStateException("Student with id: " + studentId + "does not exist");
        }
        this.studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, Student student) {
        Optional<Student> existStudent = this.studentRepository.findById(studentId);
        if(!existStudent.isPresent()){
            throw new IllegalStateException("Student does not exist");
        }

        Student dbStudent = existStudent.get();
        dbStudent.setName(student.getName());
        dbStudent.setEmail(student.getEmail());
    }
}

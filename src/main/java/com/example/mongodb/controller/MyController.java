package com.example.mongodb.controller;

import com.example.mongodb.model.Student;
import com.example.mongodb.repo.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class MyController {

    @Autowired
    private StudentRepository studentRepository;

    @PostMapping("/")
    public ResponseEntity<?> addStudent(@RequestBody Student student){
        Student s = studentRepository.save(student);
        return ResponseEntity.ok(student);
    }

    @GetMapping("/")
    public ResponseEntity<?> getStudent(){
        return ResponseEntity.ok(studentRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentId(@PathVariable("id") int id){
        return ResponseEntity.ok(studentRepository.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateStudent(@RequestBody Student student , @PathVariable("id") int id){
        Student s = studentRepository.findById(id).orElse(null);
        s.setName(student.getName());
        s.setCity(student.getCity());
        s.setCollege(student.getCollege());

        studentRepository.save(s);
        return ResponseEntity.ok("object updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudentId(@PathVariable("id") int id){
        studentRepository.deleteById(id);
        return ResponseEntity.ok("object successfully deleted");
    }
}

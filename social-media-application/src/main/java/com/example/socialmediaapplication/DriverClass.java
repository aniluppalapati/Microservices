package com.example.socialmediaapplication;

import com.example.socialmediaapplication.model.Course;
import com.example.socialmediaapplication.repository.JDBCRepository;
import com.example.socialmediaapplication.repository.JPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DriverClass implements CommandLineRunner {

    @Autowired
    private JDBCRepository jdbcRepository;

    @Autowired
    private JPARepository jpaRepository;

    @Override
    public void run(String... args) throws Exception {
        jdbcRepository.insert();
        for(Course course:jdbcRepository.select())
                  System.out.println(course);

        System.out.println(jdbcRepository.findById(1));
    }
}

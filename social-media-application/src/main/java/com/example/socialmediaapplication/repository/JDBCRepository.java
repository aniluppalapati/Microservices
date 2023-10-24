package com.example.socialmediaapplication.repository;

import com.example.socialmediaapplication.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JDBCRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    final private String INSERT_QUERY=
                    """
                         INSERT INTO course (id, name, author)
                         VALUES (1, 'Introduction to Programming', 'John Doe'),
                                (2, 'Database Design', 'Jane Smith'),
                                (3, 'Web Development Basics', 'Bob Johnson');
                    
                    """;

    final private String SELECT_QUERY=
            """
            
            Select * from course
            """;
    final private String SELECT_QUERY_Id=
            """
            
            Select * from course where id=?
            """;

    final private String


    public void insert(){
        System.out.println("Insert Query is Executing");
        int status=jdbcTemplate.update(INSERT_QUERY);
        System.out.println("Status of Insert Query"+status);
    }

    public List<Course> select(){
        return jdbcTemplate.query(SELECT_QUERY,new BeanPropertyRowMapper<>(Course.class));
        //return jdbcTemplate.queryForObject(SELECT_QUERY,new BeanPropertyRowMapper<>(Course.class),id);
    }

    public Course findById(int id){

        return jdbcTemplate.queryForObject(SELECT_QUERY_Id,new BeanPropertyRowMapper<>(Course.class),id);
    }
    public int updateBatch(){
        jdbcTemplate.batchUpdate()

        return 0;
    }
}

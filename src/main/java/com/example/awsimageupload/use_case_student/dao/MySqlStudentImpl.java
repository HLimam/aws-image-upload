package com.example.awsimageupload.use_case_student.dao;

import com.example.awsimageupload.use_case_student.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

@Repository("mysql")
public class MySqlStudentImpl implements StudentDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static class StudentRowMapper implements RowMapper<Student>{

        @Override
        public Student mapRow(ResultSet resultSet, int i) throws SQLException {

            Student student = new Student();
            student.setId(resultSet.getInt("id"));
            student.setName(resultSet.getString("name"));
            student.setCourse(resultSet.getString("course"));
            return student;
        }
    }


    @Override
    public Collection<Student> getAllStudents() {
        final String sql = "SELECT id, name, course FROM students";
        List<Student> students = jdbcTemplate.query(sql, new StudentRowMapper());
        return students;
    }

    @Override
    public Student getStudentById(int id) {
        final String sql = "SELECT id, name, course FROM students where id = ?";
        Student student=jdbcTemplate.queryForObject(sql, new StudentRowMapper(),id);
        return student;
    }

    @Override
    public void removeStudentById(int id) {
        jdbcTemplate.update("DELETE FROM students WHERE id=?",id);

    }

    @Override
    public void updateStudent(Student student) {

    }

    @Override
    public void insertStudentToDb(Student student) {

    }
}

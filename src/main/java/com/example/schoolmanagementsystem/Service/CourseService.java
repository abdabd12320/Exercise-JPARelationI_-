package com.example.schoolmanagementsystem.Service;

import com.example.schoolmanagementsystem.Api.ApiException;
import com.example.schoolmanagementsystem.Model.Course;
import com.example.schoolmanagementsystem.Model.Teacher;
import com.example.schoolmanagementsystem.Repository.CourseRepository;
import com.example.schoolmanagementsystem.Repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;

    public List<Course> getCourses()
    {
        return courseRepository.findAll();
    }
    public void addCourse(Course course)
    {
        courseRepository.save(course);
    }
    public void updateCourse(Integer id,Course course)
    {
        Course c = courseRepository.findCourseById(id);
        if(c == null)
        {
            throw new ApiException("It is null");
        }
        c.setName(course.getName());
        courseRepository.save(c);
    }
    public void deleteCourse(Integer id)
    {
        if(courseRepository.findCourseById(id) == null)
        {
            throw new ApiException("It is null");
        }
        courseRepository.deleteById(id);
    }
    public void assignTeacherToCourse(Integer teacherID,Integer courseID)
    {
        Teacher t = teacherRepository.findTeacherById(teacherID);
        Course c = courseRepository.findCourseById(courseID);
        if(c == null || t == null)
        {
            throw new ApiException("Both of them is null");
        }
        c.setTeacher(t);
        courseRepository.save(c);
    }
    public String getTeacherByCourseID(Integer id)
    {
        if(courseRepository.findCourseById(id) == null)
        {
            throw new ApiException("It is null");
        }
        return courseRepository.findCourseById(id).getTeacher().getName();
    }
}

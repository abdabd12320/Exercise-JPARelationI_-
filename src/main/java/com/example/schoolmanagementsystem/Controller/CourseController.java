package com.example.schoolmanagementsystem.Controller;

import com.example.schoolmanagementsystem.Api.ApiResponse;
import com.example.schoolmanagementsystem.Model.Course;
import com.example.schoolmanagementsystem.Service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/course")
public class CourseController {

    private final CourseService courseService;

    @GetMapping("/get")
    public ResponseEntity getCourses()
    {
        return ResponseEntity.status(200).body(courseService.getCourses());
    }
    @PostMapping("/add")
    public ResponseEntity addCourse(@Valid@RequestBody Course course)
    {
        courseService.addCourse(course);
        return ResponseEntity.status(200).body(new ApiResponse("Course added"));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateCourse(@PathVariable Integer id,@Valid@RequestBody Course course)
    {
        courseService.updateCourse(id, course);
        return ResponseEntity.status(200).body(new ApiResponse("Course updated"));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCourse(@PathVariable Integer id)
    {
        courseService.deleteCourse(id);
        return ResponseEntity.status(200).body(new ApiResponse("Course deleted"));
    }
    @PutMapping("/{teacherID}/assign/{courseID}")
    public ResponseEntity assignTeacherToCourse(@PathVariable Integer teacherID,@PathVariable Integer courseID)
    {
        courseService.assignTeacherToCourse(teacherID, courseID);
        return ResponseEntity.status(200).body(new ApiResponse("Done assign"));
    }
    @GetMapping("/get-teacher-by-courseID/{id}")
    public ResponseEntity getTeacherByCourseID(@PathVariable Integer id)
    {
        return ResponseEntity.status(200).body(new ApiResponse(courseService.getTeacherByCourseID(id)));
    }
}

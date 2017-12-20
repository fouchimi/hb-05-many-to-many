package com.example.hibernate.demo;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.example.hibernate.demo.entity.Course;
import com.example.hibernate.demo.entity.Instructor;
import com.example.hibernate.demo.entity.InstructorDetail;
import com.example.hibernate.demo.entity.Review;
import com.example.hibernate.demo.entity.Student;

public class CreateCourseAndStudentsDemo {

	public static void main(String[] args) {
        
        // create session factory
        SessionFactory factory = new Configuration()
        		.configure("hibernate.cfg.xml")
        		.addAnnotatedClass(Instructor.class)
        		.addAnnotatedClass(InstructorDetail.class)
        		.addAnnotatedClass(Course.class)
        		.addAnnotatedClass(Review.class)
        		.addAnnotatedClass(Student.class)
                .buildSessionFactory();
 
        // create a session
        Session session = factory.getCurrentSession();
 
        try {
      
            // start a transaction
            session.beginTransaction();
            
            // Create course
            Course tempCourse = new Course("Algorithms");
            
            // Save the course and leverage the cascade ALL
            System.out.println("Saving the course");
            System.out.println("tempCourse" + tempCourse);
            session.save(tempCourse);
            
            // Create students
            Student tempStudent1 = new Student("Fouchimi", "Ousmane", "ousmanefouchimi@gmail.com");
            Student tempStudent2 = new Student("Tchapmi", "Lyne", "tchapmil@gmail.com");
            Student tempStudent3 = new Student("Barack", "Hussein", "hbarack@gmail.com");
            
            // Adding students to course
            tempCourse.addStudent(tempStudent1);
            tempCourse.addStudent(tempStudent2);
            tempCourse.addStudent(tempStudent3);
            
            // Saving the students
            System.out.println("Saving students");
            session.save(tempStudent1);
            session.save(tempStudent2);
            session.save(tempStudent3);
            
            // commit transaction
            session.getTransaction().commit();
            
            System.out.println("Done!");
        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
        	session.close();
            factory.close();
        }
    }
}

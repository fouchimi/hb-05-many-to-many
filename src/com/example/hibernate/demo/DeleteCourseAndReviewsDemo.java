package com.example.hibernate.demo;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.example.hibernate.demo.entity.Course;
import com.example.hibernate.demo.entity.Instructor;
import com.example.hibernate.demo.entity.InstructorDetail;
import com.example.hibernate.demo.entity.Review;

public class DeleteCourseAndReviewsDemo {

	public static void main(String[] args) {
        
        // create session factory
        SessionFactory factory = new Configuration()
        		.configure("hibernate.cfg.xml")
        		.addAnnotatedClass(Instructor.class)
        		.addAnnotatedClass(InstructorDetail.class)
        		.addAnnotatedClass(Course.class)
        		.addAnnotatedClass(Review.class)
                .buildSessionFactory();
 
        // create a session
        Session session = factory.getCurrentSession();
 
        try {
      
            // start a transaction
            session.beginTransaction();
           
            // Get course
            int theCourseId = 10;
            
            Course tempCourse = session.get(Course.class, theCourseId);
            
            // Print the course
            System.out.println("tempCourse: " + tempCourse);
            
            // print the course reviews
            System.out.println("Course Reviews: " + tempCourse.getReviews());
            
            // delete the course
            session.delete(tempCourse);
            
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

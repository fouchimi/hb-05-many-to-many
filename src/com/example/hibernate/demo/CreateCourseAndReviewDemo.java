package com.example.hibernate.demo;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.example.hibernate.demo.entity.Course;
import com.example.hibernate.demo.entity.Instructor;
import com.example.hibernate.demo.entity.InstructorDetail;
import com.example.hibernate.demo.entity.Review;

public class CreateCourseAndReviewDemo {

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
            
            // Create course
            Course tempCourse = new Course("Design Software");
            
            // Add reviews to the course
            tempCourse.addReview(new Review("Great Course, instructive material"));
            tempCourse.addReview(new Review("Great content, i think the tone was a little bit lower"));
            tempCourse.addReview(new Review("Definitely needs to point out pre-requisite for non experience software developers"));
            
            // Save the course and leverage the cascade ALL
            System.out.println("Saving the course");
            System.out.println(tempCourse);
            System.out.println(tempCourse.getReviews());
            session.save(tempCourse);
            
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

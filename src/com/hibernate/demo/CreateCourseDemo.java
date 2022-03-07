package com.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo.entity.Course;
import com.hibernate.demo.entity.Instructor;
import com.hibernate.demo.entity.InstructorDetail;

public class CreateCourseDemo {

	public static void main(String[] args) {

		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(Course.class).buildSessionFactory();

		Session session = factory.getCurrentSession();

		try {

//			Instructor i = new Instructor("Prashant", "Ranjan", "PNK");

			Course c = new Course("java");

			session.beginTransaction();

//			c.setInstructor(i);
//
//			session.save(i);
//
//			session.save(c);

			var gi = session.get(Instructor.class, 2);

			c.setInstructor(gi);

			session.save(c);

			System.out.println(gi);
			System.out.println(gi.getCourses());

			session.getTransaction().commit();

		} finally {
			session.close();
			factory.close();
		}
	}

}

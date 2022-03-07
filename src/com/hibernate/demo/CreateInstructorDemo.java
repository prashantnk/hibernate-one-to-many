package com.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo.entity.Course;
import com.hibernate.demo.entity.Instructor;
import com.hibernate.demo.entity.InstructorDetail;

public class CreateInstructorDemo {

	public static void main(String[] args) {

		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(Course.class).buildSessionFactory();

		Session session = factory.getCurrentSession();

		try {

			Instructor i = new Instructor("pp", "rr", "ppnn@gmail.com");
			InstructorDetail id = new InstructorDetail("pnn", "ww mm");
// class having foreign key does some changes or save the cascading data
//			i.setInstructorDetail(id);
			id.setInstructor(i);

			System.out.println("i : " + id);

			session.beginTransaction();

			session.save(id);
//			this won't save instructor , because id doesn't have FK
			session.getTransaction().commit();

			System.out.println("i : " + id);

		} finally {
			factory.close();
		}
	}

}

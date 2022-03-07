package com.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.hibernate.demo.entity.Course;
import com.hibernate.demo.entity.Instructor;
import com.hibernate.demo.entity.InstructorDetail;

public class EagerLazyFetching {

	public static void main(String[] args) {

		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(Course.class).buildSessionFactory();

		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();
// by session.get 

//			var gi = session.get(Instructor.class, 2);

//			System.out.println(gi);

// by session.createQuery

			final String hql = "select i from Instructor i join fetch i.courses where i.id = :theInstructorId";
			final String updateInsdetail = "update Instructor i set i.instructorDetail=:theInstructorDetail where i.id=2";

			Query<Instructor> q = session.createQuery(hql, Instructor.class);

			q.setParameter("theInstructorId", 2);

			var i = q.getSingleResult();

			session.createQuery(updateInsdetail)
					.setParameter("theInstructorDetail", session.get(InstructorDetail.class, 2)).executeUpdate();

			System.out.println(i);
			System.out.println(i.getCourses());

			session.getTransaction().commit();

		} finally {
			session.close();
			factory.close();
		}
	}

}

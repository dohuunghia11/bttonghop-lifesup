package com.lifesup.dao;

import java.util.List;

import com.lifesup.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import org.springframework.stereotype.Repository;

import org.springframework.web.bind.annotation.ResponseBody;


@Repository(value = "userDAO")
public class UserDAO {
	SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

	@ResponseBody
	public List<User> showAll() {
		Session session = sessionFactory.openSession();
		List<User> list = session.createQuery("FROM User").list();
		for (User user : list) {
			System.out.println(user);
		}
		return list;

	}

	public User findById(int id) {
		Session session = sessionFactory.openSession();
		User user = session.load(User.class, id);
		System.out.println(user);
		return user;
	}

	public void update(User user) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			session.update(user);
			session.getTransaction().commit();
			System.out.println("update success!");
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public void delete(int id) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			User user = session.load(User.class, id);
			session.delete(user);
			session.getTransaction().commit();
			System.out.println("detete success!");
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

	}

	public void save(User user) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			session.save(user);
			session.getTransaction().commit();
			System.out.println("insert success!");
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	 public static void main(String[] args) {
		 UserDAO s = new UserDAO();
	 
	  s.showAll(); 
	  }
	 }

package br.com.senaigo.view;

import br.com.senaigo.entities.Category;
import br.com.senaigo.persistence.jdbc.PCategoryJdbc;
import br.com.senaigo.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.EntityManager;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bruno on 22/04/16.
 */
public class HqlTest {

    public final static Logger log = Logger.getLogger(HqlTest.class);

    public static void main(String[] args){
        readAll();

    }

    public static void create(){
        //Prep Work
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        File file = new File(System.getProperty("user.dir")+"/resources/image-default.png");
//        System.out.println(file.getAbsoluteFile());

        List<Category> list = new ArrayList<Category>();
        for(int i=0;i<100;i++){
            Category category = new Category();
            category.setCategoryName("Category "+i);
            category.setDescription("Description "+i);
            category.setPicture(file);
            list.add(category);
        }

        for(Category category: list){
            session.save(category);
        }

        tx.commit();
        sessionFactory.close();
    }

    public static void readAll(){
        //Prep work
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        //Get All Employees
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("from Category");
        List<Category> list = query.list();
        for(Category category : list){
            log.info("List of Categories::Id->"+category.getCategoryId()+",Name -> "+category.getCategoryName());
        }

        tx.commit();
        sessionFactory.close();
        log.info("DONE");
    }
}

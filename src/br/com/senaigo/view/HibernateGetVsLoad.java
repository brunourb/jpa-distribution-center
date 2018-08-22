package br.com.senaigo.view;

import br.com.senaigo.entities.Category;
import br.com.senaigo.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bruno on 22/04/16.
 */
public class HibernateGetVsLoad {

    public static void main(String[] args) {

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

//        //Get Example
//        Category emp = (Category) session.get(Category.class, new Long(2));
//        System.out.println("Category get called");
//        System.out.println("Category ID= "+emp.getCategoryId());
//        System.out.println("Category Get Details:: "+emp+"\n");
//
//        //load Example
//        Category emp1 = (Category) session.load(Category.class, new Long(1));
//        System.out.println("Category load called");
//        System.out.println("Category ID= "+emp1.getCategoryId());
//        System.out.println("Category load Details:: "+emp1+"\n");

        //Close resources
        tx.commit();
        sessionFactory.close();
    }
}

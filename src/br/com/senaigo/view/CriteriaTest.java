package br.com.senaigo.view;

import br.com.senaigo.entities.Category;
import br.com.senaigo.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * @link {http://www.tutorialspoint.com/hibernate/hibernate_criteria_queries.htm}
 * @link http://docs.oracle.com/javaee/6/tutorial/doc/gjivm.html
 * Created by bruno on 22/04/16.
 */
public class CriteriaTest {

    public final static Logger log = Logger.getLogger(Criteria.class);

    static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    static Session session = sessionFactory.openSession();

    public static void main(String[] args) {
        readAllHibernate();
//        readHibernateRestriction01();
//        LogicalExpression();
    }

    public static void readAllHibernate() {

        org.hibernate.Criteria cr = session.createCriteria(Category.class);
        List<Category> categories = cr.list();

        for (Category category : categories) {
            log.info(String.format("%s \t %s", category.getCategoryId(), category.getCategoryName()));

        }

        sessionFactory.close();
    }

    public static void readHibernateRestriction01() {
        Criteria cr = session.createCriteria(Category.class);
        cr.add(Restrictions.eq("categoryId", 40));

//        // To get records having salary more than 2000
//        cr.add(Restrictions.gt("salary", 2000));
//
//        // To get records having salary less than 2000
//        cr.add(Restrictions.lt("salary", 2000));
//
//        // To get records having fistName starting with zara
//        cr.add(Restrictions.like("firstName", "zara%"));
//
//        // Case sensitive form of the above restriction.
//        cr.add(Restrictions.ilike("firstName", "zara%"));
//
//        // To get records having salary in between 1000 and 2000
//        cr.add(Restrictions.between("salary", 1000, 2000));
//
//        // To check if the given property is null
//        cr.add(Restrictions.isNull("salary"));
//
//        // To check if the given property is not null
//        cr.add(Restrictions.isNotNull("salary"));
//
//        // To check if the given property is empty
//        cr.add(Restrictions.isEmpty("salary"));
//
//        // To check if the given property is not empty
//        cr.add(Restrictions.isNotEmpty("salary"));


        List<Category> results = cr.list();

        for (Category category : results) {
            log.info(String.format("%s \t %s", category.getCategoryId(), category.getCategoryName()));

        }

        sessionFactory.close();
    }

    public static void LogicalExpression(){

        Criteria cr = session.createCriteria(Category.class);

        Criterion categoryId = Restrictions.gt("categoryId", 100);

        // To get records matching with OR condistions
        Criterion name = Restrictions.ilike("categoryName","%3%");
        Criterion description = Restrictions.ilike("description","%4%");

        LogicalExpression orExp = Restrictions.or(name, description);
        cr.add( orExp );


        // To get records matching with AND condistions
//        LogicalExpression andExp = Restrictions.and(salary, name);
//        cr.add( andExp );

        List<Category> results = cr.list();

        for (Category category : results) {
            log.info(String.format("%s \t %s", category.getCategoryId(), category.getCategoryName()));

        }

        sessionFactory.close();
    }
}

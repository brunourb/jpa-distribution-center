package br.com.senaigo.view;

import br.com.senaigo.entities.Category;
import br.com.senaigo.persistence.jdbc.PCategoryJdbc;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by bruno on 22/04/16.
 */
public class JpqlTest {

    public final static Logger log = Logger.getLogger(PCategoryJdbc.class);

    public static void main(String[] args){
        // aplica taxas em cada venda
        for (Category category : read()) {
            System.out.println((category.toString()));
        }

    }

    public static List<Category> read(){

        EntityManager manager = JpaUtil.getEntityManager();
        String categoryName = "category";

        List<Category> categories = manager.createQuery("select c from Category c where upper(c.categoryName) like upper(:categoryName)", Category.class)
                .setParameter("categoryName", "'%"+categoryName+"%'")
                .getResultList();


        manager.close();

        return categories;

    }

}

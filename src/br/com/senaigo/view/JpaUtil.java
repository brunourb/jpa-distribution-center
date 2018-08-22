package br.com.senaigo.view;

import br.com.senaigo.entities.Category;
import br.com.senaigo.entities.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bruno on 11/03/16.
 */
public class JpaUtil {

    private static EntityManagerFactory factory;
    static {
        factory = Persistence.createEntityManagerFactory("orm-jpa");
    }
    public static EntityManager getEntityManager() {
        return factory.createEntityManager();
    }
    public static void close() {
        factory.close();
    }




    public static void main(String[] args){
//        Persistence.createEntityManagerFactory("orm-jpa");
        create();
//        update();

//        find();
    }

    public static void find(){
        EntityManager manager = JpaUtil.getEntityManager();
        EntityTransaction tx = manager.getTransaction();
        tx.begin();


        Category category = manager.find(Category.class,103);


        tx.commit();
        manager.close();
        JpaUtil.close();
        category.getProducts().get(1).getProductName();

    }

    public static void create(){
        EntityManager manager = JpaUtil.getEntityManager();
        EntityTransaction tx = manager.getTransaction();
        tx.begin();

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
            manager.persist(category);
        }

//        manager.persist(veiculo);
        tx.commit();
        manager.close();
        JpaUtil.close();
    }

    public static void update(){

        EntityManager manager = JpaUtil.getEntityManager();
        EntityTransaction tx = manager.getTransaction();

       /* manager.createQuery(); //JPQL
        manager.createNativeQuery(); //SQL ANSI
        manager.createNamedQuery(name);
*/

        tx.begin();

        Category category = new Category();
        category.setCategoryName("azzzzzzz");
        category.setCategoryName("Category");
        category.setDescription("Description");
        category.setPicture(null);

        manager.persist(category);

        List<Product> products = new ArrayList<Product>();

        for (int i = 0; i < 100; i++) {
            Product product = new Product();
            product.setProductName("Product "+i);
            product.setSupplierId(i);
            product.setCategory(category);
            products.add(product);
        }

        category.getProducts().addAll(products);

        manager.merge(category);
        tx.commit();
        manager.close();
        JpaUtil.close();

        category.getProducts().get(3).getProductName();

    }









}

package br.com.senaigo.view;

import br.com.senaigo.controller.CategoryController;
import br.com.senaigo.entities.Category;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.*;

/**
 * Created by bruno on 26/02/16.
 */
public class CategoryView {

    public static CategoryController controller = new CategoryController();

    public static Scanner sc = new Scanner(System.in);

    public static void main(final String[] args){
//        create();
//        read();
        update();
    }

    /**
     * @see http://www.mysqltutorial.org/mysql-jdbc-blob
     */
    private static void create(){
        // this will list the current system properties
//        Properties p = System.getProperties();
//        p.list(System.out);

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
        controller.create(list);

        Category t = new Category();
        t.setCategoryId(1);
        controller.read(t);
    }

    private static void read(){
        Category category = new Category();
        category.setCategoryId(1);
        category = controller.read(category);

        System.out.println(category.toString());
    }

    private static void update(){
        Category category = new Category();
        category.setCategoryId(50);
        category = controller.read(category);
        category.setCategoryName(category.getCategoryName()+" update sucess.");
        category.setDescription(category.getDescription()+" update sucess.");

        controller.update(category);

        System.out.println(category.toString());
    }

}

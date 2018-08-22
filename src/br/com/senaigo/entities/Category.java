package br.com.senaigo.entities;

import org.apache.commons.io.IOUtils;

import javax.persistence.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bruno on 26/02/16.
 */
@Entity
//@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue
    protected Integer categoryId;

//    @Column(name = "xxxxxxx", nullable = false, length = 50)
    protected String categoryName;
    protected String description;

    /**
     * @link{http://docs.oracle.com/javaee/5/api/javax/persistence/OneToMany.html}
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category",cascade = CascadeType.ALL)
    protected List<Product> products;


    /**
     * http://stackoverflow.com/questions/4317035/how-to-convert-inputstream-to-virtual-file
     */
    public static transient final String PREFIX = "stream2file";
    public static transient final String SUFFIX = ".tmp";

    /**
     * TINYBLOB: 255 bytes
     * BLOB: 65,535 bytes (64 KB)
     * MEDIUMBLOB: 16,777,215 bytes (16 MB)
     * LONGBLOB: 4 GB
     */
    protected File picture;

    public Category() {
        products = new ArrayList<Product>();
    }

    public Category(Integer categoryId, String categoryName, String description, File picture) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.description = description;
        this.picture = picture;
    }

    public static File stream2file (InputStream in) throws IOException {

        final File tempFile = File.createTempFile(PREFIX, SUFFIX);
        tempFile.deleteOnExit();
        try (FileOutputStream out = new FileOutputStream(tempFile)) {
            IOUtils.copy(in, out);
        }
        return tempFile;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public File getPicture() {
        return picture;
    }

    public void setPicture(File picture) {
        this.picture = picture;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", description='" + description + '\'' +
                ", picture=" + picture +
                '}';
    }
}

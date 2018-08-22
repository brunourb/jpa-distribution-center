package br.com.senaigo.entities;

import javax.persistence.*;

/**
 * Created by bruno on 18/03/16.
 */
@Entity
public class Product {

    @Id
    @GeneratedValue
    protected Integer productId;
    protected String productName;
    protected Integer supplierId;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "CAT_ID",nullable = false)
    protected Category category;

    public Product(){

    }

    public Product(Integer productId, String productName, Integer supplierId, Category category) {
        this.productId = productId;
        this.productName = productName;
        this.supplierId = supplierId;
        this.category = category;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;

        Product product = (Product) o;

        return getProductId() != null ? getProductId().equals(product.getProductId()) : product.getProductId() == null;

    }

    @Override
    public int hashCode() {
        return getProductId() != null ? getProductId().hashCode() : 0;
    }
}

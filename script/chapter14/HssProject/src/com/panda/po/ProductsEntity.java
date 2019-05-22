package com.panda.po;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "PRODUCTS", schema = "STUDY", catalog = "")
public class ProductsEntity {
    private String productId;
    private String productName;
    private String productType;
    private Collection<OrderDetailsEntity> orderDetailsByProductId;

    public int getCheck() {
        return check;
    }

    public void setCheck(int check) {
        this.check = check;
    }

    private int check;


    @Id
    @Column(name = "PRODUCT_ID")
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    @Basic
    @Column(name = "PRODUCT_NAME")
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Basic
    @Column(name = "PRODUCT_TYPE")
    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductsEntity that = (ProductsEntity) o;

        if (productId != null ? !productId.equals(that.productId) : that.productId != null) return false;
        if (productName != null ? !productName.equals(that.productName) : that.productName != null) return false;
        if (productType != null ? !productType.equals(that.productType) : that.productType != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = productId != null ? productId.hashCode() : 0;
        result = 31 * result + (productName != null ? productName.hashCode() : 0);
        result = 31 * result + (productType != null ? productType.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "productsByProductId")
    public Collection<OrderDetailsEntity> getOrderDetailsByProductId() {
        return orderDetailsByProductId;
    }

    public void setOrderDetailsByProductId(Collection<OrderDetailsEntity> orderDetailsByProductId) {
        this.orderDetailsByProductId = orderDetailsByProductId;
    }
}

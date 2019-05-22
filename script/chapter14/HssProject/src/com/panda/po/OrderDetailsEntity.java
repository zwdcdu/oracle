package com.panda.po;

import javax.persistence.*;

@Entity
@Table(name = "ORDER_DETAILS", schema = "STUDY", catalog = "")
public class OrderDetailsEntity {
    private int id;
    private int orderId;
    private String productId;
    private long productNum;
    private long productPrice;
    private OrdersEntity ordersByOrderId;
    private ProductsEntity productsByProductId;

    @Id
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "ORDER_ID")
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Basic
    @Column(name = "PRODUCT_ID")
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    @Basic
    @Column(name = "PRODUCT_NUM")
    public long getProductNum() {
        return productNum;
    }

    public void setProductNum(long productNum) {
        this.productNum = productNum;
    }

    @Basic
    @Column(name = "PRODUCT_PRICE")
    public long getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(long productPrice) {
        this.productPrice = productPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderDetailsEntity that = (OrderDetailsEntity) o;

        if (id != that.id) return false;
        if (orderId != that.orderId) return false;
        if (productNum != that.productNum) return false;
        if (productPrice != that.productPrice) return false;
        if (productId != null ? !productId.equals(that.productId) : that.productId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + orderId;
        result = 31 * result + (productId != null ? productId.hashCode() : 0);
        result = 31 * result + (int) (productNum ^ (productNum >>> 32));
        result = 31 * result + (int) (productPrice ^ (productPrice >>> 32));
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "ORDER_ID", referencedColumnName = "ORDER_ID", nullable = false)
    public OrdersEntity getOrdersByOrderId() {
        return ordersByOrderId;
    }

    public void setOrdersByOrderId(OrdersEntity ordersByOrderId) {
        this.ordersByOrderId = ordersByOrderId;
    }

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "PRODUCT_ID", nullable = false)
    public ProductsEntity getProductsByProductId() {
        return productsByProductId;
    }

    public void setProductsByProductId(ProductsEntity productsByProductId) {
        this.productsByProductId = productsByProductId;
    }
}

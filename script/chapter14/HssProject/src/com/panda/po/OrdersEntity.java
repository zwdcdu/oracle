package com.panda.po;

import javax.persistence.*;
import java.util.Date;
import java.util.Collection;

@Entity
@Table(name = "ORDERS", schema = "STUDY", catalog = "")
public class OrdersEntity {
    private int orderId;
    private String customerName;
    private String customerTel;
    private Date orderDate;
    private long employeeId;
    private Long discount;
    private Long tradeReceivable;
    private EmployeesEntity employeesByEmployeeId;
    private Collection<OrderDetailsEntity> orderDetailsByOrderId;

    public int getHasDetail() {
        return hasDetail;
    }

    public void setHasDetail(int hasDetail) {
        this.hasDetail = hasDetail;
    }

    private int hasDetail;
    @Id
    @Column(name = "ORDER_ID")
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Basic
    @Column(name = "CUSTOMER_NAME")
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @Basic
    @Column(name = "CUSTOMER_TEL")
    public String getCustomerTel() {
        return customerTel;
    }

    public void setCustomerTel(String customerTel) {
        this.customerTel = customerTel;
    }

    @Basic
    @Column(name = "ORDER_DATE")
    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    @Basic
    @Column(name = "EMPLOYEE_ID")
    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    @Basic
    @Column(name = "DISCOUNT")
    public Long getDiscount() {
        return discount;
    }

    public void setDiscount(Long discount) {
        this.discount = discount;
    }

    @Basic
    @Column(name = "TRADE_RECEIVABLE")
    public Long getTradeReceivable() {
        return tradeReceivable;
    }

    public void setTradeReceivable(Long tradeReceivable) {
        this.tradeReceivable = tradeReceivable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrdersEntity that = (OrdersEntity) o;

        if (orderId != that.orderId) return false;
        if (employeeId != that.employeeId) return false;
        if (customerName != null ? !customerName.equals(that.customerName) : that.customerName != null) return false;
        if (customerTel != null ? !customerTel.equals(that.customerTel) : that.customerTel != null) return false;
        if (orderDate != null ? !orderDate.equals(that.orderDate) : that.orderDate != null) return false;
        if (discount != null ? !discount.equals(that.discount) : that.discount != null) return false;
        if (tradeReceivable != null ? !tradeReceivable.equals(that.tradeReceivable) : that.tradeReceivable != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = orderId;
        result = 31 * result + (customerName != null ? customerName.hashCode() : 0);
        result = 31 * result + (customerTel != null ? customerTel.hashCode() : 0);
        result = 31 * result + (orderDate != null ? orderDate.hashCode() : 0);
        result = 31 * result + (int) (employeeId ^ (employeeId >>> 32));
        result = 31 * result + (discount != null ? discount.hashCode() : 0);
        result = 31 * result + (tradeReceivable != null ? tradeReceivable.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "EMPLOYEE_ID", referencedColumnName = "EMPLOYEE_ID", nullable = false)
    public EmployeesEntity getEmployeesByEmployeeId() {
        return employeesByEmployeeId;
    }

    public void setEmployeesByEmployeeId(EmployeesEntity employeesByEmployeeId) {
        this.employeesByEmployeeId = employeesByEmployeeId;
    }

    @OneToMany(mappedBy = "ordersByOrderId")
    public Collection<OrderDetailsEntity> getOrderDetailsByOrderId() {
        return orderDetailsByOrderId;
    }

    public void setOrderDetailsByOrderId(Collection<OrderDetailsEntity> orderDetailsByOrderId) {
        this.orderDetailsByOrderId = orderDetailsByOrderId;
    }
}

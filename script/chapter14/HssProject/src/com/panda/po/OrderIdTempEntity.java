package com.panda.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ORDER_ID_TEMP", schema = "STUDY", catalog = "")
public class OrderIdTempEntity {
    private int orderId;

    @Id
    @Column(name = "ORDER_ID")
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderIdTempEntity that = (OrderIdTempEntity) o;

        if (orderId != that.orderId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return orderId;
    }
}

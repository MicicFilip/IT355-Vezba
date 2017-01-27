/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proba.entity;

import java.util.Objects;
import javax.persistence.*;

/**
 *
 * @author Micic
 */
@Entity
@Table(name = "inventory")
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "datum", nullable = false)
    private String datum;

    @JoinColumn(name = "categoryId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Category categoryId;

    @JoinColumn(name = "instrumentId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Instrument instrumentId;

    @JoinColumn(name = "storeId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Store storeId;

    public Inventory() {
    }

    public Inventory(Integer id) {
        this.id = id;
    }

    public Inventory(Integer id, Integer quantity, String datum) {
        this.id = id;
        this.quantity = quantity;
        this.datum = datum;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public Category getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Category categoryId) {
        this.categoryId = categoryId;
    }

    public Instrument getInstrumentId() {
        return instrumentId;
    }

    public void setInstrumentId(Instrument instrumentId) {
        this.instrumentId = instrumentId;
    }

    public Store getStoreId() {
        return storeId;
    }

    public void setStoreId(Store storeId) {
        this.storeId = storeId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Inventory)) {
            return false;
        }
        Inventory other = (Inventory) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Inventory{" + "id=" + id + ", quantity=" + quantity + ", datum=" + datum + ", categoryId=" + categoryId + ", instrumentId=" + instrumentId + ", storeId=" + storeId + '}';
    }

}

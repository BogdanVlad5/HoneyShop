package com.honeyshop.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "SALES")
public class Sale extends AbstractEntity {

    @Column(name = "DATE_SOLD")
    private LocalDate dateSold;
    @Column(name = "AMOUNT_SOLD")
    private int amountSold;
    private boolean available;

    public Sale() {
    }

    public Sale(LocalDate dateSold, int amountSold, boolean available) {
        this.dateSold = dateSold;
        this.amountSold = amountSold;
        this.available = available;
    }

    public LocalDate getDateSold() {
        return dateSold;
    }

    public void setDateSold(LocalDate dateSold) {
        this.dateSold = dateSold;
    }

    public int getAmountSold() {
        return amountSold;
    }

    public void setAmountSold(int amountSold) {
        this.amountSold = amountSold;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}

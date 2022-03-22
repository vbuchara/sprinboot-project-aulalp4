package com.ifms.edu.siaula3.models;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.ifms.edu.siaula3.enums.Pools;
import com.ifms.edu.siaula3.enums.EnumTypePgsql;

import org.springframework.format.annotation.DateTimeFormat;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
@TypeDef(name = "enum_pools", typeClass = EnumTypePgsql.class)

@Entity
public class Item {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 2)
    private String name;

    @NotNull
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "enum_pools")
    @Type(type = "enum_pools")
    private Pools pool;

    @Max(value=4)
    @Min(value=0)
    @NotNull
    private int quality;

    @Max(value=6)
    @Min(value=0)
    private Integer charges;

    @NotNull
    @Max(value=5)
    @Min(value=0)
    private double stars = 0;

    @Basic
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date createdAt;

    public Item() {
    }

    public Item(
        Long id, 
        String name, 
        String description, 
        Pools pool, 
        int quality, 
        Integer charges,
        double stars,
        Date createdAt
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.pool = pool;
        this.quality = quality;
        this.charges = charges;
        this.stars = stars;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Pools getPool() {
        return this.pool;
    }

    public void setPool(Pools pool) {
        this.pool = pool;
    }

    public int getQuality() {
        return this.quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public Integer getCharges() {
        return this.charges;
    }

    public void setCharges(Integer charges) {
        this.charges = charges;
    }   

    public double getStars() {
        return this.stars;
    }

    public void setStars(double stars) {
        this.stars = stars;
    }    

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Item: {" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", pool='" + getPool() + "'" +
            ", quality='" + getQuality() + "'" +
            ", charges='" + getCharges() + "'" +
            ", stars='" + getStars() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            "}";
    }
}


package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.example.framework.data.JPAEntity;

/**
 * Category Entity
 *
 * Created by Y.Kamesh on 8/2/2015.
 */
@Entity
@Table(indexes = {  @Index(name="name_idx", columnList = "name", unique = true),
        @Index(name="priority_idx", columnList = "priority")})
public class Category extends JPAEntity<Long> {
    private String name;
    private Integer priority;
    private Category parentCategory;

    @NotNull @NotBlank
    @Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull
    @Column
    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    public Category getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }

    
    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", priority=" + priority +
                ", parentCategory=" + parentCategory +
                '}';
    }
}

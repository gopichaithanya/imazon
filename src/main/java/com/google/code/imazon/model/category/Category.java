package com.google.code.imazon.model.category;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.Immutable;

@Entity
@Immutable
public class Category {
	private Long categoryId;
	private String name;

	public Category() {
	};

	public Category(String name) {
		super();
		this.name = name;
	}
	
	@Id
	@SequenceGenerator(name = "CategoryIdGenerator", sequenceName = "CategorySeq")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "CategoryIdGenerator")
	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

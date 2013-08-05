package com.google.code.imazon.model.category;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
@org.hibernate.annotations.Immutable
public class Category {
	private Long categoryId;
	private String name;

	public Category() {
	};

	public Category(String name) {
		super();
		this.name = name;
	}
	@Column(name = "categoryId")
	@SequenceGenerator(name = "CategoryIdGenerator", sequenceName = "CategorySeg")
	@Id
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

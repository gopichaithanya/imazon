package com.google.code.imazon.model.category;

import javax.persistence.*;

@Entity
@org.hibernate.annotations.Immutable
@org.hibernate.annotations.BatchSize(size = 10)
public class Category {
	private String name;
	private Short categoryId;

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
	public Short getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(short categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

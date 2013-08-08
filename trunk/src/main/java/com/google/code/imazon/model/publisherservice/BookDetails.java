package com.google.code.imazon.model.publisherservice;

import java.math.BigDecimal;
import java.util.Calendar;

import com.google.code.imazon.model.category.Category;

public class BookDetails {

	private String title;
	private String author;
	private Category category;
	private Calendar publicationDate;
	private BigDecimal price;
	private String language;
	private String city;
	private String country;
	private String isbn;
	
	public BookDetails(String title, String author, Category category,
			BigDecimal price, String language, String city, String country,
			String isbn) {
		this.title = title;
		this.author = author;
		this.category = category;
		this.price = price;
		this.language = language;
		this.city = city;
		this.country = country;
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public Category getCategory() {
		return category;
	}
	
	public Calendar getPublicationDate() {
		return publicationDate;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public String getLanguage() {
		return language;
	}

	public String getCity() {
		return city;
	}

	public String getCountry() {
		return country;
	}

	public String getIsbn() {
		return isbn;
	}
}

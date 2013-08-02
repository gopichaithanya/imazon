package com.google.code.imazon.model.book;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.*;

import com.google.code.imazon.model.category.Category;
import com.google.code.imazon.model.user.User;

@Entity
@org.hibernate.annotations.BatchSize(size = 10)
public class Book {
	private Long bookId;
	private String title;
	private String author;
	private Category category;
	private User publisher;
	private Calendar year;
	private BigDecimal price;
	private String language;
	private String city;
	private String country;
	private String isbn;
	private Long version;

	public Book() {
	}

	public Book(String title, String author, Category category, User publisher,
			Calendar year, BigDecimal price, String language, String city,
			String country, String isbn) {
		super();
		this.title = title;
		this.author = author;
		this.category = category;
		this.publisher = publisher;
		this.year = year;
		this.price = price;
		this.language = language;
		this.city = city;
		this.country = country;
		this.isbn = isbn;
	}

	@Column(name = "bookId")
	@SequenceGenerator(name = "BookIdGenerator", sequenceName = "BookSeg")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "BookIdGenerator")
	public long getBookId() {
		return bookId;
	}

	public void setBookId(long bookId) {
		this.bookId = bookId;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "categoryId")
	public Category getCategory() {
		return category;
	}
	
	public void setCategory(Category category) {
		this.category = category;
	}
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	public User getPublisher() {
		return publisher;
	}
	
	public void setPublisher(User publisher) {
		this.publisher = publisher;
	}
	
	@Temporal(TemporalType.DATE)
	public Calendar getYear() {
		return year;
	}
	
	public void setYear(Calendar year) {
		this.year = year;
	}
	
	public BigDecimal getPrice() {
		return price;
	}
	
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	public String getLanguage() {
		return language;
	}
	
	public void setLanguage(String language) {
		this.language = language;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	public String getIsbn() {
		return isbn;
	}
	
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	@Version
	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}
}

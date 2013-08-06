package com.google.code.imazon.model.book;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.hibernate.annotations.BatchSize;

import com.google.code.imazon.model.category.Category;
import com.google.code.imazon.model.user.User;

@Entity
@BatchSize(size = 10)
public class Book {
	private Long bookId;
	private String title;
	private String author;
	private Category category;
	private User publisher;
	private Calendar publicationDate;
	private BigDecimal price;
	private String language;
	private String city;
	private String country;
	private String isbn;
	private Long version;

	public Book() {
	}

	public Book(String title, String author, Category category, User publisher,
			Calendar publicationDate, BigDecimal price, String language, String city,
			String country, String isbn) {
		super();
		this.title = title;
		this.author = author;
		this.category = category;
		this.publisher = publisher;
		this.publicationDate = publicationDate;
		this.price = price;
		this.language = language;
		this.city = city;
		this.country = country;
		this.isbn = isbn;
	}

	@Id
	@SequenceGenerator(name = "BookIdGenerator", sequenceName = "BookSeq")
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
	@JoinColumn(name = "publisherId", referencedColumnName = "userId")
	public User getPublisher() {
		return publisher;
	}
	
	public void setPublisher(User publisher) {
		this.publisher = publisher;
	}
	
	@Temporal(TemporalType.DATE)
	public Calendar getPublicationDate() {
		return publicationDate;
	}
	
	public void setPublicationDate(Calendar publicationDate) {
		this.publicationDate = publicationDate;
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

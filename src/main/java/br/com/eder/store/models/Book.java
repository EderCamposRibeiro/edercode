package br.com.eder.store.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Book {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank // Null and Blank
	private String title;
	
	@Lob // In order to use a big text description.
	@Length(min = 10) // Minimal number of characters
	@NotBlank
	private String description;
	
	@DecimalMin("20") // Minimal decimal value
	private BigDecimal price;
	
	@Min(50) // Minimal integer value
	private Integer numberOfPages;
	
	@ManyToMany
	@Size(min = 1) // Minimal amount of elements on the list
	@NotNull // List cannot be Null
	private List<Author> authors = new ArrayList<>();
	
	public List<Author> getAuthors() {
		return authors;
	}
	
	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getNumberOfPages() {
		return numberOfPages;
	}

	public void setNumberOfPages(Integer numberOfPages) {
		this.numberOfPages = numberOfPages;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", description=" + description + ", price=" + price
				+ ", numberOfPages=" + numberOfPages + ", authors=" + authors + "]";
	}
}

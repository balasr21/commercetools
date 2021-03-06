package com.rasmivan.commercetools.domain;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "invoice")
public class Invoice {
	
	/** The id. */
	private String id;
	
	private Long quantity;
	
	private ProductMaster productId;
	
	private Instant timestamp;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name = "id", columnDefinition = "INT", unique = true, nullable = false)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "quantity", nullable = false)
	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "productid", nullable = false)
	@JsonBackReference
	public ProductMaster getProductId() {
		return productId;
	}

	public void setProductId(ProductMaster productId) {
		this.productId = productId;
	}

	@Column(name = "timestamp", nullable = true, columnDefinition = "DATETIME(6)")
	public Instant getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Instant timestamp) {
		this.timestamp = timestamp;
	}
	
}

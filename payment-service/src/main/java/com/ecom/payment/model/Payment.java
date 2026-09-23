package com.ecom.payment.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.validation.annotation.Validated;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Validated
@Builder
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "payment")
public class Payment {

	@Id
	@GeneratedValue
	private Integer id;
	
	private BigDecimal amount;
	
	@Enumerated(EnumType.STRING)
	private PaymentMethod payMethod;
	
	private Integer OrderId;
	
	@CreatedDate
	@Column(updatable = false, nullable = false)
	private LocalDateTime createDate;
	
	@LastModifiedDate
	@Column(insertable = false)
	private LocalDateTime lastModifiedDate;
}

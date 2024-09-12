package com.example.studentms.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="hellosample")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@ToString
public class Product {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)//Primary key generation
	private Long id;
	@Column(nullable = false)//Column will be not null.For length there is "length", but by default it is 255.
	private String name;
	private String description;
}

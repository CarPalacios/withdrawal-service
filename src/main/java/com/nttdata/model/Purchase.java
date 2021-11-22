package com.nttdata.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.nttdata.model.Purchase;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "purchase")
@Data
public class Purchase {

	@Id
	private String id;
	
	@Field(name = "cardNumber")
	private String cardNumber;
		
}
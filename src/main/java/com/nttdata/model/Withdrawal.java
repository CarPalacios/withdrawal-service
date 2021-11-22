package com.nttdata.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.nttdata.model.Withdrawal;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Document(collection = "withdrawal")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Withdrawal {

	@Id
	private String id;
	
	@Field(name = "cardNumber")
	private String cardNumber;
	
	@Field(name = "amount")
	private Double amount;
	
	@Field(name = "description")
	private String description;

	@Field(name = "withdrawalDate")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime withdrawalDate = LocalDateTime.now();

}

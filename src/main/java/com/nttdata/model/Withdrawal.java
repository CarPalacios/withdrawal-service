package com.nttdata.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.nttdata.model.Withdrawal;
import java.time.LocalDateTime;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**Se crea la clase Withdrawal y se definen las variables.*/
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

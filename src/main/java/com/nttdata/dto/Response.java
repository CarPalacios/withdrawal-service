package com.nttdata.dto;

import com.nttdata.dto.Response;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
public class Response {
	
	private String error;
	
	private Object data;

}

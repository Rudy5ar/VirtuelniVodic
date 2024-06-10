package com.pmf.pris.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExceptionDTO {
	private String message;
}

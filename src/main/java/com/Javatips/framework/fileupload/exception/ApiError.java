package com.Javatips.framework.fileupload.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiError {
	
	private int outcomeCode;
	private String outcomeMessage;
	private String internalMessage;

}

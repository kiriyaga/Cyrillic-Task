package com.task.beans.messages;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class ResponseMessage {

	private String message;

	public ResponseMessage(String message) {
		
		this.setMessage(message);

	}
}

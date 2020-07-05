package com.task.utils.logger;

public final class Messages {

	public static String getLoggerMessage(OperationEnum operation, Object object) {

		return operation + ": " + object;	
	}
}

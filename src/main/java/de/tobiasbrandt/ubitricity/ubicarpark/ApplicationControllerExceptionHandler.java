package de.tobiasbrandt.ubitricity.ubicarpark;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApplicationControllerExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(ApplicationControllerExceptionHandler.class);

	/**
	 * Handles a {@link UbicarparkApiException}, giving out a message via the API
	 */
	@ExceptionHandler(value = UbicarparkApiException.class)
	public ResponseEntity<ErrorEntity> handleUbicarparkApiException(UbicarparkApiException e) {
		if (logger.isDebugEnabled()) {
			logger.debug("UbicarparkApiException thrown: ", e);
		}
		return new ResponseEntity<ErrorEntity>(new ErrorEntity(e), e.getHttpStatus());
	}

}

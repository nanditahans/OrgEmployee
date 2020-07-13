package com.example.demo.exception;


import com.example.demo.dto.ErrorResponseObject;
import com.example.demo.dto.ResponseDTO;
import com.example.demo.dto.RootResponseDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
	public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


		@ExceptionHandler(ResourceNotFoundException.class)
		public ResponseEntity<RootResponseDTO> resourceNotFoundException(ResourceNotFoundException ex) {
			//ErrorDetails errorDetails = new ErrorDetails(new Date(),ex.getMessage(), request.getDescription(false));
			List<ErrorResponseObject> errorResponseObjectList=new ArrayList<ErrorResponseObject>();

			ErrorResponseObject errorResponseObject= new ErrorResponseObject("NOT_FOUND",ex.getMessage());
			errorResponseObjectList.add(errorResponseObject);
			return ResponseEntity.ok(new RootResponseDTO(false,"",true,errorResponseObjectList));
		}
		@ExceptionHandler(MethodArgumentNotValid.class)
			public ResponseEntity<RootResponseDTO> handleMethodArgumentNotValid(MethodArgumentNotValid ex) {
			List<ErrorResponseObject> errorResponseObjectList=new ArrayList<ErrorResponseObject>();
			ErrorResponseObject errorResponseObject=new ErrorResponseObject("MethodArgumentNotValidException",ex.getMessage());
			errorResponseObjectList.add(errorResponseObject);
			return ResponseEntity.ok(new RootResponseDTO(false,"Validation Failed",true,errorResponseObjectList));
	    }
		/*@ExceptionHandler(MethodArgumentNotValidException.class)
		ResponseEntity<?> MethodArgumentNotValidException(MethodArgumentNotValidException ex,WebRequest request) {
			ErrorDetails errorDetails=new ErrorDetails(new Date(),"not valid due to validation error: " + ex.getMessage(), request.getDescription(false));
			return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
		}*/
		@ExceptionHandler(Exception.class)
		public ResponseEntity<RootResponseDTO> globalExceptionHandler(Exception ex) {
			List<ErrorResponseObject> errorResponseObjectList=new ArrayList<ErrorResponseObject>();
			ErrorResponseObject errorResponseObject=new ErrorResponseObject("INTERNAL SERVER ERROR",ex.getMessage());
			errorResponseObjectList.add(errorResponseObject);
			return ResponseEntity.ok(new RootResponseDTO(false,"",true,errorResponseObjectList));
		}
	}



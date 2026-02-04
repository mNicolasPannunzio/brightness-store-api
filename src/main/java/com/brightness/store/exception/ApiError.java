package com.brightness.store.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ApiError {
  
  private int status;
  private String error;
  private String message;
  private LocalDateTime timestamp;

  public ApiError(HttpStatus pStatus, String pMessage){
    this.setStatus(pStatus.value());
    this.setError(pStatus.name());
    this.setMessage(pMessage);
    this.setTimestamp(LocalDateTime.now());

  }

  //Getters

  public int getStatus(){
    return this.status;
  }

  public String getError(){
    return this.error;
  }

  public String getMessage(){
    return this.message;
  }

  public LocalDateTime getTimestamp(){
    return this.timestamp;
  }

  //Setters

  private void setStatus(int pStatus){
    this.status = pStatus;
  }

  private void setError(String pError){
    this.error = pError;
  }

  private void setMessage(String pMessage){
    this.message = pMessage;
  }

  private void setTimestamp(LocalDateTime pTimestamp){
    this.timestamp = pTimestamp;
  }
}

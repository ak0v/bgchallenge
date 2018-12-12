package com.bg.restchallange.service;

public class RestRequest<T> {
   private String path;
   private MethodType methodType;
   private String pathParam;
   private String queryParam;
   
   public RestRequest( String path, MethodType methodType, String pathParam, String queryParam) {
	this.path = pathParam + path;
	this.methodType = methodType;
	this.pathParam = pathParam;
	this.queryParam = queryParam;
   }

   public String getPath() {
	return path;
   }
   public void setPath(String path) {
	this.path = path;
   }
   public MethodType getMethodType() {
	return methodType;
   }
   public void setMethodType(MethodType methodType) {
	this.methodType = methodType;
   }
   public String getPathParam() {
	return pathParam;
   }
   public void setPathParam(String pathParam) {
	this.pathParam = pathParam;
   }
   public String getQueryParam() {
	return queryParam;
   }
   public void setQueryParam(String queryParam) {
	this.queryParam = queryParam;
   }
}

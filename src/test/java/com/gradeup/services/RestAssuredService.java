package com.gradeup.services;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 
 * @author dgupta54
 *
 */
public class RestAssuredService {

	/**
	 * GET Request
	 * 
	 * @param baseURL
	 * @param childURL
	 * @param headers
	 * @param queryParams
	 * @return
	 * @throws IllegalArgumentException
	 * @throws UnsupportedEncodingException
	 * @throws JWTCreationException
	 * @throws JSONException
	 * @throws ParseException 
	 */
	public static JSONObject getJSONObjectAsResponse(String baseURL, String childURL, Map<String, String> headers, Map<String, String> queryParams) throws IllegalArgumentException, UnsupportedEncodingException, JSONException {
		RestAssured.baseURI = baseURL;
		RequestSpecification request = given();

		//Setting headers in Request
		for(String key : headers.keySet()) {
			request.header(key, headers.get(key));
		}

		//Setting Query Params
		for(String key : queryParams.keySet()) {
			request.queryParam(key, queryParams.get(key));
		}

		//Return response as JSONObject
		return new JSONObject(request.get(childURL).getBody().asString());
	}
	
	/**
	 * GET Request
	 * 
	 * @param baseURL
	 * @param childURL
	 * @param headers
	 * @param queryParams
	 * @return
	 * @throws IllegalArgumentException
	 * @throws UnsupportedEncodingException
	 * @throws JWTCreationException
	 * @throws JSONException
	 * @throws ParseException 
	 */
	public static JSONArray getJSONArrayAsResponse(String baseURL, String childURL, Map<String, String> headers, Map<String, String> queryParams) throws IllegalArgumentException, UnsupportedEncodingException, JSONException {
		RestAssured.baseURI = baseURL;
		RequestSpecification request = given();

		//Setting headers in Request
		for(String key : headers.keySet()) {
			request.header(key, headers.get(key));
		}

		//Setting Query Params
		for(String key : queryParams.keySet()) {
			request.queryParam(key, queryParams.get(key));
		}

		//Return response as JSONObject
		return new JSONArray(request.get(childURL).getBody().asString());
	}

	/**
	 * GET Request
	 * 
	 * @param baseURL
	 * @param childURL
	 * @param headers
	 * @param queryParams
	 * @return
	 * @throws IllegalArgumentException
	 * @throws UnsupportedEncodingException
	 * @throws JWTCreationException
	 * @throws JSONException
	 * @throws ParseException 
	 */
	public static String getStringAsResponse(String baseURL, String childURL, Map<String, String> headers, Map<String, String> queryParams) throws IllegalArgumentException, UnsupportedEncodingException, JSONException {
		RestAssured.baseURI = baseURL;
		RequestSpecification request = given();

		//Setting headers in Request
		for(String key : headers.keySet()) {
			request.header(key, headers.get(key));
		}

		//Setting Query Params
		for(String key : queryParams.keySet()) {
			request.queryParam(key, queryParams.get(key));
		}

		//Return response as JSONObject
		return request.get(childURL).getBody().asString();
	}

	/**
	 * POST Request
	 * 
	 * @param baseURL
	 * @param childURL
	 * @param headers
	 * @param requestParams
	 * @return
	 * @throws IllegalArgumentException
	 * @throws UnsupportedEncodingException
	 * @throws JWTCreationException
	 * @throws JSONException
	 * @throws ParseException 
	 */
	public static JSONObject postJSONObjectAsResponse(String baseURL, String childURL, Map<String, String> headers, JSONObject requestParams) throws IllegalArgumentException, UnsupportedEncodingException, JSONException {
		RestAssured.baseURI = baseURL;
		RequestSpecification request = given();

		//Setting Content Type as JSON
		request.contentType(ContentType.JSON);

		//Setting headers in request
		for(String key : headers.keySet()) {
			request.header(key, headers.get(key));
		}

		//Setting Body as JSON Object
		request.body(requestParams);

		//Return response as JSONObject
		return new JSONObject(request.post(childURL).getBody().asString());
	}
	
	/**
	 * POST Request
	 * 
	 * @param baseURL
	 * @param childURL
	 * @param headers
	 * @param requestParams
	 * @return
	 * @throws IllegalArgumentException
	 * @throws UnsupportedEncodingException
	 * @throws JWTCreationException
	 * @throws JSONException
	 * @throws ParseException 
	 */
	public static JSONArray postJSONArrayAsResponse(String baseURL, String childURL, Map<String, String> headers, JSONObject requestParams) throws IllegalArgumentException, UnsupportedEncodingException, JSONException {
		RestAssured.baseURI = baseURL;
		RequestSpecification request = given();

		//Setting Content Type as JSON
		request.contentType(ContentType.JSON);

		//Setting headers in request
		for(String key : headers.keySet()) {
			request.header(key, headers.get(key));
		}

		//Setting Body as JSON Object
		request.body(requestParams);

		//Return response as JSONObject
		return new JSONArray(request.post(childURL).getBody().asString());
	}

	/**
	 * POST Request
	 * 
	 * @param baseURL
	 * @param childURL
	 * @param headers
	 * @param requestParams
	 * @return
	 * @throws IllegalArgumentException
	 * @throws UnsupportedEncodingException
	 * @throws JWTCreationException
	 * @throws JSONException
	 * @throws ParseException 
	 */
	public static String postStringAsResponse(String baseURL, String childURL, Map<String, String> headers, JSONObject requestParams) throws IllegalArgumentException, UnsupportedEncodingException, JSONException {
		RestAssured.baseURI = baseURL;
		RequestSpecification request = given();

		//Setting Content Type as JSON
		request.contentType(ContentType.JSON);

		//Setting headers in request
		for(String key : headers.keySet()) {
			request.header(key, headers.get(key));
		}

		//Setting Body as JSON Object
		request.body(requestParams);

		//Return response as JSONObject
		return request.post(childURL).getBody().asString();
	}

}
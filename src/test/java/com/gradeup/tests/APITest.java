package com.gradeup.tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.annotations.Test;

import com.gradeup.helpers.Constants;
import com.gradeup.helpers.Log;
import com.gradeup.helpers.PropertyReader;
import com.gradeup.services.RestAssuredService;
import com.gradeup.utilities.ExcelUtilities;

/**
 * 
 * @author dgupta54
 *
 */
public class APITest{

	@Test
	public void APITest() throws IllegalArgumentException, JSONException, InvalidFormatException, IOException  {
		Log.info("Starting API Test");
		RestAssuredService restAssuredService = new RestAssuredService();

		Map<String, String> headers = new HashMap<>();
		Map<String, String> queryParams = new HashMap<>();
		JSONArray responseArray = RestAssuredService.getJSONArrayAsResponse(PropertyReader.getInstance().readProperty("baseURL"), PropertyReader.getInstance().readProperty("childURL"), headers, queryParams);
		//System.out.println(responseArray.toString());


		List<Map<String, String>> data = new ArrayList<>();
		for(int i=0; i<responseArray.length();i++) {
			//System.out.println(i);
			JSONObject jsonObj = responseArray.getJSONObject(i);
			Map<String, String> lineData = new HashMap<>();

			try{
				lineData.put("record_id", jsonObj.get("record_id").toString());
			}
			catch(JSONException e) {
				lineData.put("record_id", "");
			}
			try{
				lineData.put("projectName", jsonObj.get("projectName").toString());
			}
			catch(JSONException e) {
				lineData.put("projectName", "");
			}

			try{
				lineData.put("projectType", jsonObj.get("projectType").toString());
			}
			catch(JSONException e) {
				lineData.put("projectType", "");
			}

			try{
				lineData.put("description", jsonObj.get("description").toString());
			}
			catch(JSONException e) {
				lineData.put("description", "");
			}

			try{
				lineData.put("sqft", jsonObj.get("sqft").toString());
			}
			catch(JSONException e) {
				lineData.put("sqft", "");
			}

			try{
				lineData.put("estimatedProjectValuation", jsonObj.get("estimatedProjectValuation").toString());
			}
			catch(JSONException e) {
				lineData.put("estimatedProjectValuation", "");
			}

			try{
				lineData.put("number", jsonObj.get("number").toString());
			}
			catch(JSONException e) {
				lineData.put("number", "");
			}

			try{
				lineData.put("noticeType", jsonObj.get("noticeType").toString());
			}
			catch(JSONException e) {
				lineData.put("noticeType", "");
			}

			try{
				lineData.put("address", jsonObj.get("address").toString());
			}
			catch(JSONException e) {
				lineData.put("address", "");
			}

			try{
				lineData.put("city", jsonObj.get("city").toString());
			}
			catch(JSONException e) {
				lineData.put("city", "");
			}

			try{
				lineData.put("state", jsonObj.get("state").toString());
			}
			catch(JSONException e) {
				lineData.put("state", "");
			}

			try{
				lineData.put("zipcode", jsonObj.get("zipcode").toString());
			}
			catch(JSONException e) {
				lineData.put("zipcode", "");
			}

			try{
				lineData.put("contact", jsonObj.get("contact").toString());
			}
			catch(JSONException e) {
				lineData.put("contact", "");
			}

			try{
				lineData.put("contactPhone", jsonObj.get("contactPhone").toString());
			}
			catch(JSONException e) {
				lineData.put("contactPhone", "");
			}

			try{
				lineData.put("contactEmail", jsonObj.get("contactEmail").toString());
			}
			catch(JSONException e) {
				lineData.put("contactEmail", "");
			}

			try{
				lineData.put("contactAddress", jsonObj.get("contactAddress").toString());
			}
			catch(JSONException e) {
				lineData.put("contactAddress", "");
			}

			try{
				lineData.put("owner", jsonObj.get("owner").toString());
			}
			catch(JSONException e) {
				lineData.put("owner", "");
			}

			try{
				lineData.put("architect", jsonObj.get("architect").toString());
			}
			catch(JSONException e) {
				lineData.put("architect", "");
			}

			try{
				lineData.put("openDate", jsonObj.get("openDate").toString());
			}
			catch(JSONException e) {
				lineData.put("openDate", "");
			}

			try{
				lineData.put("addedTimeStamp", jsonObj.get("addedTimeStamp").toString());
			}
			catch(JSONException e) {
				lineData.put("addedTimeStamp", "");
			}

			try{
				lineData.put("status", jsonObj.get("status").toString());
			}
			catch(JSONException e) {
				lineData.put("status", "");
			}

			try{
				lineData.put("closeDate", jsonObj.get("closeDate").toString());
			}
			catch(JSONException e) {
				lineData.put("closeDate", "");
			}

			try{
				lineData.put("link", jsonObj.get("link").toString());
			}
			catch(JSONException e) {
				lineData.put("link", "");
			}

			try{
				lineData.put("type", jsonObj.get("type").toString());
			}
			catch(JSONException e) {
				lineData.put("type", "");
			}

			try{
				lineData.put("constructionStartDate", jsonObj.get("constructionStartDate").toString());
			}
			catch(JSONException e) {
				lineData.put("constructionStartDate", "");
			}

			data.add(lineData);
		}

		//Column Index Mapping as per Excel
		Map<String, Integer> columnIndexing = new HashMap<>();
		columnIndexing.put("record_id",0);
		columnIndexing.put("projectName",1);
		columnIndexing.put("projectType",2);
		columnIndexing.put("description",3);	
		columnIndexing.put("sqft",4);
		columnIndexing.put("estimatedProjectValuation",5);	
		columnIndexing.put("number",6);
		columnIndexing.put("noticeType",7);	
		columnIndexing.put("address",8);	
		columnIndexing.put("city",9);	
		columnIndexing.put("state",10);	
		columnIndexing.put("zipcode",11);	
		columnIndexing.put("contact",12);
		columnIndexing.put("contactPhone",13);
		columnIndexing.put("contactEmail",14);
		columnIndexing.put("contactAddress",15);
		columnIndexing.put("owner",16);	
		columnIndexing.put("architect",17);	
		columnIndexing.put("openDate",18);	
		columnIndexing.put("addedTimeStamp",19);
		columnIndexing.put("status",20);
		columnIndexing.put("closeDate",21);
		columnIndexing.put("link",22);
		columnIndexing.put("type",23);	
		columnIndexing.put("constructionStartDate",24);
		ExcelUtilities.WriteExcelDataInBulk(Constants.APIEXCELPATH, "Sheet1", "record_id", columnIndexing, data);

		System.out.println(data);
	}
}
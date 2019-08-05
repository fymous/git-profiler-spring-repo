package com.github.profile.searchapp.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.profile.searchapp.dao.UserService;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/history")
	public List<String> getSearchHistory() {
		return userService.getSearch();		
	}
	
	@GetMapping("/history/remove")
	public boolean removeSearchHistory(@RequestBody String userInfo) {
		return userService.removeSearch(userInfo);		
	}
	
	@PostMapping("/save")
	public boolean saveUser(@RequestBody String userInfo) {
		JSONObject jsonObject = getJSONObj(userInfo);
        String handleName = (String) jsonObject.get("login");
        System.out.println(handleName);
        Map<String, String> userData = new HashMap<>();
        userData.put("name", handleName);
        userData.put("info", userInfo);
        userService.saveUser(userData);
        return true;		
	}
	
    /**
     * Utility function to generate json from json-string
     */	
	public JSONObject getJSONObj(String userInfo) {
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = null;
		try {
			jsonObject = (JSONObject) jsonParser.parse(userInfo);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return jsonObject;
	}
	
}

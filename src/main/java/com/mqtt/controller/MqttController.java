package com.mqtt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mqtt.MqttGateway;

@RestController
public class MqttController {
	@Autowired
	MqttGateway mqtGateway;
	@PostMapping("/sendMessage")
	public ResponseEntity<?> publish(@RequestBody String mqttMessage){
		
		try {
		JsonObject convertObject = new Gson().fromJson(mqttMessage, JsonObject.class);
		mqtGateway.sentToMqtt(convertObject.get("message").toString(), convertObject.get("topic").toString());
		return ResponseEntity.ok("Success");
		}catch(Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.ok("fail");
		}
	}

}

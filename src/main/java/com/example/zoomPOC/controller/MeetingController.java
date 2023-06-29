package com.example.zoomPOC.controller;

import com.example.zoomPOC.DTO.AddMeetingRequestDTO;
import com.example.zoomPOC.service.ZoomApiExample;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class MeetingController {

    @Autowired
    ZoomApiExample zoomApiExample;

    @GetMapping("/api/{userId}/meetings")
    public ResponseEntity getMeetingsByUserId(
            @RequestHeader(value = "accessToken") String accessToken,
            @PathVariable(value = "userId") String userId
    ) throws IOException {
        return ResponseEntity.ok().body(zoomApiExample.getMeetingsByUserId(userId, accessToken));
    }

    @PostMapping("/api/{userId}/meetings")
    public ResponseEntity createMeeting(
            @RequestHeader(value = "accessToken") String accessToken,
            @PathVariable(value = "userId") String userId,
            @RequestBody String json,
            BindingResult errors
    ) {
        return ResponseEntity.ok().body(zoomApiExample.createMeeting(userId, accessToken, json));
    }

    @GetMapping("/api/pastMeetings/{meetingId}")
    public ResponseEntity getPastMeetingByMeetingId(
            @RequestHeader(value = "accessToken") String accessToken,
            @PathVariable(value = "meetingId") String meetingId
    ) throws IOException {
        return ResponseEntity.ok().body(zoomApiExample.getPastMeetingByMeetingId(meetingId, accessToken));
    }
}

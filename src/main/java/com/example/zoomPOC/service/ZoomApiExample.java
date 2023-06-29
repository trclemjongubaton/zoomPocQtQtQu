package com.example.zoomPOC.service;

import com.example.zoomPOC.DTO.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;

import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Service
public class ZoomApiExample {
    private static final String ZOOM_API_BASE_URL = "https://api.zoom.us/v2";

    public MeetingsDTO getMeetingsByUserId(String userId, String token) throws IOException {
        OkHttpClient client = new OkHttpClient();
        System.out.println(token);
        String apiUrl = ZOOM_API_BASE_URL + "/users/" + userId + "/meetings";

        // Create the HTTP request
        Request request = new Request.Builder()
                .url(apiUrl)
                .addHeader("Authorization", "Bearer " + token)
                .build();
        Response response = null;
        String responseStr = "";
        try {
            // Send the HTTP request and get the response
            response = client.newCall(request).execute();

            // Process the response
            if (response.isSuccessful()) {
                responseStr = response.body().string();
                System.out.println("Meetings API Response: " + responseStr);
            } else {
                System.out.println("Error: " + response.code() + " - " + response.message());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject jo = new JSONObject(responseStr);
        JSONArray data = (JSONArray) jo.get("meetings");

        MeetingsDTO dtos = new MeetingsDTO();
        for (Object obj : data) {
            dtos.getMeetings().add(convertJSONToDTO((JSONObject) obj));
        }

        return dtos;
    }

    private MeetingDTO convertJSONToDTO(JSONObject jo) {
        return MeetingDTO.builder()
                .id(jo.getLong("id"))
                .hostId(jo.getString("host_id"))
                .topic(jo.has("topic") ? jo.getString("topic") : "")
                .startTime(jo.getString("start_time"))
                .hostEmail(jo.has("host_email") ? jo.getString("host_email") : "")
                .duration(jo.getInt("duration"))
                .meetingLink(jo.getString("join_url"))
                .password(jo.has("password") ? jo.getString("password") : "")
                .build();
    }

    public MeetingDTO createMeeting(String userId, String token, String json) {
        OkHttpClient client = new OkHttpClient();
        System.out.println(token);
        String apiUrl = ZOOM_API_BASE_URL + "/users/" + userId + "/meetings";

//        String jsonBody = json.toString();
        RequestBody body = RequestBody.create(
                MediaType.parse("application/json"), json);

        // Create the HTTP request
        Request request = new Request.Builder()
                .url(apiUrl)
                .addHeader("Authorization", "Bearer " + token)
                .post(body)
                .build();
        Response response = null;
        String responseStr = "";
        try {
            // Send the HTTP request and get the response
            response = client.newCall(request).execute();

            // Process the response
            if (response.isSuccessful()) {
                responseStr = response.body().string();
                System.out.println("Meetings API Response: " + responseStr);
            } else {
                System.out.println("Error: " + response.code() + " - " + response.message());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject jo = new JSONObject(responseStr);
        MeetingDTO dto = convertJSONToDTO(jo);
        return dto;
    }

    public PastParticipantsDTO getPastMeetingByMeetingId(String meetingId, String token) throws IOException {
        OkHttpClient client = new OkHttpClient();
        System.out.println(token);
        String apiUrl = ZOOM_API_BASE_URL + "/past_meetings/"+ meetingId +"/participants";

        // Create the HTTP request
        Request request = new Request.Builder()
                .url(apiUrl)
                .addHeader("Authorization", "Bearer " + token)
                .build();
        Response response = null;
        String responseStr = "";
        try {
            // Send the HTTP request and get the response
            response = client.newCall(request).execute();

            // Process the response
            if (response.isSuccessful()) {
                responseStr = response.body().string();
                System.out.println("Meetings API Response: " + responseStr);
            } else {
                System.out.println("Error: " + response.code() + " - " + response.message());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject jo = new JSONObject(responseStr);
        JSONArray data = (JSONArray) jo.get("participants");

        PastParticipantsDTO dtos = new PastParticipantsDTO();
        for (Object obj : data) {
            dtos.getParticipants().add(convertParticipantJSONToDTO((JSONObject) obj));
        }

        return dtos;
    }

    private PastParticipantDTO convertParticipantJSONToDTO(JSONObject jo) {
        return PastParticipantDTO.builder()
                .id(jo.getString("id"))
                .name(jo.getString("name"))
                .userId(jo.getString("user_id"))
                .email(jo.getString("user_email"))
                .jointTime(jo.getString("join_time"))
                .leaveTime(jo.getString("leave_time"))
                .duration(jo.getInt("duration"))
                .build();
    }
}

package com.example.zoomPOC.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MeetingDTO {
    private Long id;
    private String hostId;
    private String hostEmail;
    private String topic;
    private String startTime;
    private Date endTime;
    private Integer duration;
    private String meetingLink;
    private String password;
}

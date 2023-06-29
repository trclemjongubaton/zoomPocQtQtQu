package com.example.zoomPOC.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PastParticipantDTO {
    private String id;
    private String userId;
    private String name;
    private String email;
    private String jointTime;
    private String leaveTime;
    private Integer duration;
}

package com.example.zoomPOC.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PastParticipantsDTO {

    private List<PastParticipantDTO> participants = new ArrayList<>();
}

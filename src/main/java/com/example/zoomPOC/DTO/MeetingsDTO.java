package com.example.zoomPOC.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MeetingsDTO {

    private List<MeetingDTO> meetings = new ArrayList<>();
}

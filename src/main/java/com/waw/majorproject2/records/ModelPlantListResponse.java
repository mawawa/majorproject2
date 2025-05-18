package com.waw.majorproject2.records;

import com.waw.majorproject2.models.Crop;

import java.util.List;

public record ModelPlantListResponse(String Object, List<Crop> plants) {
}

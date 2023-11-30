package com.siseth.edwin.controller;

import com.siseth.edwin.enumerate.StationType;
import com.siseth.edwin.dto.*;
import com.siseth.edwin.serwis.EdwinStationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/weather-station/edwin")
@Tag(name = "Edwin Station Controller", description = "Endpoints for getting Edwin Station Data")
public class EdwinStationController {

    private final EdwinStationService edwinStationService;

    @Autowired
    public EdwinStationController(EdwinStationService edwinStationService) {
        this.edwinStationService = edwinStationService;
    }

    @GetMapping("/observationStation")
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Get Observation Station data", description = "Get list of  Observation Station (stationId, name, type, active)")
    public ResponseEntity<ObservationStationResponseDTO> getObservationStations(@RequestParam(required = false, defaultValue = "") String contains,
                                                                                @RequestParam(required = false) StationType type,
                                                                                @RequestParam(required = false, defaultValue = "true") Boolean active,
                                                                                @RequestParam(required = false, defaultValue = "0") Integer page,
                                                                                @RequestParam(required = false, defaultValue = "100000") Integer size,
                                                                                @RequestParam(required = false, defaultValue = "asc") String sort) {
        ObservationStationRequestDTO requestDTO = new ObservationStationRequestDTO(contains, type, active, page, size, sort);
        return ResponseEntity.ok(edwinStationService.getObservationStations(requestDTO));
    }

    @GetMapping("/observationStation/{stationId}")
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Get Observation Station data by Id", description = "Get data about Observation Station data by Id (stationId, name, type, active)")
    public ResponseEntity<ObservationStationDTO> getObservationStationById(@PathVariable String stationId) {
        return ResponseEntity.ok(edwinStationService.getObservationStationById(stationId));
    }

    @GetMapping("/observationStation/location")
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Get Observation Station Location data by position", description = "Get list of ObservationStationLocation (stationId, name, type, active)")
    public ResponseEntity<ObservationStationLocationResponseDTO> getObservationStationsByLocation(@RequestParam Double latitude,
                                                                                                  @RequestParam Double longitude,
                                                                                                  @RequestParam(required = false) StationType type,
                                                                                                  @RequestParam(required = false) Integer distance,
                                                                                                  @RequestParam(required = false, defaultValue = "true") Boolean active,
                                                                                                  @RequestParam(required = false) Integer page,
                                                                                                  @RequestParam(required = false) Integer size,
                                                                                                  @RequestParam(required = false, defaultValue = "asc") String sort) {
        ObservationStationLocationRequestDTO requestDTO = new ObservationStationLocationRequestDTO(latitude, longitude, active, distance, type, page, size, sort);
        return ResponseEntity.ok(edwinStationService.getObservationStationsByLocation(requestDTO));
    }

    @GetMapping("/meteo/station")
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Get Meteo Station data by Id", description = "Get list of Meteo Station (stationId, timestamp, temperature, humidity)")
    public ResponseEntity<MeteoStationResponseDTO> getMeteoDataForStation(@RequestParam String stationId,
                                                                          @RequestParam(required = false) String after,
                                                                          @RequestParam(required = false) String before,
                                                                          @RequestParam(required = false) Integer page,
                                                                          @RequestParam(required = false) Integer size,
                                                                          @RequestParam(required = false, defaultValue = "asc") String sort) {
        MeteoStationRequestDTO requestDTO = new MeteoStationRequestDTO(stationId, after, before, page, size, sort);
        return ResponseEntity.ok(edwinStationService.getMeteoDataForStation(requestDTO));
    }

    @GetMapping("/meteo/location")
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Get Meteo Station data by position", description = "Get list of Meteo Station (stationId, timestamp, temperature, humidity)")
    public ResponseEntity<MeteoLocationResponseDTO> getMeteoDataByLocation(@RequestParam Double latitude,
                                                                           @RequestParam Double longitude,
                                                                           @RequestParam(required = false) String after,
                                                                           @RequestParam(required = false) String before,
                                                                           @RequestParam(required = false) Integer stationsCount,
                                                                           @RequestParam(required = false) Integer page,
                                                                           @RequestParam(required = false) Integer size,
                                                                           @RequestParam(required = false, defaultValue = "asc") String sort) {
        MeteoLocationRequestDTO requestDTO = new MeteoLocationRequestDTO(latitude, longitude, after, before, stationsCount, page, size, sort);
        return ResponseEntity.ok(edwinStationService.getMeteoDataByLocation(requestDTO));
    }
}

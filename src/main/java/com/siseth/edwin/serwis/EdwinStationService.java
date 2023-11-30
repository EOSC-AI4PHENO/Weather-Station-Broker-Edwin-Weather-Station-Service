package com.siseth.edwin.serwis;

import com.siseth.edwin.component.RestClient;
import com.siseth.edwin.constants.EdwinUrl;
import com.siseth.edwin.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EdwinStationService {

    private final RestClient restClient;

    @Autowired
    public EdwinStationService(RestClient restClient) {
        this.restClient = restClient;
    }

    public ObservationStationResponseDTO getObservationStations(ObservationStationRequestDTO requestDTO) {
        String url = EdwinUrl.BASE_URL_OBSERVATION_STATION;
        return restClient.sendObservationStationRequest(url, requestDTO);
    }

    public ObservationStationDTO getObservationStationById(String stationId) {
        String url = EdwinUrl.BASE_URL_OBSERVATION_STATION + "/" + stationId;
        return restClient.sendObservationStationIdRequest(url);
    }

    public ObservationStationLocationResponseDTO getObservationStationsByLocation(ObservationStationLocationRequestDTO requestDTO) {
        String url = EdwinUrl.BASE_URL_LOCATION + "/" + requestDTO.getLatitude() + "/" + requestDTO.getLongitude();
        return restClient.sendObservationStationLocationRequest(url, requestDTO);
    }

    public MeteoStationResponseDTO getMeteoDataForStation(MeteoStationRequestDTO requestDTO) {
        String url = EdwinUrl.BASE_URL_METEO_STATION + "/" + requestDTO.getStationId();
        return restClient.sendMeteoStationRequest(url, requestDTO);
    }

    public MeteoLocationResponseDTO getMeteoDataByLocation(MeteoLocationRequestDTO requestDTO) {
        String url = EdwinUrl.BASE_URL_METEO_LOCATION + "/" + requestDTO.getLatitude() + "/" + requestDTO.getLongitude();
        return restClient.sendMeteoLocationRequest(url, requestDTO);
    }
}
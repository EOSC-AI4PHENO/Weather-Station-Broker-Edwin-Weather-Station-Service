package com.siseth.edwin.component;

import com.siseth.edwin.dto.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;

@Component
public class RestClient {

    private final RestTemplate restTemplate = new RestTemplate();

    public ObservationStationDTO sendObservationStationIdRequest(String url) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<ObservationStationDTO> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.GET,
                requestEntity,
                ObservationStationDTO.class
        );

        return responseEntity.getBody();
    }

    public ObservationStationResponseDTO sendObservationStationRequest(String baseUrl, ObservationStationRequestDTO requestDTO) {
        String url = buildUrlWithQueryParams(baseUrl, requestDTO);
        return restTemplate.getForObject(url, ObservationStationResponseDTO.class);
    }


    public ObservationStationLocationResponseDTO sendObservationStationLocationRequest(String baseUrl, ObservationStationLocationRequestDTO requestDTO) {
        String url = buildUrlWithQueryParams(baseUrl, requestDTO);
        ObservationStationLocationResponseDTO response = restTemplate.getForObject(url, ObservationStationLocationResponseDTO.class);
        //TODO uprościc
        return response != null ?
                new ObservationStationLocationResponseDTO(
                        response.getContent() != null ?
                                response.getContent()
                                        .stream()
                                        .sorted(Comparator.comparing(ObservationStationDTO::getName))
                                        .collect(Collectors.toList()) :
                                Collections.emptyList(),
                                response.getPage(),
                                response.getLinks()
                ) :
                null;
    }

    public MeteoStationResponseDTO sendMeteoStationRequest(String baseUrl, MeteoStationRequestDTO requestDTO) {
        String url = buildUrlWithQueryParams(baseUrl, requestDTO);
        return restTemplate.getForObject(url, MeteoStationResponseDTO.class);
    }

    public MeteoLocationResponseDTO sendMeteoLocationRequest(String baseUrl, MeteoLocationRequestDTO requestDTO) {
        String url = buildUrlWithQueryParams(baseUrl, requestDTO);
        return restTemplate.getForObject(url, MeteoLocationResponseDTO.class);
    }

    private String buildUrlWithQueryParams(String baseUrl, Object requestDTO) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(baseUrl);

        if (requestDTO instanceof ObservationStationRequestDTO) {
            ObservationStationRequestDTO observationRequestDTO = (ObservationStationRequestDTO) requestDTO;
            addQueryParamsForObservationStationRequest(uriBuilder, observationRequestDTO);
        } else if (requestDTO instanceof ObservationStationLocationRequestDTO) {
            ObservationStationLocationRequestDTO locationRequestDTO = (ObservationStationLocationRequestDTO) requestDTO;
            addQueryParamsForObservationStationLocationRequest(uriBuilder, locationRequestDTO);
        } else if (requestDTO instanceof MeteoStationRequestDTO) {
            MeteoStationRequestDTO meteoStationRequestDTO = (MeteoStationRequestDTO) requestDTO;
            addQueryParamsForMeteoStationRequest(uriBuilder, meteoStationRequestDTO);
        } else if (requestDTO instanceof MeteoLocationRequestDTO) {
            MeteoLocationRequestDTO meteoLocationRequestDTO = (MeteoLocationRequestDTO) requestDTO;
            addQueryParamsForMeteoLocationRequest(uriBuilder, meteoLocationRequestDTO);
        }

        return uriBuilder.build().toUriString();
    }

    private UriComponentsBuilder addQueryParamsForObservationStationRequest(UriComponentsBuilder uriBuilder, ObservationStationRequestDTO requestDTO) {
        if (requestDTO.getType() != null)
            uriBuilder.queryParam("type", requestDTO.getType());

        if (requestDTO.getContains() != null)
            uriBuilder.queryParam("contains", requestDTO.getContains());

        if (requestDTO.getActive() != null)
            uriBuilder.queryParam("active", requestDTO.getActive());

        uriBuilder = paginationQueryParams(uriBuilder, requestDTO.getPage(), requestDTO.getSort(), requestDTO.getSize());
        return uriBuilder;
    }




    private UriComponentsBuilder addQueryParamsForObservationStationLocationRequest(UriComponentsBuilder uriBuilder, ObservationStationLocationRequestDTO requestDTO) {
        if (requestDTO.getDistance() != null)
            uriBuilder.queryParam("distance", requestDTO.getDistance());

        if (requestDTO.getType() != null)
            uriBuilder.queryParam("type", requestDTO.getType());

        if (requestDTO.getActive() != null)
            uriBuilder.queryParam("active", requestDTO.getActive());

        uriBuilder = paginationQueryParams(uriBuilder, requestDTO.getPage(), requestDTO.getSort(), requestDTO.getSize());

        return uriBuilder;
    }

    private UriComponentsBuilder addQueryParamsForMeteoStationRequest(UriComponentsBuilder uriBuilder, MeteoStationRequestDTO requestDTO) {
        if (requestDTO.getAfter() != null)
            uriBuilder.queryParam("after", requestDTO.getAfter());

        if (requestDTO.getBefore() != null)
            uriBuilder.queryParam("before", requestDTO.getBefore() );

        uriBuilder = paginationQueryParams(uriBuilder, requestDTO.getPage(), requestDTO.getSort(), requestDTO.getSize());

        return uriBuilder;
    }

    private UriComponentsBuilder addQueryParamsForMeteoLocationRequest(UriComponentsBuilder uriBuilder, MeteoLocationRequestDTO requestDTO) {

        if (requestDTO.getAfter() != null)
            uriBuilder.queryParam("after", requestDTO.getAfter());

        if (requestDTO.getBefore() != null)
            uriBuilder.queryParam("before", requestDTO.getBefore() );

        if (requestDTO.getStationsCount() != null)
            uriBuilder.queryParam("stationsCount", requestDTO.getStationsCount());

        uriBuilder = paginationQueryParams(uriBuilder, requestDTO.getPage(), requestDTO.getSort(), requestDTO.getSize());
        return uriBuilder;
    }

    private UriComponentsBuilder paginationQueryParams(UriComponentsBuilder uriBuilder, Integer page, String sort, Integer size) {
        if (page != null)
            uriBuilder.queryParam("page", page);

        if (sort != null)
            uriBuilder.queryParam("sort", sort);

        if (size != null)
            uriBuilder.queryParam("size", size);

        return uriBuilder;
    }
}
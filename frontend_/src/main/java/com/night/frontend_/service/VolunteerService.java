package com.night.frontend_.service;

import com.night.frontend_.model.Volunteer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class VolunteerService {

    private final RestTemplate restTemplate;

    // Use application.properties to configure this, defaulting to localhost:8080
    @Value("${api.backend.url:http://localhost:8080/api/volunteers}")
    private String apiUrl;

    public VolunteerService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Volunteer> getAllVolunteers() {
        try {
            ResponseEntity<List<Volunteer>> response = restTemplate.exchange(
                    apiUrl,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<Volunteer>>() {}
            );
            return response.getBody();
        } catch (Exception e) {
            // Log error and return empty list or throw custom exception
            System.err.println("Error fetching volunteers: " + e.getMessage());
            return List.of();
        }
    }

    public Volunteer getVolunteerById(Long id) {
        try {
            return restTemplate.getForObject(apiUrl + "/" + id, Volunteer.class);
        } catch (Exception e) {
            System.err.println("Error fetching volunteer " + id + ": " + e.getMessage());
            return null;
        }
    }

    public Volunteer createVolunteer(Volunteer volunteer) {
        return restTemplate.postForObject(apiUrl, volunteer, Volunteer.class);
    }

    public void updateVolunteer(Long id, Volunteer volunteer) {
        restTemplate.put(apiUrl + "/" + id, volunteer);
    }

    public void deleteVolunteer(Long id) {
        restTemplate.delete(apiUrl + "/" + id);
    }
}

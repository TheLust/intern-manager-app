package md.ceiti.internmanagerapp.back.service;

import com.vaadin.flow.component.UI;
import lombok.RequiredArgsConstructor;
import md.ceiti.internmanagerapp.back.BadRequest;
import md.ceiti.internmanagerapp.back.dto.AssignmentDto;
import md.ceiti.internmanagerapp.back.util.Api;
import md.ceiti.internmanagerapp.back.util.ExceptionResponse;
import md.ceiti.internmanagerapp.front.view.ErrorView;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
public class AssignmentService {

    private final RestTemplate restTemplate;

    public List<AssignmentDto> getAll() {
        try {
            return Arrays.stream(Objects.requireNonNull(restTemplate.exchange(Api.ASSIGNMENTS,
                                    HttpMethod.GET,
                                    setHeader(),
                                    AssignmentDto[].class)
                            .getBody()))
                    .toList();
        } catch (HttpClientErrorException e) {
            throw new BadRequest(Objects.requireNonNull(e.getResponseBodyAs(ExceptionResponse.class)).getMessage());
        } catch (ResourceAccessException e) {
            UI.getCurrent().navigate(ErrorView.class);
            return new ArrayList<>();
        }
    }

    public AssignmentDto save(AssignmentDto assignmentDto) {
        try {
            return restTemplate.exchange(Api.ASSIGNMENT +
                                    "?project=" +
                                    assignmentDto.getProject().getId() +
                                    "&employee=" +
                                    assignmentDto.getEmployee().getId(),
                            HttpMethod.POST,
                            setHeader(),
                            AssignmentDto.class)
                    .getBody();
        } catch (HttpClientErrorException e) {
            throw new BadRequest(Objects.requireNonNull(e.getResponseBodyAs(ExceptionResponse.class)).getMessage());
        }
    }

    public AssignmentDto update(AssignmentDto assignmentDto) {
        try {
            return restTemplate.exchange(Api.ASSIGNMENT +
                                    "?id=" +
                                    assignmentDto.getId() +
                                    "&project=" +
                                    assignmentDto.getProject().getId() +
                                    "&employee=" +
                                    assignmentDto.getEmployee().getId(),
                            HttpMethod.PUT,
                            setHeader(),
                            AssignmentDto.class)
                    .getBody();
        } catch (HttpClientErrorException e) {
            throw new BadRequest(Objects.requireNonNull(e.getResponseBodyAs(ExceptionResponse.class)).getMessage());
        }
    }

    public void delete(AssignmentDto assignmentDto) {
        try {
            restTemplate.exchange(Api.ASSIGNMENT +
                                    "?id=" +
                                    assignmentDto.getId(),
                            HttpMethod.DELETE,
                            setHeader(),
                            String.class)
                    .getBody();
        } catch (HttpClientErrorException e) {
            throw new BadRequest(Objects.requireNonNull(e.getResponseBodyAs(ExceptionResponse.class)).getMessage());
        }
    }

    private HttpEntity<String> setHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(headers);
    }

    private HttpEntity<Object> setHeader(Object object) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(object, headers);
    }
}

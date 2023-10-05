package md.ceiti.internmanagerapp.back.service;

import com.vaadin.flow.component.UI;
import lombok.RequiredArgsConstructor;
import md.ceiti.internmanagerapp.back.BadRequest;
import md.ceiti.internmanagerapp.back.dto.EmployeeDto;
import md.ceiti.internmanagerapp.back.dto.WorkRecordDto;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
public class WorkRecordService {

    private final RestTemplate restTemplate;

    public List<WorkRecordDto> getAll() {
        try {
            return Arrays.stream(Objects.requireNonNull(restTemplate.exchange(Api.RECORDS,
                                    HttpMethod.GET,
                                    setHeader(),
                                    WorkRecordDto[].class)
                            .getBody()))
                    .toList();
        } catch (HttpClientErrorException e) {
            throw new BadRequest(Objects.requireNonNull(e.getResponseBodyAs(ExceptionResponse.class)).getMessage());
        } catch (ResourceAccessException e) {
            UI.getCurrent().navigate(ErrorView.class);
            return new ArrayList<>();
        }
    }

    public List<WorkRecordDto> findByEmployeeAndDate(String employeeName,
                                                     String date) {
        if (!employeeName.isBlank() && !date.isBlank()) {
            return getAll().stream()
                    .filter(
                            workRecord -> workRecord.getAssignment().getEmployee().getDisplayName().contains(employeeName) &&
                            workRecord.getDate().toString().contains(date)
                    )
                    .toList();
        }

        if (date.isBlank() && !employeeName.isBlank()) {
            return getAll().stream()
                    .filter(workRecord -> workRecord.getAssignment().getEmployee().getDisplayName().contains(employeeName))
                    .toList();
        }

        if (!date.isBlank()) {
            return getAll().stream()
                    .filter(workRecord -> workRecord.getDate().toString().contains(date))
                    .toList();
        }

        return getAll();
    }

    public WorkRecordDto save(WorkRecordDto recordDto) {
        try {
            return restTemplate.exchange(Api.RECORD +
                                    "?assignment=" +
                                    recordDto.getAssignment().getId(),
                            HttpMethod.POST,
                            setHeader(recordDto),
                            WorkRecordDto.class)
                    .getBody();
        } catch (HttpClientErrorException e) {
            throw new BadRequest(Objects.requireNonNull(e.getResponseBodyAs(ExceptionResponse.class)).getMessage());
        }
    }

    public WorkRecordDto update(WorkRecordDto recordDto) {
        try {
            return restTemplate.exchange(Api.RECORD +
                                    "?id=" +
                                    recordDto.getId() +
                                    "&assignment=" +
                                    recordDto.getAssignment().getId(),
                            HttpMethod.PUT,
                            setHeader(recordDto),
                            WorkRecordDto.class)
                    .getBody();
        } catch (HttpClientErrorException e) {
            throw new BadRequest(Objects.requireNonNull(e.getResponseBodyAs(ExceptionResponse.class)).getMessage());
        }
    }

    public void delete(WorkRecordDto recordDto) {
        try {
            restTemplate.exchange(Api.RECORD +
                                    "?id=" +
                                    recordDto.getId(),
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

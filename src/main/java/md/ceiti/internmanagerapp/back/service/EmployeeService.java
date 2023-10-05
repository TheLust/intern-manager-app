package md.ceiti.internmanagerapp.back.service;

import com.vaadin.flow.component.UI;
import lombok.RequiredArgsConstructor;
import md.ceiti.internmanagerapp.back.BadRequest;
import md.ceiti.internmanagerapp.back.dto.EmployeeDto;
import md.ceiti.internmanagerapp.back.dto.EmployeeIncome;
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
public class EmployeeService {

    private final RestTemplate restTemplate;

    public List<EmployeeDto> getAll() {
        try {
            return Arrays.stream(Objects.requireNonNull(restTemplate.exchange(Api.EMPLOYEES,
                                    HttpMethod.GET,
                                    setHeader(),
                                    EmployeeDto[].class)
                            .getBody()))
                    .toList();
        } catch (HttpClientErrorException e) {
            throw new BadRequest(Objects.requireNonNull(e.getResponseBodyAs(ExceptionResponse.class)).getMessage());
        } catch (ResourceAccessException e) {
            UI.getCurrent().navigate(ErrorView.class);
            return new ArrayList<>();
        }
    }

    public List<EmployeeIncome> getAllEmployeeIncomes(Long id, Integer month) {
        try {
            return Arrays.stream(Objects.requireNonNull(restTemplate.exchange(Api.EMPLOYEE + "income/" +
                                            "?id=" + id +
                                            "&month=" + month,
                                    HttpMethod.GET,
                                    setHeader(),
                                    EmployeeIncome[].class)
                            .getBody()))
                    .toList();
        } catch (HttpClientErrorException e) {
            throw new BadRequest(Objects.requireNonNull(e.getResponseBodyAs(ExceptionResponse.class)).getMessage());
        }
        catch (ResourceAccessException e) {
            UI.getCurrent().navigate(ErrorView.class);
            return new ArrayList<>();
        }
    }

    public EmployeeDto save(EmployeeDto employeeDto) {
        try {
            return restTemplate.exchange(Api.EMPLOYEE +
                                    "?department=" +
                                    employeeDto.getDepartment().getId() +
                                    "&job=" +
                                    employeeDto.getJob().getId(),
                            HttpMethod.POST,
                            setHeader(employeeDto),
                            EmployeeDto.class)
                    .getBody();
        } catch (HttpClientErrorException e) {
            throw new BadRequest(Objects.requireNonNull(e.getResponseBodyAs(ExceptionResponse.class)).getMessage());
        }
    }

    public EmployeeDto update(EmployeeDto employeeDto) {
        try {
            return restTemplate.exchange(Api.EMPLOYEE +
                                    "?department=" +
                                    employeeDto.getDepartment().getId() +
                                    "&job=" +
                                    employeeDto.getJob().getId(),
                            HttpMethod.PUT,
                            setHeader(employeeDto),
                            EmployeeDto.class)
                    .getBody();
        } catch (HttpClientErrorException e) {
            throw new BadRequest(Objects.requireNonNull(e.getResponseBodyAs(ExceptionResponse.class)).getMessage());
        }
    }

    public void delete(EmployeeDto employeeDto) {
        try {
            restTemplate.exchange(Api.EMPLOYEE + "?id=" + employeeDto.getId(),
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

package md.ceiti.internmanagerapp.back.service;

import com.vaadin.flow.component.UI;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import md.ceiti.internmanagerapp.back.BadRequest;
import md.ceiti.internmanagerapp.back.dto.DepartmentDto;
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
public class DepartmentService {

    private final RestTemplate restTemplate;

    @SneakyThrows
    public List<DepartmentDto> getAll() {
        try {
            return Arrays.stream(Objects.requireNonNull(restTemplate.exchange(Api.DEPARTMENTS,
                                    HttpMethod.GET,
                                    setHeader(),
                                    DepartmentDto[].class)
                            .getBody()))
                    .toList();
        } catch (HttpClientErrorException e) {
            throw new BadRequest(Objects.requireNonNull(e.getResponseBodyAs(ExceptionResponse.class)).getMessage());
        } catch (ResourceAccessException e) {
            UI.getCurrent().navigate(ErrorView.class);
            return new ArrayList<>();
        }
    }

    @SneakyThrows
    public DepartmentDto save(DepartmentDto departmentDto) {
        try {
            return restTemplate.exchange(Api.DEPARTMENTS,
                            HttpMethod.POST,
                            setHeader(departmentDto),
                            DepartmentDto.class)
                    .getBody();
        } catch (HttpClientErrorException e) {
            throw new BadRequest(Objects.requireNonNull(e.getResponseBodyAs(ExceptionResponse.class)).getMessage());
        }
    }

    @SneakyThrows
    public DepartmentDto update(DepartmentDto departmentDto) {
        try {
            return restTemplate.exchange(Api.DEPARTMENT + "?id=" + departmentDto.getId(),
                            HttpMethod.PUT,
                            setHeader(departmentDto),
                            DepartmentDto.class)
                    .getBody();
        } catch (HttpClientErrorException e) {
            throw new BadRequest(Objects.requireNonNull(e.getResponseBodyAs(ExceptionResponse.class)).getMessage());
        }
    }

    @SneakyThrows
    public void delete(DepartmentDto departmentDto) {
        try {
            restTemplate.exchange(Api.DEPARTMENT + "?id=" + departmentDto.getId(),
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

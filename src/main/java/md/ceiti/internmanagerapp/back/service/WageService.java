package md.ceiti.internmanagerapp.back.service;

import com.vaadin.flow.component.UI;
import lombok.RequiredArgsConstructor;
import md.ceiti.internmanagerapp.back.BadRequest;
import md.ceiti.internmanagerapp.back.dto.WageDto;
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
public class WageService {

    private final RestTemplate restTemplate;

    public List<WageDto> getAll() {
        try {
            return Arrays.stream(Objects.requireNonNull(restTemplate.exchange(Api.WAGES,
                                    HttpMethod.GET,
                                    setHeader(),
                                    WageDto[].class)
                            .getBody()))
                    .toList();
        } catch (HttpClientErrorException e) {
            throw new BadRequest(Objects.requireNonNull(e.getResponseBodyAs(ExceptionResponse.class)).getMessage());
        } catch (ResourceAccessException e) {
            UI.getCurrent().navigate(ErrorView.class);
            return new ArrayList<>();
        }
    }

    public WageDto save(WageDto wageDto) {
        try {
            return restTemplate.exchange(Api.WAGE +
                                    "?project=" +
                                    wageDto.getProject().getId() +
                                    "&job=" +
                                    wageDto.getJob().getId(),
                            HttpMethod.POST,
                            setHeader(wageDto),
                            WageDto.class)
                    .getBody();
        } catch (HttpClientErrorException e) {
            throw new BadRequest(Objects.requireNonNull(e.getResponseBodyAs(ExceptionResponse.class)).getMessage());
        }
    }

    public WageDto update(WageDto wageDto) {
        try {
            return restTemplate.exchange(Api.WAGE +
                                    "?id=" +
                                    wageDto.getId() +
                                    "&project=" +
                                    wageDto.getProject().getId() +
                                    "&job=" +
                                    wageDto.getJob().getId(),
                            HttpMethod.PUT,
                            setHeader(wageDto),
                            WageDto.class)
                    .getBody();
        } catch (HttpClientErrorException e) {
            throw new BadRequest(Objects.requireNonNull(e.getResponseBodyAs(ExceptionResponse.class)).getMessage());
        }
    }

    public void delete(WageDto wageDto) {
        try {
            restTemplate.exchange(Api.WAGE + "?id=" + wageDto.getId(),
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

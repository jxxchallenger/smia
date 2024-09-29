package io.jxxchallenger.smia.license.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import io.jxxchallenger.smia.license.model.Organization;

@FeignClient("organization-service")
public interface OrganizationFeignClient {

    @GetMapping(value = "/v1/organization/{organizationId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    Organization getOrganization(@PathVariable("organizationId") String organizationId);
}

package io.jxxchallenger.smia.license.service.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import io.jxxchallenger.smia.license.model.Organization;

@Component
public class OrganizationRestTemplateClient {

    @Autowired
    @Qualifier("customRestTemplate")
    private RestTemplate restTemplate;

    public Organization getOrganization(String organizationId) {
        return restTemplate.getForObject("http://organization-service/v1/organization/{organizationId}",
                Organization.class, organizationId);
    }
}

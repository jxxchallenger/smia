package io.jxxchallenger.smia.license.service.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import io.jxxchallenger.smia.license.model.Organization;

//@Component
public class OrganizationDiscoveryClient {

    @Autowired
    private DiscoveryClient discoveryClient;

    public Organization getOrganization(String organizationId) {
        RestTemplate restTemplate = new RestTemplate();
        List<ServiceInstance> instances = discoveryClient.getInstances("organization-service");
        if (instances.isEmpty()) {
            return null;
        }
        ResponseEntity<Organization> entity = restTemplate.getForEntity(instances.get(0).getUri().toString() + "/v1/organization/{organizationId}",
                Organization.class, organizationId);
        return entity.getBody();
    }
}

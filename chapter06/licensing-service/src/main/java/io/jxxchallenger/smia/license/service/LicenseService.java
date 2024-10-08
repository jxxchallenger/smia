package io.jxxchallenger.smia.license.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import io.jxxchallenger.smia.license.model.License;
import io.jxxchallenger.smia.license.model.Organization;
import io.jxxchallenger.smia.license.model.example.LicenseExample;
import io.jxxchallenger.smia.license.repository.LicenseMapper;
import io.jxxchallenger.smia.license.service.client.OrganizationDiscoveryClient;
import io.jxxchallenger.smia.license.service.client.OrganizationFeignClient;
import io.jxxchallenger.smia.license.service.client.OrganizationRestTemplateClient;

@Service
public class LicenseService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LicenseService.class);

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private LicenseMapper licenseMapper;

    @Autowired
    private OrganizationDiscoveryClient discoveryClient;

    @Autowired
    private OrganizationRestTemplateClient organizationRestTemplateClient;
    
    @Autowired
    private OrganizationFeignClient organizationFeignClient;

    public License getLicense(String licenseId, String organizationId, String clientType) {
        LicenseExample example = new LicenseExample();
        example.createCriteria().andLicenseIdEqualTo(licenseId).andOrganizationIdEqualTo(organizationId);

        List<License> licenses = licenseMapper.selectByExample(example);
        if (licenses.isEmpty()) {
            throw new IllegalArgumentException(String.format(
                    messageSource.getMessage("license.search.error.message", null, null), licenseId, organizationId));
        }
        License license = licenses.get(0);
        Organization organization = retrieveOrganizationInfo(organizationId, clientType);
        if (organization != null) {
            license.setOrganizationName(organization.getName());
            license.setContactEmail(organization.getContactEmail());
            license.setContactName(organization.getContactName());
            license.setContactPhone(organization.getContactPhone());
        }
        return license;
    }

    private Organization retrieveOrganizationInfo(String organizationId, String clientType) {
        switch (clientType) {
        case "feign":
            LOGGER.info("I am using the feign client");
            return organizationFeignClient.getOrganization(organizationId);
        case "rest":
            LOGGER.info("I am using the rest client");
            return organizationRestTemplateClient.getOrganization(organizationId);
        case "discovery":
        default:
            LOGGER.info("I am using the discovery client");
            return discoveryClient.getOrganization(organizationId);
        }
    }

    public License createLicense(License license) {
        licenseMapper.insert(license);
        return license;
    }

    public License updateLicense(License license) {
        licenseMapper.updateByPrimaryKey(license);
        return license;
    }

    public String deleteLicense(String licenseId, String organizationId) {
        LicenseExample example = new LicenseExample();
        example.createCriteria().andLicenseIdEqualTo(licenseId).andOrganizationIdEqualTo(organizationId);
        licenseMapper.deleteByExample(example);
        String responseMessage = null;
        responseMessage = String.format(
                messageSource.getMessage("license.delete.message", null, LocaleContextHolder.getLocale()), licenseId,
                organizationId);
        return responseMessage;
    }
}

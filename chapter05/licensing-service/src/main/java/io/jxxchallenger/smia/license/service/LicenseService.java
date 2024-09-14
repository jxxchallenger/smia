package io.jxxchallenger.smia.license.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import io.jxxchallenger.smia.license.model.License;
import io.jxxchallenger.smia.license.model.example.LicenseExample;
import io.jxxchallenger.smia.license.repository.LicenseMapper;

@Service
public class LicenseService {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private LicenseMapper licenseMapper;

    public License getLicense(String licenseId, String organizationId) {
        LicenseExample example = new LicenseExample();
        example.createCriteria().andLicenseIdEqualTo(licenseId).andOrganizationIdEqualTo(organizationId);
        
        List<License> licenses = licenseMapper.selectByExample(example);
        if (licenses.isEmpty()) {
            throw new IllegalArgumentException(
                    String.format(messageSource.getMessage(
                    "license.search.error.message", null, null),
                    licenseId, organizationId));
        }
        return licenses.get(0);
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

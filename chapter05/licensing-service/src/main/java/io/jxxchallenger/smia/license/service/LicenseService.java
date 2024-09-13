package io.jxxchallenger.smia.license.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import io.jxxchallenger.smia.license.model.License;

@Service
public class LicenseService {

    @Autowired
    private MessageSource messageSource;

    public License getLicense(String licenseId, String organizationId) {
        License license = new License();
        license.setId(new Random().nextInt(1000));
        license.setLicenseId(licenseId);
        license.setOrganizationId(organizationId);
        license.setDescription("Software product");
        license.setProductName("Ostock");
        license.setLicenseType("full");
        return license;
    }

    public String createLicense(License license, String organizationId) {
        String responseMessage = null;
        if (license != null) {
            license.setOrganizationId(organizationId);
            responseMessage = String.format(
                    messageSource.getMessage("license.create.message", null, LocaleContextHolder.getLocale()),
                    license.toString());
        }
        return responseMessage;
    }

    public String updateLicense(License license, String organizationId) {
        String responseMessage = null;
        if (license != null) {
            license.setOrganizationId(organizationId);
            responseMessage = String.format(
                    messageSource.getMessage("license.update.message", null, LocaleContextHolder.getLocale()),
                    license.toString());
        }
        return responseMessage;
    }

    public String deleteLicense(String licenseId, String organizationId) {
        String responseMessage = null;
        responseMessage = String.format(
                messageSource.getMessage("license.delete.message", null, LocaleContextHolder.getLocale()), licenseId,
                organizationId);
        return responseMessage;
    }
}

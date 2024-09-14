package io.jxxchallenger.smia.license.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.jxxchallenger.smia.license.model.License;
import io.jxxchallenger.smia.license.service.LicenseService;

@RestController
@RequestMapping(value = "/v1/organization/{organizationId}/license")
public class LicenseController {

    @Autowired
    private LicenseService licenseService;

    @GetMapping(value = "/{licenseId}")
    public ResponseEntity<License> getLicense(@PathVariable String organizationId, @PathVariable String licenseId) {
        License license = licenseService.getLicense(licenseId, organizationId);
        license.add(linkTo(methodOn(LicenseController.class).getLicense(organizationId, licenseId)).withSelfRel(),
                linkTo(methodOn(LicenseController.class).createLicense(organizationId, license))
                        .withRel("createLicense"),
                linkTo(methodOn(LicenseController.class).updateLicense(organizationId, license))
                        .withRel("updateLicense"),
                linkTo(methodOn(LicenseController.class).deleteLicense(organizationId, licenseId))
                        .withRel("deleteLicense"));
        return ResponseEntity.ok(license);
    }

    @PutMapping
    public ResponseEntity<License> updateLicense(@PathVariable String organizationId, @RequestBody License license) {
        return ResponseEntity.ok(licenseService.updateLicense(license));
    }

    @PostMapping
    public ResponseEntity<License> createLicense(@PathVariable String organizationId, @RequestBody License license) {
        return ResponseEntity.ok(licenseService.createLicense(license));
    }

    @DeleteMapping(value = "/{licenseId}")
    public ResponseEntity<String> deleteLicense(@PathVariable String organizationId, @PathVariable String licenseId) {
        return ResponseEntity.ok(licenseService.deleteLicense(licenseId, organizationId));
    }
}
package io.jxxchallenger.smia.organization.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.jxxchallenger.smia.organization.model.Organization;
import io.jxxchallenger.smia.organization.repository.OrganizationMapper;

@Service
public class OrganizationService {

    @Autowired
    private OrganizationMapper organizationMapper;

    public Organization findById(String organizationId) {
        return organizationMapper.selectByPrimaryKey(organizationId);
    }

    public Organization create(Organization organization) {
        organization.setId(UUID.randomUUID().toString());
        organizationMapper.insert(organization);
        return organization;
    }

    public void update(Organization organization) {
        organizationMapper.updateByPrimaryKey(organization);
    }

    public void delete(Organization organization) {
        organizationMapper.deleteByPrimaryKey(organization.getId());
    }
}
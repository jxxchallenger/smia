package io.jxxchallenger.smia.organization.repository;

import io.jxxchallenger.smia.organization.model.Organization;
import io.jxxchallenger.smia.organization.model.example.OrganizationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrganizationMapper {
    long countByExample(OrganizationExample example);

    int deleteByExample(OrganizationExample example);

    int deleteByPrimaryKey(String id);

    int insert(Organization row);

    int insertSelective(Organization row);

    List<Organization> selectByExample(OrganizationExample example);

    Organization selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("row") Organization row, @Param("example") OrganizationExample example);

    int updateByExample(@Param("row") Organization row, @Param("example") OrganizationExample example);

    int updateByPrimaryKeySelective(Organization row);

    int updateByPrimaryKey(Organization row);
}
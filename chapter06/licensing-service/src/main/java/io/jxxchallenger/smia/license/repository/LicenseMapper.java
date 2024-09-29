package io.jxxchallenger.smia.license.repository;

import io.jxxchallenger.smia.license.model.License;
import io.jxxchallenger.smia.license.model.example.LicenseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LicenseMapper {
    long countByExample(LicenseExample example);

    int deleteByExample(LicenseExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(License row);

    int insertSelective(License row);

    List<License> selectByExample(LicenseExample example);

    License selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("row") License row, @Param("example") LicenseExample example);

    int updateByExample(@Param("row") License row, @Param("example") LicenseExample example);

    int updateByPrimaryKeySelective(License row);

    int updateByPrimaryKey(License row);
}
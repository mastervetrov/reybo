package reybo.crm.settings.company.mapper;

import reybo.crm.settings.company.request.CompanyRequest;
import reybo.crm.settings.company.dto.CompanyDto;
import reybo.crm.common.api.mapper.BaseMapper;
import reybo.crm.settings.company.model.Company;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CompanyMapper extends BaseMapper<CompanyRequest, Company, CompanyDto> {}

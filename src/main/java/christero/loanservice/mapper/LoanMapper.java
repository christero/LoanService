package christero.loanservice.mapper;

import christero.loanservice.dto.LoanApplicationReponseDTO;
import christero.loanservice.dto.LoanApplicationRequestDTO;
import christero.loanservice.dto.LoanApplicationStatusResponseDTO;
import christero.loanservice.model.LoanApplication;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper
public interface LoanMapper {

    LoanMapper INSTANCE = org.mapstruct.factory.Mappers.getMapper(LoanMapper.class);

    @Mapping(target = "applicationID", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "loanAmount", source = "lanebelop")
    @Mapping(target = "purpose", source = "behov")
    @Mapping(target = "borrowers", source = "lanetakere", qualifiedByName = "mapLanetakerDTOListToBorrowerDTOList")
    LoanApplication mapDtoToModel(LoanApplicationRequestDTO dto);

    @Mapping(target = "nationalIdentificationNumber", source = "fnr")
    @Mapping(target = "name", source = "navn")
    @Named("mapLanetakerDTOListToBorrowerDTOList")
    LoanApplication.BorrowerDTO mapLanetakerDTOToBorrowerDTO(LoanApplicationRequestDTO.LanetakerDTO lanetakerDTO);

    @Mapping(target = "soknadsID", source = "applicationID")
    LoanApplicationReponseDTO mapModelToResponseDto(LoanApplication model);

    @Mapping(target = "soknadsID", source = "applicationID")
    LoanApplicationStatusResponseDTO mapModelToStatusResponseDto(LoanApplication model);
}

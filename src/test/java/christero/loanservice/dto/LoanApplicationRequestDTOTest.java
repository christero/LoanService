package christero.loanservice.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.constraints.AssertFalse;
import org.junit.jupiter.api.Test;


import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class LoanApplicationRequestDTOTest {

    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();


    @Test
    void validate_validDTO_shouldReturnValid() {
        LoanApplicationRequestDTO requestDTO = new LoanApplicationRequestDTO();
        LoanApplicationRequestDTO.LanetakerDTO lanetakerDTO = new LoanApplicationRequestDTO.LanetakerDTO();
        requestDTO.setLanebelop(1500);
        requestDTO.setBehov("Boliglån");
        lanetakerDTO.setFnr("123456789");
        lanetakerDTO.setNavn("John Doe");
        requestDTO.setLanetakere(List.of(lanetakerDTO));

        Set<ConstraintViolation<LoanApplicationRequestDTO>> violations = validator.validate(requestDTO);

        assertTrue(violations.isEmpty());
    }

    @Test
    void validate_emptyDTO_shouldReturnInvalid() {
        LoanApplicationRequestDTO requestDTO = new LoanApplicationRequestDTO();
        LoanApplicationRequestDTO.LanetakerDTO lanetakerDTO = new LoanApplicationRequestDTO.LanetakerDTO();

        Set<ConstraintViolation<LoanApplicationRequestDTO>> violations = validator.validate(requestDTO);

        assertFalse(violations.isEmpty());
    }

    @Test
    void validate_invalidLanebelopAndBehov_shouldReturnInvalid() {
        LoanApplicationRequestDTO requestDTO = new LoanApplicationRequestDTO();
        LoanApplicationRequestDTO.LanetakerDTO lanetakerDTO = new LoanApplicationRequestDTO.LanetakerDTO();
        requestDTO.setLanebelop(0);
        requestDTO.setBehov("");
        lanetakerDTO.setFnr("123456789");
        lanetakerDTO.setNavn("John Doe");
        requestDTO.setLanetakere(List.of(lanetakerDTO));

        Set<ConstraintViolation<LoanApplicationRequestDTO>> violations = validator.validate(requestDTO);

        assertFalse(violations.isEmpty());
        assertEquals(2, violations.size());
    }

    @Test
    void validate_invalidLanetakere_shouldReturnInvalid() {
        LoanApplicationRequestDTO requestDTO = new LoanApplicationRequestDTO();
        LoanApplicationRequestDTO.LanetakerDTO lanetakerDTO = new LoanApplicationRequestDTO.LanetakerDTO();
        requestDTO.setLanebelop(1500);
        requestDTO.setBehov("Boliglån");

        Set<ConstraintViolation<LoanApplicationRequestDTO>> violations = validator.validate(requestDTO);

        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
    }
}
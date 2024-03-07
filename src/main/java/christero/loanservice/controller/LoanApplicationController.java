package christero.loanservice.controller;

import christero.loanservice.dto.LoanApplicationReponseDTO;
import christero.loanservice.dto.LoanApplicationRequestDTO;
import christero.loanservice.dto.LoanApplicationStatusResponseDTO;
import christero.loanservice.exception.LoanApplicationNotFoundException;
import christero.loanservice.mapper.LoanMapper;
import christero.loanservice.model.LoanApplication;
import christero.loanservice.service.LoanApplicationService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/application")
public class LoanApplicationController {

    private final LoanApplicationService loanApplicationService;
    private static final Logger logger = LoggerFactory.getLogger(LoanApplicationController.class);

    @Autowired
    public LoanApplicationController(LoanApplicationService loanApplicationService) {
        this.loanApplicationService = loanApplicationService;
    }

    @PostMapping()
    public LoanApplicationReponseDTO applyForLoan(@Valid @RequestBody LoanApplicationRequestDTO dto) {
        logger.info("Loan application received: {}", dto);

        LoanApplication loanApplication = loanApplicationService.applyForLoan(dto);
        return LoanMapper.INSTANCE.mapModelToResponseDto(loanApplication);
    }

    @GetMapping("/status/{applicationId}")
    public ResponseEntity<LoanApplicationStatusResponseDTO> getStatus(@PathVariable String applicationId) {
        try {
            var loanApplication = loanApplicationService.getApplicationById(applicationId);
            logger.info("Loan application status retrieved for ID {}", applicationId);

            return ResponseEntity.ok(LoanMapper.INSTANCE.mapModelToStatusResponseDto(loanApplication));
        } catch (LoanApplicationNotFoundException e) {
            logger.error("Loan application not found for ID {}", applicationId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}

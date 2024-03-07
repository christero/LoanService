package christero.loanservice.service;

import christero.loanservice.dto.LoanApplicationRequestDTO;
import christero.loanservice.enums.LoanApplicationStatus;
import christero.loanservice.exception.LoanApplicationNotFoundException;
import christero.loanservice.mapper.LoanMapper;
import christero.loanservice.model.LoanApplication;
import christero.loanservice.repository.LoanApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class LoanApplicationService {

    private final LoanApplicationRepository loanApplicationRepository;

    @Autowired
    public LoanApplicationService(LoanApplicationRepository loanApplicationRepository) {
        this.loanApplicationRepository = loanApplicationRepository;
    }

    public LoanApplication getApplicationById(String applicationId) {
        var loanApplication = loanApplicationRepository.getLoanApplicationById(applicationId);

        if (loanApplication == null) {
            throw new LoanApplicationNotFoundException("Loan application with ID " + applicationId + " not found");
        }

        return loanApplication;
    }

    public LoanApplication applyForLoan(LoanApplicationRequestDTO loanApplicationRequestDTO) {
        if (loanApplicationRequestDTO == null) {
            throw new IllegalArgumentException("loanApplicationApplyRequestDTO cannot be null");
        }

        LoanApplication loanApplication = LoanMapper.INSTANCE.mapDtoToModel(loanApplicationRequestDTO);
        loanApplication.setApplicationID(generateUniqueId());
        loanApplication.setStatus(LoanApplicationStatus.RECEIVED);

        loanApplicationRepository.saveLoanApplication(loanApplication);

        return loanApplication;
    }

    private String generateUniqueId() {
        return UUID.randomUUID().toString();
    }
}

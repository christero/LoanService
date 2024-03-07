package christero.loanservice.service;

import christero.loanservice.dto.LoanApplicationRequestDTO;
import christero.loanservice.enums.LoanApplicationStatus;
import christero.loanservice.exception.LoanApplicationNotFoundException;
import christero.loanservice.mapper.LoanMapper;
import christero.loanservice.model.LoanApplication;
import christero.loanservice.repository.LoanApplicationRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class LoanApplicationServiceTest {

    @Mock
    private LoanApplicationRepository loanApplicationRepository;

    @Mock
    private LoanMapper loanMapper;

    @InjectMocks
    private LoanApplicationService loanApplicationService;

    @Test
    void getLoanApplicationStatus_ShouldReturnCorrectStatus() {
        String applicationId = "123";
        LoanApplication mockLoanApplication = new LoanApplication();
        mockLoanApplication.setStatus(LoanApplicationStatus.RECEIVED);
        when(loanApplicationRepository.getLoanApplicationById(applicationId)).thenReturn(mockLoanApplication);

        LoanApplication result = loanApplicationService.getApplicationById(applicationId);

        assertEquals(mockLoanApplication, result);
    }

    @Test
    void getLoanApplicationById_NonExistingId_ShouldThrowException() {
        String nonExistingId = "nonExistingId";
        when(loanApplicationRepository.getLoanApplicationById(nonExistingId)).thenReturn(null);

        assertThrows(LoanApplicationNotFoundException.class, () -> loanApplicationService.getApplicationById(nonExistingId));
    }

    @Test
    public void applyForLoan_ShouldSaveApplicationWithGeneratedIdAndReceivedStatus() {
        LoanApplicationRequestDTO requestDTO = new LoanApplicationRequestDTO();

        LoanApplication result = loanApplicationService.applyForLoan(requestDTO);

        assertNotNull(result.getApplicationID());
        assertEquals(LoanApplicationStatus.RECEIVED, result.getStatus());
        verify(loanApplicationRepository).saveLoanApplication(any());
    }
}
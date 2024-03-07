package christero.loanservice.repository;

import christero.loanservice.model.LoanApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class LoanLoanApplicationRepositoryTest {

    @Autowired
    private LoanApplicationRepository loanApplicationRepository;

    @Test
    void getLoanApplicationById_shouldGetLoanApplicationWhenApplicationExists() {
        String applicationId = "test123";
        LoanApplication loanApplication = new LoanApplication();
        loanApplication.setApplicationID(applicationId);
        loanApplicationRepository.saveLoanApplication(loanApplication);

        LoanApplication result = loanApplicationRepository.getLoanApplicationById(applicationId);

        assertNotNull(result);
        assertEquals(applicationId, result.getApplicationID());
    }

    @Test
    void getLoanApplicationById_shouldReturnNullWhenApplicationDoesNotExist() {
        String applicationId = "nonExistingId";

        LoanApplication result = loanApplicationRepository.getLoanApplicationById(applicationId);

        assertNull(result);
    }

    @Test
    void saveLoanApplication_shouldSaveApplicationW() {
        LoanApplication loanApplication = new LoanApplication();
        loanApplication.setApplicationID("test123");
        loanApplication.setLoanAmount(1000.0);

        LoanApplicationRepository loanApplicationRepository = new LoanApplicationRepository();
        loanApplicationRepository.saveLoanApplication(loanApplication);

        assertNotNull(loanApplication.getApplicationID());
    }
}
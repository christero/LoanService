package christero.loanservice.repository;

import christero.loanservice.model.LoanApplication;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class LoanApplicationRepository {

    private final Map<String, LoanApplication> applications = new HashMap<>();

    public LoanApplication getLoanApplicationById(String applicationId) {
        return applications.get(applicationId);
    }

    public void saveLoanApplication(LoanApplication loanApplication) {
        applications.put(loanApplication.getApplicationID(), loanApplication);
    }
}

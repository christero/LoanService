package christero.loanservice.model;

import christero.loanservice.enums.LoanApplicationStatus;
import lombok.Data;

import java.util.List;

@Data
public class LoanApplication {

    private String applicationID;
    private LoanApplicationStatus status;
    private List<BorrowerDTO> borrowers;
    private double loanAmount;
    private String purpose;

    @Data
    public static class BorrowerDTO {
        private String nationalIdentificationNumber;
        private String name;
    }

}

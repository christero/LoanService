package christero.loanservice.dto;

import christero.loanservice.enums.LoanApplicationStatus;
import lombok.Data;

@Data
public class LoanApplicationStatusResponseDTO {

    private String soknadsID;
    private LoanApplicationStatus status;
}

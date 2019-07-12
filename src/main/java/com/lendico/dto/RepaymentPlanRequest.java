package com.lendico.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RepaymentPlanRequest {

    private BigDecimal duration;
    private BigDecimal loanAmount;
    private BigDecimal nominalRate;
    private LocalDateTime startDate;
}

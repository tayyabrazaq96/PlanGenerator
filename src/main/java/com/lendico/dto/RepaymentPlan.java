package com.lendico.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RepaymentPlan {

    private String date;
    private BigDecimal interest;
    private BigDecimal principal;
    private BigDecimal borrowerPaymentAmount;
    private BigDecimal initialOutstandingPrincipal;
    private BigDecimal remainingOutstandingPrincipal;
}

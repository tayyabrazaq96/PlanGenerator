package com.lendico.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class RepaymentIPlanGeneratorServiceImplTest {

    @InjectMocks
    private RepaymentIPlanGeneratorServiceImpl repaymentPlanService;

    private BigDecimal DURATION = BigDecimal.valueOf(24);
    private BigDecimal NOMINAL_RATE = BigDecimal.valueOf(5);
    private BigDecimal INITIAL_OUTSTANDING_PRINCIPAL = BigDecimal.valueOf(5000);

    @Test
    public void calculateInterestTest() {
        BigDecimal expectedResult = BigDecimal.valueOf(20.83);
        assertEquals(expectedResult, repaymentPlanService.calculateInterest(NOMINAL_RATE, INITIAL_OUTSTANDING_PRINCIPAL));
    }

    @Test
    public void calculatePerMonthMarkupTest() {
        BigDecimal expectedResult = BigDecimal.valueOf(0.004166666667);
        assertEquals(expectedResult, repaymentPlanService.calculatePerMonthMarkup(NOMINAL_RATE));
    }

    @Test
    public void calculateAnnuityTest() {
        BigDecimal expectedResult = BigDecimal.valueOf(219.36);
        BigDecimal perMonthMarkup = repaymentPlanService.calculatePerMonthMarkup(NOMINAL_RATE);
        assertEquals(expectedResult, repaymentPlanService.calculateAnnuity(perMonthMarkup, DURATION,
            INITIAL_OUTSTANDING_PRINCIPAL));
    }

    @Test
    public void calculatePrincipalTest() {
        BigDecimal expectedResult = BigDecimal.valueOf(198.53);
        BigDecimal perMonthMarkup = repaymentPlanService.calculatePerMonthMarkup(NOMINAL_RATE);
        BigDecimal annuity = repaymentPlanService.calculateAnnuity(perMonthMarkup, DURATION, INITIAL_OUTSTANDING_PRINCIPAL);
        BigDecimal interest = repaymentPlanService.calculateInterest(NOMINAL_RATE, INITIAL_OUTSTANDING_PRINCIPAL);

        assertEquals(expectedResult, repaymentPlanService.calculatePrincipal(annuity, interest));
    }

    @Test
    public void calculateRemainingPrincipalTest() {
        BigDecimal expectedResult = BigDecimal.valueOf(4801.47);
        BigDecimal perMonthMarkup = repaymentPlanService.calculatePerMonthMarkup(NOMINAL_RATE);
        BigDecimal annuity = repaymentPlanService.calculateAnnuity(perMonthMarkup, DURATION, INITIAL_OUTSTANDING_PRINCIPAL);
        BigDecimal interest = repaymentPlanService.calculateInterest(NOMINAL_RATE, INITIAL_OUTSTANDING_PRINCIPAL);
        BigDecimal principal = repaymentPlanService.calculatePrincipal(annuity, interest);

        assertEquals(expectedResult, repaymentPlanService.calculateRemainingPrincipal(INITIAL_OUTSTANDING_PRINCIPAL, principal));
    }
}

package com.lendico.service.impl;

import com.lendico.dto.RepaymentPlanRequest;
import com.lendico.dto.RepaymentPlan;
import com.lendico.helper.AppHelper;
import com.lendico.service.IPlanGeneratorService;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class RepaymentIPlanGeneratorServiceImpl implements IPlanGeneratorService {

    private final Integer DECIMAL_SCALE = 2;
    private final Integer PRECISION = 12;
    private final BigDecimal DAYS_IN_MONTH = BigDecimal.valueOf(30);
    private final BigDecimal DAYS_IN_YEAR = BigDecimal.valueOf(360);
    private final BigDecimal MONTHS_IN_YEAR = BigDecimal.valueOf(12);

    @Override
    public List<RepaymentPlan> generate(RepaymentPlanRequest request) {

        List<RepaymentPlan> planSchedule = new ArrayList<>();

        BigDecimal perMonthMarkup = calculatePerMonthMarkup(request.getNominalRate());
        BigDecimal initialPrincipal = request.getLoanAmount();
        BigDecimal annuity = calculateAnnuity(perMonthMarkup, request.getDuration(), initialPrincipal);

        LocalDateTime endDateTime = AppHelper.getLastDate(request.getStartDate(), request.getDuration().intValue());

        for(LocalDateTime date = request.getStartDate(); AppHelper.isBeforeDate(date, endDateTime);  date = date.plusMonths(1)) {
            RepaymentPlan plan = calculateRepayPlanOfMonth(date, annuity, request.getNominalRate(), initialPrincipal);
            initialPrincipal = plan.getRemainingOutstandingPrincipal();
            planSchedule.add(plan);
        }
        return planSchedule;
    }

    /**
     * Calculate Plan for One Month
     * @param month Month, Non-null
     * @param annuity Non-null
     * @param nominalRate Non-null
     * @param initialPrincipal Non-null
     * @return
     */
    protected RepaymentPlan calculateRepayPlanOfMonth(LocalDateTime month, BigDecimal annuity, BigDecimal nominalRate, BigDecimal initialPrincipal) {

        BigDecimal interest = calculateInterest(nominalRate, initialPrincipal);
        BigDecimal principal = null;
        if(initialPrincipal.longValue() < annuity.longValue()) {
            principal = initialPrincipal;
        } else {
            principal = calculatePrincipal(annuity, interest);
        }
        BigDecimal remainingPrincipal = calculateRemainingPrincipal(initialPrincipal, principal);

        return RepaymentPlan
            .builder()
            .interest(interest)
            .principal(principal)
            .date(month.toString()+"Z")
            .borrowerPaymentAmount(annuity)
            .remainingOutstandingPrincipal(remainingPrincipal)
            .initialOutstandingPrincipal(initialPrincipal)
            .build();
    }

    /**
     * Calculate Interest on Principal
     * @param nominalRate Non-null field
     * @param principal Non-null field
     * @return Interest amount on principal
     */
    protected BigDecimal calculateInterest(@NotNull BigDecimal nominalRate, @NotEmpty BigDecimal principal) {

        return principal.multiply(DAYS_IN_MONTH)
            .multiply(nominalRate)
            .divide(DAYS_IN_YEAR, DECIMAL_SCALE, RoundingMode.FLOOR)
            .divide(BigDecimal.valueOf(100), DECIMAL_SCALE, RoundingMode.FLOOR);
    }

    /**
     * Calculate Per Month Markup from nominalRate
     * @param nominalRate Non-null field
     * @return Per Month Markup Rate
     */
    protected BigDecimal calculatePerMonthMarkup(@NotNull BigDecimal nominalRate) {
        return nominalRate.divide(MONTHS_IN_YEAR, PRECISION, RoundingMode.FLOOR)
            .divide(BigDecimal.valueOf(100), PRECISION, RoundingMode.CEILING);
    }

    /**
     * Calculate Annuity
     * @param perMonthMarkup Non-null field
     * @param duration Non-null field
     * @param loanAmount Non-null field
     * @return Annuity per month
     */
    protected BigDecimal calculateAnnuity(@NotNull BigDecimal perMonthMarkup,
        @NotNull BigDecimal duration, @NotNull BigDecimal loanAmount) {

        BigDecimal temp = BigDecimal.ONE.add(perMonthMarkup);
        BigDecimal numerator = temp.pow(duration.intValue());
        BigDecimal denominator = numerator.subtract(BigDecimal.ONE);
        return perMonthMarkup
            .multiply(loanAmount)
            .multiply(numerator)
            .divide(denominator, DECIMAL_SCALE, RoundingMode.CEILING);
    }

    /**
     * Calculate Principal
     * @param annuity Non-null field
     * @param interest Non-null field
     * @return Principal
     */
    protected BigDecimal calculatePrincipal(@NotNull BigDecimal annuity, @NotNull BigDecimal interest) {
        return annuity.subtract(interest);
    }

    /**
     * Calculate Remaining Principal
     * @param initialPrincipal Non-null field
     * @param monthlyPrincipal Non-null field
     * @return Remaining Principal
     */
    protected BigDecimal calculateRemainingPrincipal(@NotNull BigDecimal initialPrincipal,
        @NotNull BigDecimal monthlyPrincipal) {

        return initialPrincipal.subtract(monthlyPrincipal);
    }
}

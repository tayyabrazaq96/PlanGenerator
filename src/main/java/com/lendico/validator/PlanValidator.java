package com.lendico.validator;

import com.lendico.dto.RepaymentPlanRequest;
import com.lendico.exception.error.PlanError;
import org.springframework.stereotype.Component;

import static com.lendico.helper.AppHelper.checkLessThanEqualZero;
import static com.lendico.helper.AppHelper.checkNull;
import static com.lendico.helper.AppHelper.checkPastDate;

@Component
public class PlanValidator implements IAppValidator<RepaymentPlanRequest> {

    @Override
    public void validate(RepaymentPlanRequest request) {
        checkNull(request, PlanError.NULL_PAYLOAD);
        checkNull(request.getDuration(), PlanError.NULL_DURATION);
        checkLessThanEqualZero(request.getDuration(), PlanError.INVALID_DURATION_VALUE);
        checkNull(request.getLoanAmount(), PlanError.NULL_LOAN_AMOUNT);
        checkLessThanEqualZero(request.getLoanAmount(), PlanError.INVALID_LOAN_AMOUNT);
        checkNull(request.getNominalRate(), PlanError.NULL_NOMINAL_RATE);
        checkLessThanEqualZero(request.getNominalRate(), PlanError.INVALID_NOMINAL_RATE);
        checkNull(request.getStartDate(), PlanError.NULL_START_DATE);
        checkPastDate(request.getStartDate(), PlanError.INVALID_INVALID_START);
    }
}

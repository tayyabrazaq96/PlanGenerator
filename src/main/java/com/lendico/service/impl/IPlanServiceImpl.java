package com.lendico.service.impl;

import com.lendico.dto.RepaymentPlanRequest;
import com.lendico.dto.RepaymentPlan;
import com.lendico.service.IPlanService;
import com.lendico.service.IPlanGeneratorService;
import com.lendico.validator.IAppValidator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IPlanServiceImpl implements IPlanService {

    private IAppValidator planValidator;
    private IPlanGeneratorService repaymentPlanService;

    public IPlanServiceImpl(final IAppValidator planValidator, final IPlanGeneratorService repaymentPlanService) {
        this.planValidator = planValidator;
        this.repaymentPlanService = repaymentPlanService;
    }

    @Override
    public List<RepaymentPlan> generate(RepaymentPlanRequest request) {
        planValidator.validate(request);
        return repaymentPlanService.generate(request);
    }
}

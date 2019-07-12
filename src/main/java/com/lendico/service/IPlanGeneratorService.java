package com.lendico.service;

import com.lendico.dto.RepaymentPlanRequest;
import com.lendico.dto.RepaymentPlan;

import java.util.List;

public interface IPlanGeneratorService {

    List<RepaymentPlan> generate(RepaymentPlanRequest payload);
}

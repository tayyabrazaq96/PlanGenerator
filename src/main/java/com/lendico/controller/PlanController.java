package com.lendico.controller;

import com.lendico.dto.RepaymentPlanRequest;
import com.lendico.dto.RepaymentPlan;
import com.lendico.service.IPlanService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/api/plans")
public class PlanController {

    private IPlanService planService;

    public PlanController(final IPlanService planService) {
        this.planService = planService;
    }

    @PostMapping
    public List<RepaymentPlan> generatePlan(@RequestBody RepaymentPlanRequest requst) {
        return planService.generate(requst);
    }

}

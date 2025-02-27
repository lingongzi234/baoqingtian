package com.tjpu22t01.respondent.service.impl;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import cn.dev33.satoken.util.SaResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tjpu22t01.common.entity.ApplicantInfo;
import com.tjpu22t01.common.entity.Cases;
import com.tjpu22t01.common.entity.Mediators;
import com.tjpu22t01.respondent.mapper.ApplicantMapper;
import com.tjpu22t01.respondent.mapper.CaseMapper;
import com.tjpu22t01.respondent.mapper.MediatorMapper;
import com.tjpu22t01.respondent.service.StatisticsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

@Service
@Slf4j
public class StatisticsServiceImpl implements StatisticsService {
    @Resource
    private CaseMapper caseMapper;
    @Resource
    private MediatorMapper mediatorsMapper;
    @Resource
    private ApplicantMapper applicantsMapper;

    @Override
    public SaResult getmonth(int year) {
        log.info("get month");
        QueryWrapper<Cases> caseWrapper = new QueryWrapper<>();
        caseWrapper.select("EXTRACT(MONTH FROM created_at) AS month",
                        "COUNT(*) AS case_count")
                .apply("EXTRACT(YEAR FROM created_at) = {0}", year)
                .groupBy("EXTRACT(MONTH FROM created_at)")
                .orderByAsc("month");
        List<Map<String, Object>> result = caseMapper.selectMaps(caseWrapper);
        return SaResult.ok().setData(result);
    }

    @Override
    public SaResult getstatus() {
        log.info("get status");
        QueryWrapper<Cases> caseWrapper = new QueryWrapper<>();
        caseWrapper.select("status", "COUNT(*) AS case_count")// 传入年份参数
                .groupBy("status")
                .orderByAsc("status");
        List<Map<String, Object>> result = caseMapper.selectMaps(caseWrapper);
        return SaResult.ok().setData(result);
    }

    @Override
    public SaResult getinformations(int type) {
        log.info("get informations");
        if (type == 1) {
            QueryWrapper<Cases> caseWrapper = new QueryWrapper<>();
            caseWrapper.select("SUM(total_amount) AS total_amount");
            List<Object> result = caseMapper.selectObjs(caseWrapper);
                BigDecimal totalAmount = new BigDecimal(result.get(0).toString());
                return SaResult.ok().setData(totalAmount);
        }
        if(type == 2){
            QueryWrapper<Cases> caseWrapper = new QueryWrapper<>();
            caseWrapper.select("COUNT(*) AS case_count")
                    .eq("status", 3);
            List<Object> result = caseMapper.selectObjs(caseWrapper);
            return SaResult.ok().setData(result);

        }
        if(type == 3){
            QueryWrapper<Mediators> MediatorWrapper = new QueryWrapper<>();
            MediatorWrapper.select(" COUNT(*) AS mediator_count");
            List<Object> result = mediatorsMapper.selectObjs(MediatorWrapper);
            return SaResult.ok().setData(result);
        }
        if(type == 4){
            QueryWrapper<ApplicantInfo> ApplicantWrapper = new QueryWrapper<>();
            ApplicantWrapper.select("COUNT(*) AS applicant_count");
            List<Object> result = applicantsMapper.selectObjs(ApplicantWrapper);
            return SaResult.ok().setData(result);
        }
        return SaResult.ok();
    }
}

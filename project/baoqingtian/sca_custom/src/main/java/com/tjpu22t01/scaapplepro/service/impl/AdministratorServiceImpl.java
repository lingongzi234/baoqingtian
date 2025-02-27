package com.tjpu22t01.scaapplepro.service.impl;

import cn.dev33.satoken.util.SaResult;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tjpu22t01.common.entity.Cases;
import com.tjpu22t01.common.entity.Mediators;
import com.tjpu22t01.scaapplepro.mapper.AdministratorMapper;
import com.tjpu22t01.scaapplepro.mapper.CasesMapper;
import com.tjpu22t01.scaapplepro.mapper.MediatorsMapper;
import com.tjpu22t01.scaapplepro.pojo.Condition;
import com.tjpu22t01.scaapplepro.service.AdministratorService;
import com.tjpu22t01.scaapplepro.tools.AddCondition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class AdministratorServiceImpl implements AdministratorService {

    @Resource
    AdministratorMapper administratorMapper;

    @Resource
    MediatorsMapper mediatorsMapper;

    @Resource
    CasesMapper casesMapper;

    @Override
    public SaResult addMediator(Mediators mediator) {

        log.info("adding mediator {}", mediator.getName());

        Wrapper<Mediators> wrapper = new QueryWrapper<Mediators>().eq("id_card_number", mediator.getIdCardNumber());
        Mediators mediators = administratorMapper.selectOne(wrapper);
        log.warn(String.valueOf(mediators));
        if(mediators != null){
            return SaResult.code(505).setMsg("mediator have created");
        }

        try {
            int tip = administratorMapper.insert(mediator);
            mediators = administratorMapper.selectOne(wrapper);
            return SaResult.code(200).setData(mediators);
        }catch (Exception e){
            log.error(e.getMessage());
            return SaResult.code(505).setMsg("insert mediator failed");
        }
    }

    @Override
    public SaResult caseConditionSelect(Condition condition) {

        Page<Cases> page = new Page<>(condition.getCurrent(), condition.getSize());

        QueryWrapper<Cases> wrapper = new QueryWrapper<Cases>().eq("status", 1);
        AddCondition.addCondition(wrapper, condition);

        try{

            Page<Cases> cases = casesMapper.selectPage(page, wrapper);
            return SaResult.ok().setData(cases).setMsg("ok");
        }catch (Exception e){
            log.error(e.getMessage());
            return SaResult.error().setMsg("select failed");
        }
    }

    @Override
    public SaResult selectMediator(Condition condition) {

        QueryWrapper<Mediators> wrapper = new QueryWrapper<Mediators>();
        AddCondition.addCondition(wrapper,condition);
        try{
            List<Mediators> mediators = mediatorsMapper.selectList(wrapper);
            return SaResult.ok().setData(mediators).setMsg("ok");
        }catch (Exception e){
            log.error(e.getMessage());
            return SaResult.error().setMsg("select failed");
        }
    }

    @Override
    public SaResult accessCase(String caseId) {


        try{
            Wrapper<Cases> wrapper = new UpdateWrapper<Cases>().eq("case_id", caseId);
            Cases cases = casesMapper.selectOne(wrapper);
            cases.setStatus(2);
            int tip = casesMapper.update(cases, wrapper);
            return SaResult.code(200).setData(cases).setMsg("ok");
        }catch (Exception e){
            log.error(e.getMessage());
            return SaResult.error().setMsg("access case failed");
        }
    }

    @Override
    public SaResult caseGetMediator(String caseId, String mediatorId) {

        try{
            administratorMapper.update(caseId, mediatorId);
            return SaResult.ok();
        }catch (Exception e){
            log.error(e.getMessage());
            return SaResult.error().setMsg("update failed");
        }
    }
}

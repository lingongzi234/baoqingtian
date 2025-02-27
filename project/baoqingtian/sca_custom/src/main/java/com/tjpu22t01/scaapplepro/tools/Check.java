package com.tjpu22t01.scaapplepro.tools;

import cn.dev33.satoken.util.SaResult;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.tjpu22t01.common.entity.Cases;
import com.tjpu22t01.common.entity.Mediators;
import com.tjpu22t01.scaapplepro.mapper.CasesMapper;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;

@Slf4j
public final class Check {

    public static boolean checkMediator(Mediators mediators) {

        return mediators != null &&
                mediators.getEmail().length() >= 3 &&
                mediators.getPhoneNumber().length() == 11 &&
                !mediators.getName().isEmpty() &&
                mediators.getBornTime() != null &&
                mediators.getIdCardNumber().length() == 18 &&
                !mediators.getEducationLevel().isEmpty() &&
                mediators.getExperienceYears() >= 0 &&
                !mediators.getEmployer().isEmpty() &&
                mediators.getOrganizationId() > 0 &&
                !mediators.getWorkExperience().isEmpty() &&
                !mediators.getQualifications().isEmpty() &&
                !mediators.getAreasOfExpertise().isEmpty();
    }

    public static SaResult checkPhone(String phone){
        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
        try{
            Phonenumber.PhoneNumber phoneNumber = phoneUtil.parse(phone,"CN");
            if(!(phoneUtil.isValidNumber(phoneNumber))){
                return SaResult.code(501).setMsg("invalid phone number").setData(false);
            }
        }catch (Exception e){
            log.error(e.getMessage());
            return SaResult.error(e.getCause().getMessage());
        }
        return null;
    }
}

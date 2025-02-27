package com.tjpu22t01.respondent.pojo;

import com.tjpu22t01.common.entity.ApplicantInfo;
import com.tjpu22t01.common.entity.Mediators;
import com.tjpu22t01.common.entity.RespondentInfo;
import com.tjpu22t01.common.entity.User;
import lombok.Data;

@Data
public class Users {
     User user;
     ApplicantInfo applicantInfo;
     Mediators mediators;
     RespondentInfo respondentInfo;
     int type;
}

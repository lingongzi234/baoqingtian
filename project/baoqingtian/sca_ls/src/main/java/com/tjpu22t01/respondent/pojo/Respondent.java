package com.tjpu22t01.respondent.pojo;

import com.tjpu22t01.common.entity.RespondentInfo;
import lombok.Data;

@Data
public class Respondent {
    String phone_number;
    RespondentInfo respondent_info;
    long case_id;
    int user_id;
    String id_card;
    String time;
}

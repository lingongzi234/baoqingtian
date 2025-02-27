package com.tjpu22t01.scaapplepro.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tjpu22t01.common.entity.ApplicantInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ApplicantMapper extends BaseMapper<ApplicantInfo> {

    @Update("UPDATE applicant_info set id_photo_address = #{fileName} WHERE user_id = #{userId}")
    public int updateIdPhotoAddress(int userId, String fileName);

}

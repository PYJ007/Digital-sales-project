package com.dajun.springbootplatform.repository;

import com.dajun.springbootplatform.entities.Specialist;
import com.dajun.springbootplatform.entities.other.specialistInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface certificationRepository {

    @Select("select * from specialist where specialist_phone like '%_forbid'")
    List<Specialist> findAllNotPassSpecialistAndPic();

    @Select("select a.*,pic from specialist a,specialistcertification b where a.specialist_id=b.id and specialist_id=#{id}")
    specialistInfo findNotPassSpecialist(@Param("id") int id);

    @Update("update specialist set specialist_phone=#{phone} where specialist_id=#{id}")
    void passSpecialist(@Param("phone") String phone,@Param("id") Integer id);

    @Insert("insert into specialistcertification(id,pic) values(#{id},#{pic})")
    void insertCertification(@Param("id") Integer id,@Param("pic") byte[] pic);
}

package com.dajun.springbootplatform.repository;

import com.dajun.springbootplatform.entities.User;
import com.dajun.springbootplatform.entities.field;
import com.dajun.springbootplatform.entities.other.phoneAndCropsName;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public interface fieldRepository {
    //通过手机号把用户全部信息都查询出来
    User showField(String phone);
    //给入面积，SQL语句中累加，注意原本面积应该不能为null
    //这里的输入格式要求是把电话号码和田的亩数都赋值，其他无所谓，用不到
    void updateAcres(User user);
    //查询所有用户的田的信息
    List<field> findFieldByTel(String tel);
    //通过手机号和作物名称删除作物
    void deleteFieldByTelAndName(phoneAndCropsName phoneAndCropsName);
    //查询要删除的田的信息的亩数 -> 为了防止有人同种作物插好几个，这个是返回总数sum()
    int selectFieldAcresByTelAndName(phoneAndCropsName phoneAndCropsName);

    List<field> findMyField(String phone);

    int findFieldTime(Integer fieldId);

    void addImportentRecord(@Param("fieldId") Integer fieldId, @Param("recommendId") Integer recommendId, @Param("groupId") String groupId, @Param("seedId") Integer seedId, @Param("date") Date date);

    void updateFieldCrop(@Param("seedName") String seedName,@Param("seedType") String seedType,@Param("fieldId") Integer fieldId);
}

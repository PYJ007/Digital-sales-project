package com.dajun.springbootplatform.repository;

import com.dajun.springbootplatform.entities.other.policyAndLink;
import com.dajun.springbootplatform.entities.policy;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface policyRepository {
    //查询所有政策
    List<policy> selectAllPolicy();
    //删除一个政策
    void deletePolicyById(int id);
    //查询最新的三个政策
    List<policyAndLink> selectThreePolicy();
    //保存政策在那个recommendRepository里，我懒得改了
    //查询一个政策
    policy findPolicyById(int id);

    @Delete("delete from policylink where policy_id=#{id}")
    void deletePolicyLLink(@Param("id") int id);

    @Select(" select a.*,link from policy a,policylink b where a.policy_id=b.policy_id and b.pass=0")
    List<policyAndLink> findAllNotPass();

    @Select("select a.*,link from policy a,policylink b where a.policy_id=b.policy_id and a.policy_id=#{id}")
    policyAndLink findPolicyAndLink(@Param("id") int id);

    @Update("update policylink set pass=1,policy_id=#{newId} where policy_id=#{id}")
    void updateLink(@Param("id") int id,@Param("newId") String newId);

    @Update("update policy set policy_id=#{newId} where policy_id=#{id}")
    void updatePolicyId(@Param("id") int id,@Param("newId") String newId);
}

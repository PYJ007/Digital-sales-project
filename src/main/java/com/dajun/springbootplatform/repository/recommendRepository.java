package com.dajun.springbootplatform.repository;

import com.dajun.springbootplatform.entities.other.policyfile;
import com.dajun.springbootplatform.entities.other.recommendIdAndTel;
import com.dajun.springbootplatform.entities.other.sowMethodAndSeedId;
import com.dajun.springbootplatform.entities.other.specialistAndSeedId;
import com.dajun.springbootplatform.entities.policy;
import com.dajun.springbootplatform.entities.recommend;
import com.dajun.springbootplatform.entities.recommendElements;
import com.dajun.springbootplatform.entities.recommendPesticide;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface recommendRepository {
    //这里是查询五个推荐
    List<recommend> findBySeedIdAndSpecialistIdLimit(specialistAndSeedId specialistAndSeedId);
    //删除ID
    void deleteByRecommendId(int recommend_id);
    //删除juge表
    @Delete("delete from recommendjude where recommend_id=#{recommend_id}")
    void deleteJudgeById(@Param("recommend_id") int recommend_id);
    //插入推荐
    void insertBySeedId(recommend recommend);
    //插入待审核的推荐表
    void insertJudgeBySeedId(recommend recommend);
    //通过专家的id去推荐表获取最新的推进信息的id
    int findRecommendIdBySpecialistId(int specialistId);
    void saveElements(recommendElements recommendElements);
    void savePesticide(recommendPesticide recommendPesticide);
    List<recommend> findBySeedIdAndSpecialistId(specialistAndSeedId specialistAndSeedId);
    //通过id获取推荐的化肥元素
    recommendElements findElementsById(int id);
    //通过ID获取推荐的预防疾病
    recommendPesticide findPesticideById(int id);
    //通过用户作物id、播种方式（见实体类）  SQL语句中和时间比较筛选信息,按时间降序
    //播种方式不筛选了，我去掉了，但是实体类保留了，只是SQL语句中删除了
    //我想起来用户可能不止一种作物，所以通过单一的作物ID是不行的，所以用多表查询
    List<recommend> findRecommendByCropsAndTime(@Param("user_tel") String user_tel,@Param("fieldId") String fieldId);
    //因为我们的推荐表那个标志是否已读一旦被操作了，就会导致其他人都看不到
    //所以额外加一个表，用来标志用户是否已读，只记录读，不记录没有读；当然推荐表中所有是否已读都要是否( 0 )
    //需要给出用户手机号  和  推荐ID，读取的是是否读了(布尔值，只要存在里面就说明读了->但是好像有点问题喔，返回的不是布尔值)
    Integer recommendReadOrNot(@Param("recommendId") Integer recommendId,@Param("groupId") String groupId);
    //更新用户是否已读
    void insertRecommendRead(recommendIdAndTel recommendIdAndTel);
    //插入一个政策
    void savePolicy(policy policy);
    //插入一个政策文件信息
    void savePolicyFile(String location);
    //通过推荐的ID获取推荐的内容
    recommend findRecommendById(int id);
    //查询所有的文件信息
    List<String> findAllFiles();
    @Insert("insert into policylink(policy_id,link,pass) values(#{id},#{link},0)")
    void insertPolicyLink(@Param("id") String id,@Param("link") String link);

    @Update("update policy set policy_id=#{newId} where policy_id=#{id}")
    void updatePolicyId(@Param("newId") String newId,@Param("id") int id);

}

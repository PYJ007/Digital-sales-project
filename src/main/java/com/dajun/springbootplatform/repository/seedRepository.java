package com.dajun.springbootplatform.repository;

import com.dajun.springbootplatform.entities.Seed;
import com.dajun.springbootplatform.entities.browsingHistory;
import com.dajun.springbootplatform.entities.valuate.candidateSeed;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface seedRepository {
    int findSeedIdByName(String name);

    //存入种子
    void saveSeedInfo(Seed seed);

    @Update("update seed set seed_id=#{newId} where seed_id=#{id}")
    void updateSeedId(@Param("id") int id,@Param("newId") String newId);

    //查询出所有种子信息
    List<Seed> findAllSeed();

    //删除种子信息
    void deleteSeedById(int seedId);

    //通过ID返回种子名称
    String findSeedNameById(int id);

    //通过类型名称返回具体作物名称
    List<String> findDetailNameByTypeName(String typeName);

    //查询最新的三个种子
    List<Seed> findThreeSeed();

    //查询推荐的农作物
    List<Seed> findSeedByState();

    //根据种子ID获取种子信息
    Seed findSeedById(int id);

    //提供者的电话找到该提供者对应的所有种子
    List<Seed> findSeedByPhone(String phone);

    //将种子自增范围改回来
    @Update("alter table seed auto_increment= #{id}")
    void alterTableInc(@Param("id") int id);

    //根据种子名称获取单价
    double getPrice(String name);

    //根据买家的姓名选出该买家购物车中的种子
    List<Seed> findSeedFromCar(String userName);

    //根据买家的电话在浏览记录表中推荐某一类型的种子
    List<Seed> findSeedFromBrowsingHistory(String user_phone);

    //根据用户电话查询浏览记录
    List<browsingHistory> findBrowsingHistory(String user_phone);

    //根据买家的姓名选出该买家购物车中的种子,同时实现分页
    List<Seed> findSeedFromCar2(@Param("userName") String userName,@Param("star") int star,@Param("count") int count);

    //得到所有的种子类别
    List<String> getType();

    //根据类型返回该类型的所有种子
    List<Seed> findSeedByType(String type);

    //根据种子类型随机选取指定个数的种子
    List<Seed> findRandSeedByType(@Param("type") String type);

    List<Seed> findSeedByType2(@Param("type") String type,@Param("star") int star,@Param("count") int count);

    //收藏后向购物车添加
    void addShopCar(@Param("userName") String name,@Param("id") int id);

    //删除购物车中的类容
    void deleteShopCar(@Param("userName") String name,@Param("id") int id);

    //根据种子名字找到种子的图片的名称
    String findPhotoBySeedName(String seedname);

    //获取对应用户的购物车的商品数量
    int findCount(String userName);

    int findCountByType(String type);

    //保存未审核的种子
    void saveCandidate(candidateSeed seed);

    void saveCandidateFileById(@Param("file") byte[] file,@Param("seed_id") Integer seed_id);

    //获取正在审核的种子
    List<candidateSeed> getCandidate1(String phone);

    //获取未通过审核的种子
    List<candidateSeed> getCandidate2(String phone);

    List<candidateSeed> getCandidate3();

    List<candidateSeed> getCandidate4();

    candidateSeed fondCSeedById(int id);

    void updateCSeed(@Param("id") int id,@Param("reason") String reason);

    void changeCSeedToSeed(candidateSeed cs);

    void deleteCSeed(int id);

    List<candidateSeed> getCandidateByManufacture(String userName);

    List<candidateSeed> getRejectCandidateByManufacture(String userName);
    String findTypeBySeedName(String seedName);
}

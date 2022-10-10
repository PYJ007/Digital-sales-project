package com.dajun.springbootplatform.entities;
import lombok.Data;
import java.util.Date;

@Data
public class recommend {
//    推荐ID
    private int recommend_id;
//    专家ID
    private int specialist_id;
//    开始时间
    private String recommend_time;
//    结束时间
    private String recommend_endtime;
//    种子ID
    private Integer seed_id;
//    推荐的类型（肥、药、水、其他）
    private int recommend_type;
//    推荐是否被用户读取
    private int recommend_readed;
//    详细内容
    private String detail;
//    注意事项
    private String notice;
//    植物的生长阶段
    private String stage;
//    播种方式
    private int sowmethod;
//    用户执行该推荐的时间
    private Date excute_time;
}

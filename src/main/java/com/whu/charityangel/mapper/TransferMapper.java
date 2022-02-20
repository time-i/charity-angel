package com.whu.charityangel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.whu.charityangel.model.entity.Transfer;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TransferMapper extends BaseMapper<Transfer> {

    //插入一条转账记录到transfer表单
    @Insert("insert into transfer(`money`,`from`,`to`,`date_time`) values(#{money},#{from},#{to},#{dateTime})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    int insertTransfer(Transfer info);

    //目标账户充值
    int updateSource(Transfer info);

    //捐款账户扣款
    int updateDestination(Transfer info);

    //查询转账记录
    //@Select("select (`to`,`money`,`date_time`) from transfer where `from`=#{from}")
    List<Transfer> queryMyRecord(Long myid);

    @Select("select * from transfer where `to`=${accountId};")
    List<Transfer> selectTransferByTo(Long accountId);

    @Select("select * from transfer where `from` = ${accountId}")
    List<Transfer> selectTransferByFrom(Long accountId);
}

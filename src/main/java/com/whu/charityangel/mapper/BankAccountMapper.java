package com.whu.charityangel.mapper;


import com.alipay.mychain.sdk.vm.abi.datatype.Int;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.whu.charityangel.model.entity.BankAccount;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.*;

@Mapper
public interface BankAccountMapper extends BaseMapper<BankAccount> {
    @Insert("insert into account(`money`, `login_id`, `id_kind`) values(#{money},#{loginId},#{idKind})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    int insertAccount(BankAccount info);

    @Select("select * from account where login_id = #{loginId} and id_kind = #{idKind}")
    BankAccount  queryBankAccountByLoginId(Long loginId, Integer idKind);

    @Select("select * from account where id = #{accountId}")
    BankAccount  queryLoginIdByBankAccount(Long accountId);

    //充值
    @Update("update account set money = money+#{amount} where id = #{accountId}")
    int IssueMoney(Integer amount, Long accountId);
}


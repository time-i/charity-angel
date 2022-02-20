package com.whu.charityangel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.whu.charityangel.model.entity.Program;
import com.whu.charityangel.model.entity.Transfer;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ProgramMapper extends BaseMapper<Program>{
    //插入一条数据到数据库中
    @Insert("insert into program(`name`,`user_id`,`desc`,`required_money`,`start`,`ddl`) values(#{name},#{userId},#{desc},#{requiredMoney},#{start},#{ddl})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    void insertProgram(Program program);

    @Select("select * from program")
    List<Program> selectAllInfo();

    @Select("select * from program where id =#{id}")
    Program selectAllInfoById(Long id);

    @Select("select * from program where name =#{name}")
    Program selectAllInfoByName(String name);

    @Select("select * from program where contract_name =#{contractName}")
    Program selectAllInfoByContractName(String contractName);

    @Select("select * from program where user_id =#{userId}")
    List<Program> selectAllInfoByUserId(Long userId);

    @Select("select money from account where login_id = #{id} and id_kind = 1")
    Integer selectProgramMoneyById(Long id);

    @Update("update program set contract_name = #{contractName} where id=#{id}")
    void updateProgramContractName(Program program);

    @Update("update program set contract_addr = #{contractAddr} where id=#{id}")
    void updateProgramContractAddr(Program program);
}

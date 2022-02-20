package com.whu.charityangel.chain;

import com.alipay.mychain.sdk.api.utils.Utils;
import com.alipay.mychain.sdk.domain.account.Identity;

import java.io.IOException;

public class ChainFunction{
     /**
     * 部署合约,使用预制的管理员名字,口令,密钥
     * 返回<string>合约名字
     * 
     *       deployInit(<Integer>,<String>,<String>,<String>,<Integer>); 
     *
     *       参数对应合约中的属性
     *           uint pragmaId;      //数据库中该募集捐方的的顺次编号
     *           string pragmaName;  //募集方名字
     *           string deadLine;    //募集截止日期
     *           string transTime;   //募集建立日期
     *           uint amount;        //募集所需资金数目
     */
	public String deployInit(Long _pragmaId, String _pragmaName, String _deadLine, String _transTime, Integer _amount) throws IOException {
		Chaintest chainHandle = new Chaintest();
		chainHandle.initMychainEnv();
		chainHandle.initSdk();

		String returnValue;
		returnValue = chainHandle.deployContract(_pragmaId,_pragmaName,_deadLine,_transTime, _amount);
		return returnValue;
	}
    /**
     *
     *功能:  返回值<Boolean>表示添加一条转账记录成功与否

     *       addTrans(<String>,<Integer>,<String>,<String>,<Integer>); 
     *
     *       向合约中添加一条转账信息
     *       对应合约参数含义
     *           String _contractName //合约名字
     *           uint _id;           //数据库中该转账的顺次编号
     *           string _from;       //发送
     *           string _to;         //目标
     *           uint amount;        //转账资金数目
     *
     *
     *
     */
	public Boolean addTrans(String _contractName,Long _id, String _from, String _to, Integer _amount) throws IOException{
		Chaintest chainHandle = new Chaintest();
		chainHandle.callContractId = _contractName;
		chainHandle.initMychainEnv();
		chainHandle.initSdk();

		Boolean returnValue;
		returnValue = chainHandle.addTransactions(_id,_from,_to, _amount);
		return returnValue;
	}
     /**功能:  返回值<String>该合约中所有起点为_from的记录

     *       getInfoByFrom(<String>,<String>); 
     *
     *       查询合约中所有起点为_from的记录
     *       对应合约参数含义
     *           _contractName           // 合约名
     *           string _from;           //起点
     *
     *
     */
	public String getInfoByFrom(String _contractName,String _from) throws IOException{
		Chaintest chainHandle = new Chaintest();
		chainHandle.callContractId = _contractName;
		chainHandle.initMychainEnv();
		chainHandle.initSdk();

		String returnValue;
		returnValue = chainHandle.showInfoByFrom(_from);

		return returnValue;
	}
    /**
     *
     *功能:  返回值<String>该合约中所有目标为_to的记录

     *       getInfoByTo(<String>,<String>); 
     *
     *       查询合约中所有目标为_to的记录
     *       对应合约参数含义
     *           _contractName         //合约名
     *           string _to;           //目标
     *
     *
     */
	public String getInfoByTo(String _contractName,String _to) throws IOException{
		Chaintest chainHandle = new Chaintest();
		chainHandle.callContractId = _contractName;
		chainHandle.initMychainEnv();
		chainHandle.initSdk();

		String returnValue;
		returnValue = chainHandle.showInfoByTo(_to);

		return returnValue;
	}
     /**
     *
     *功能:  返回值<String>合约设置好的属性

     *       attribute(<String>); 
     *
     *       获取合约名字返回基本属性
             _contractName = <String> //例如"wuda1626426288661"
     *
     *
     */
	public String attribute(String _contractName) throws IOException{
		Chaintest chainHandle = new Chaintest();
		chainHandle.callContractId = _contractName;
		chainHandle.initMychainEnv();
		chainHandle.initSdk();

		String returnValue;
		returnValue = chainHandle.getAttribute();

		return returnValue;
	}
    /**
     * 返回合约中目标为_to的数量
     * (<合约名>,<目标to>)
     */
	public long countTo(String _contractName,String _to) throws IOException{
		Chaintest chainHandle = new Chaintest();
		chainHandle.callContractId = _contractName;
		chainHandle.initMychainEnv();
		chainHandle.initSdk();

		long returnValue;
		returnValue = chainHandle.selectAllAmountByTo(_to);

		return returnValue;
	}
    /**
     * 返回合约中起点为_from的数量
     * (<合约名>,<起点>)
     */
	public long countFrom(String _contractName,String _from) throws IOException{
		Chaintest chainHandle = new Chaintest();
		chainHandle.callContractId = _contractName;
		chainHandle.initMychainEnv();
		chainHandle.initSdk();

		long returnValue;
		returnValue = chainHandle.selectAllAmountByFrom(_from);

		return returnValue;
	}
	public String getContractHash(String _contractName){
		Identity contractHash = Utils.getIdentityByName(_contractName);
		return contractHash.toString();
	}
    /**
     * 根据交易id返回交易条目
     * (<合约名>,<id>)
     */
//	public String getTransById(String _contractName,Integer _id) throws IOException{
//		Chaintest chainHandle = new Chaintest();
//		chainHandle.callContractId = _contractName;
//		chainHandle.initMychainEnv();
//		chainHandle.initSdk();
//
//		String returnValue;
//		returnValue = chainHandle.selectInfoById(_id);
//
//		return returnValue;
//	}
}

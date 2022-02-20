<template>
    <div class="fillcontain">
        <head-top></head-top>
        <br><br><br><br><br><br><br>
        <p class="explain_text">客户账户余额: {{bank_money}}</p>
    </div>
</template>

<script>
    import headTop from '../components/headTop'
    import {bank_query_balance} from '@/api/getData'
    export default {
        data(){
            return {
                bank_money: "________",
            }
        },
    	components: {
    		headTop,
    	},
        created(){
            this.initData();
        },
        methods: {
            async initData(){
                try{
                    this.getAttributes();
                }catch(err){
                    this.$message({
                        type: 'error',
                        message: '获取银行账户金额失败'
                    });
                }
            },
            async getAttributes(){
                const res = await bank_query_balance();
                if (res.rtCode == '0') {
                    this.bank_money = res.data.balance;
                    /*
                    this.bank_id = item.id;
                    this.bank_idKind = item.idKind;
                    this.bank_loginId = item,loginId;
                    this.bank_money = item.money;
                    */
                } else {
                  this.dataText = "数据加载异常" + res.msg;
                }
            }
        },
    }
</script>

<style lang="less">
	@import '../style/mixin';
    .explain_text{
      margin-top: 20px;
      text-align: center;
      font-size: 20px;
      color: #333;
    }

</style>

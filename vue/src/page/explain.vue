<template>
    <div class="fillcontain">
        <head-top></head-top>
		<br><br><br><br>
        <p class="explain_text">众筹项目资金溯源系统</p>
        <p class="explain_text">利用区块链的防篡改特性，解决众筹场景下资金流动的不信任问题</p>
        <p class="explain_text">我们打通银行与用户之间的障碍，转账数据上链，资金流动全程可追溯</p>
    </div>
</template>

<script>
	import headTop from '../components/headTop'
    export default {
    	components: {
    		headTop,
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

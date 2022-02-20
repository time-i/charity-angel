<template>
	<div class="manage_page fillcontain">
		<el-row style="height: 100%;">
	  		<el-col :span="4"  style="min-height: 100%; background-color: #eb9c83;">
				<el-menu :default-active="defaultActive" style="min-height: 100%;background-color: #eb9c83;"  router>
					<el-menu-item index="home"><i class="el-icon-menu"></i>首页</el-menu-item>
					<el-submenu index="1">
						<template slot="title"><i class="el-icon-document"></i>个人信息</template>
						<el-menu-item index="addAdmin">创建管理员账户</el-menu-item>
					</el-submenu>
					<el-submenu index="2">
						<template slot="title"><i class="el-icon-minus"></i>银行账户</template>
						<el-menu-item index="issuebank">存款</el-menu-item>
						<el-menu-item index="bankquerybalance">余额查询</el-menu-item>
						<el-menu-item index="bankTransferInfo">账户流水</el-menu-item>
					</el-submenu>
					<el-submenu index="3">
						<template slot="title"><i class="el-icon-plus"></i>项目管理</template>
						<el-menu-item index="init_project">发起项目</el-menu-item>
						<el-menu-item index="transfer">向项目转账</el-menu-item>
						<el-menu-item index="query_all_project">查询所有项目</el-menu-item>
						<el-menu-item index="queryProjectInfo">查询项目信息</el-menu-item>
						<el-menu-item index="queryProjectMoney">查询项目余额</el-menu-item>
						<el-menu-item index="queryProjectDonator">查询项目捐献者</el-menu-item>
					</el-submenu>
					<el-submenu index="4">
						<template slot="title"><i class="el-icon-check"></i>链上信息</template>
						<el-menu-item index="chain_query_in">资金转入记录</el-menu-item>
						<el-menu-item index="chain_query_out">资金转出记录</el-menu-item>
					</el-submenu>
					<el-submenu index="5">
						<template slot="title"><i class="el-icon-warning"></i>说明</template>
						<el-menu-item index="explain">说明</el-menu-item>
					</el-submenu>
				</el-menu>
			</el-col>
			<el-col :span="20" style="height: 100%;overflow: auto;">
				<keep-alive>
				    <router-view></router-view>
				</keep-alive>
			</el-col>
		</el-row>
  	</div>
</template>

<script>
    export default {
		computed: {
			defaultActive: function(){
				return this.$route.path.replace('/', '');
			}
		},
    }
</script>


<style lang="less" scoped>
	@import '../style/mixin';
	
</style>

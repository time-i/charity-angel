<template>
  	<div class="login_page fillcontain">
		
		<el-menu
		:default-active="activeIndex"
		class="el-menu-demo"
		mode="horizontal"
		@select="handleSelect"
		background-color="#606266"
		text-color="#fff"
		active-text-color="#ffd04b">
			<el-menu-item index="1" @click="user_login()">登录</el-menu-item>
			<el-menu-item index="2" @click="user_reigister()">注册普通用户</el-menu-item>
			<el-menu-item index="3" @click="admin_register()">注册管理员</el-menu-item>
		</el-menu>

	  	<transition name="form-fade" mode="in-out">
	  		<section class="form_contianer" v-show="showLogin">
		  		<div class="manage_tip">
		  			<p>Charity Angel</p>
		  		</div>
		    	<el-form :model="loginForm" :rules="rules" ref="loginForm">
					<el-form-item prop="account">
						<el-input v-model="loginForm.account" placeholder="用户名"><span>dsfsf</span></el-input>
					</el-form-item>
					<el-form-item prop="password">
						<el-input type="password" placeholder="密码" v-model="loginForm.password"></el-input>
					</el-form-item>
					<el-form-item>
				    	<el-button type="primary" @click="submitForm('loginForm')" class="submit_btn">注册</el-button>
				  	</el-form-item>
				</el-form>
	  		</section>
	  	</transition>
  	</div>
</template>

<script>
	import {register_admin} from '@/api/getData'
	//import {mapActions, mapState} from 'vuex'
	import {mapMutations} from 'vuex';

	export default {
	    data(){
			return {
				activeIndex: '1',
				loginForm: {
					username: '',
					password: '',
				},
				rules: {
					username: [
			            { required: true, message: '请输入用户名', trigger: 'blur' },
			        ],
					password: [
						{ required: true, message: '请输入密码', trigger: 'blur' }
					],
				},
				showLogin: false,
			}
		},
		mounted(){
			this.showLogin = true;
		},
		computed: {
		},
		methods: {
			...mapMutations(['changeLogin']),
			async submitForm(formName) {
				let _this = this;
				this.$refs[formName].validate(async (valid) => {
					if (valid) {
                        console.log({account: this.loginForm.account, password: this.loginForm.password});
						const res = await register_admin({account: this.loginForm.account, password: this.loginForm.password})
						if (res.rtCode == '0') {
							this.$message({
		                        type: 'success',
		                        message: '管理员账号注册成功'
		                    });
		                    _this.changeLogin({ Authorization: res.data.token});
							console.log(res.data.token);
							this.$router.push('/home');
						}else{
							this.$message({
		                        type: 'error',
		                        message: res.msg
		                    });
						}
					} else {
						this.$notify.error({
							title: '错误',
							message: '请输入正确的用户名密码',
							offset: 100
						});
						return false;
					}
				});
			},
			handleSelect(key, keyPath) {
        		console.log(key, keyPath);
      		},
			user_login() {
				this.$router.push('/');
			},
			user_reigister() {
				this.$router.push('/user_register');
			},	
			admin_register() {
				this.$router.push('/admin_register')
			}		
		}
	}
</script>

<!--



-->

<style lang="less" scoped>
	@import '../style/mixin';
	.login_page{
		background-color: #324057;
	}
	.manage_tip{
		position: absolute;
		width: 100%;
		top: -100px;
		left: 0;
		p{
			font-size: 34px;
			color: #fff;
		}
	}
	.form_contianer{
		.wh(350px, 210px);
		.ctp(350px, 210px);
		padding: 25px;
		border-radius: 5px;
		text-align: center;
		background-color: #fff;
		.submit_btn{
			width: 100%;
			font-size: 16px;
		}
	}
	.tip{
		font-size: 12px;
		color: red;
	}
	.form-fade-enter-active, .form-fade-leave-active {
	  	transition: all 1s;
	}
	.form-fade-enter, .form-fade-leave-active {
	  	transform: translate3d(0, -50px, 0);
	  	opacity: 0;
	}
</style>

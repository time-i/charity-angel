<template>
    <div>
        <head-top></head-top>
        <el-row style="margin-top: 20px;">
  			<el-col :span="12" :offset="4">
		        <el-form :model="formData" :rules="rules" ref="formData" label-width="110px" class="demo-formData">
					<el-form-item label="捐赠人" prop="from">
						<el-input v-model="formData.from" placeholder="account"></el-input>
					</el-form-item>
					<el-form-item label="接收项目" prop="to">
						<el-input v-model="formData.to" placeholder="account"></el-input>
					</el-form-item>
					<el-form-item label="金额" prop="money">
						<el-input v-model="formData.money" ></el-input>
					</el-form-item>
					<el-form-item class="button_submit">
						<el-button type="primary" @click="submitForm('formData')">转账</el-button>
					</el-form-item>
				</el-form>
  			</el-col>
  		</el-row>
    </div>
</template>

<script>
    import headTop from '@/components/headTop'
    import {issueTransfer} from '@/api/getData'
    export default {
    	data(){
    		return {
    			formData: {
					name: '', 
					desc: '',
		        },
		        rules: {
					from: [
						{ required: true, message: '请输入捐赠人名称', trigger: 'blur' },
					],
					to: [
						{ required: true, message: '请输入受赠项目名称', trigger: 'blur' },
					],
                    money: [
                        { required: true, message: '请输入转账金额', trigger: 'blur' }
                    ],
				}
    		}
    	},
    	components: {
    		headTop,
    	},
    	mounted(){
    		this.initData();
    	},
    	methods: {
    		async initData(){
    		},
		    submitForm(formName) {
				this.$refs[formName].validate(async (valid) => {
					if (valid) {
						try{
							console.log(this.formData);
							let result = await issueTransfer(this.formData);
							console.log("result:" + result);
							if (result.rtCode == '0') {
								this.$message({
					            	type: 'success',
					            	message: '资金流记录成功'
					          	});
					          	this.formData = {
									name: '',
									desc: '',
						        };
							}else{
								this.$message({
					            	type: 'error',
					            	message: '资金流记录失败'
					          	});
							}
						}catch(err){
							this.$message({
								type: 'error',
								message: '请求发送失败'
							});
						}
					} else {
						this.$notify.error({
							title: '错误',
							message: '请检查输入是否正确',
							offset: 100
						});
						return false;
					}
				});
			},
		}
    }
</script>

<style lang="less">
	@import '../style/mixin';
	.button_submit{
		text-align: center;
	}
	.avatar-uploader .el-upload {
	    border: 1px dashed #d9d9d9;
	    border-radius: 6px;
	    cursor: pointer;
	    position: relative;
	    overflow: hidden;
	}
	.avatar-uploader .el-upload:hover {
	    border-color: #20a0ff;
	}
	.avatar-uploader-icon {
	    font-size: 28px;
	    color: #8c939d;
	    width: 120px;
	    height: 120px;
	    line-height: 120px;
	    text-align: center;
	}
	.avatar {
	    width: 120px;
	    height: 120px;
	    display: block;
	}
	.el-table .info-row {
	    background: #c9e5f5;
	}

	.el-table .positive-row {
	    background: #e2f0e4;
	}
</style>

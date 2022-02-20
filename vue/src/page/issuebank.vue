<template>
    <div>
        <head-top></head-top>
        <el-row style="margin-top: 20px;">
  			<el-col :span="12" :offset="4">
				<p class="explain_text">存款</p><br>
		        <el-form :model="formData" :rules="rules" ref="formData" label-width="110px" class="demo-formData">
					<el-form-item label="账户名" prop="userName">
						<el-input v-model="formData.userName"></el-input>
					</el-form-item>
					<el-form-item label="存款金额" prop="issue">
						<el-input v-model="formData.issue"></el-input>
					</el-form-item>
					<el-form-item class="button_submit">
						<el-button type="primary" @click="submitForm('formData')">确定</el-button>
					</el-form-item>
				</el-form>
  			</el-col>
  		</el-row>
    </div>
</template>

<script>
    import headTop from '@/components/headTop'
    import {issue_bank} from '@/api/getData'
    export default {
    	data(){
    		return {
    			formData: {
					name: '', 
					desc: '',
		        },
		        rules: {
					userName: [
						{ required: true, message: '请输入用户名', trigger: 'blur' },
					],
					issue: [
						{ required: true, message: '请输转账金额', trigger: 'blur' }
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
							console.log({issue:this.formData.issue,userName:this.formData.userName});
							let result = await issue_bank({issue:this.formData.issue,userName:this.formData.userName});
							if (result.rtCode == '0') {
								this.$message({
									type: 'success',
									message: '存款操作成功'
								});
								this.formData = {
									account: '',
									password: '',
								};
							}else{
								this.$message({
									type: 'error',
									message: result.msg
								});
							}
							
						}catch(err){
                            this.$message({
					            type: 'error',
					            message: '需要管理员权限或用户名重复'
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

	.explain_text{
      margin-top: 20px;
      text-align: center;
      font-size: 20px;
      color: #333;
    }
</style>

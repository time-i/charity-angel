<template>
    <div class="fillcontain">
        <head-top></head-top>
        <el-row style="margin-top: 20px;">
  			<el-col :span="12" :offset="4">
		        <el-form :model="formData" :rules="rules" ref="formData" label-width="110px" class="demo-formData">
					<el-form-item label="项目ID" prop="id">
						<el-input v-model="formData.id"></el-input>
					</el-form-item>
					<el-form-item class="button_submit">
						<el-button type="primary" @click="submitForm('formData')">查询</el-button>
						<!--<el-button type="primary" icon="el-icon-search" @click="submitForm('formData')" >搜索</el-button>-->
					</el-form-item>
				</el-form>
  			</el-col>
  		</el-row>
        <p class="explain_text">项目账户余额: {{bank_money}}</p>
    </div>
</template>


<script>
    import headTop from '@/components/headTop'
    import {query_project_money} from '@/api/getData'
    export default {
    	data(){
    		return {
    			formData: {
					id: '',
		        },
		        rules: {
					id: [
						{ required: true, message: '请输入项目ID', trigger: 'blur' },
					]
				},
                bank_money: "________",
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
							let result = await query_project_money(this.formData.id);

							if (result.rtCode == '0') {
                                this.bank_money = result.data.account.money;
					          	this.formData = {
									name: '',
									desc: '',
						        };
								this.$message({
					            	type: 'success',
					            	message: '查询成功'
					          	});
							}else{
								this.$message({
					            	type: 'error',
					            	message: "查询失败"
					          	});
							}
						}catch(err){
							this.$message({
								type: 'error',
								message: "请求发送失败"
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

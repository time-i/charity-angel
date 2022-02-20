<template>
    <div>
        <head-top></head-top>
        <el-row style="margin-top: 20px;">
  			<el-col :span="12" :offset="4">
		        <el-form :model="formData" :rules="rules" ref="formData" label-width="110px" class="demo-formData">
					<el-form-item label="项目名称" prop="name">
						<el-input v-model="formData.name" ></el-input>
					</el-form-item>
					<el-form-item label="项目描述" prop="desc">
						<el-input v-model="formData.desc"></el-input>
					</el-form-item>
					<el-form-item label="限额" prop="requiredMoney">
						<el-input v-model="formData.requiredMoney" ></el-input>
					</el-form-item>
					<el-form-item label="截止日期" prop="ddl">
						<el-input v-model="formData.ddl" ></el-input>
					</el-form-item>
					<el-form-item class="button_submit">
						<el-button type="primary" @click="submitForm('formData')">创建</el-button>
					</el-form-item>
				</el-form>
  			</el-col>
  		</el-row>
    </div>
</template>

<script>
    import headTop from '@/components/headTop'
    import {initial_project} from '@/api/getData'
    export default {
    	data(){
    		return {
    			formData: {
					name: '', 
					desc: '',
					requiredMoney: '',
					ddl: '',
		        },
		        rules: {
					name: [
						{ required: true, message: '请输入项目名称', trigger: 'blur' },
					],
					desc: [
						{ required: true, message: '请输入项目描述', trigger: 'blur' }
					],
                    requiredMoney: [
                        { required: true, message: '请输入项目额度', trigger: 'blur' }
                    ],
                    ddl: [
                        { required: true, message: '请输入项目截止日期', trigger: 'blur' }
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
							console.log({name:this.formData.name,desc:this.formData.desc,requiredMoney:this.formData.requiredMoney,ddl:this.formData.ddl});
							let result = await initial_project({name:this.formData.name,desc:this.formData.desc,requiredMoney:this.formData.requiredMoney,ddl:this.formData.ddl});
							console.log(result);
							if (result.rtCode == '0') {
								this.$message({
					            	type: 'success',
					            	message: '项目创建成功'
					          	});
					          	this.formData = {
									name: '', 
									desc: '',
									requiredMoney: '',
									ddl: '',
						        };
							}else{
								this.$message({
					            	type: 'error',
					            	message: "项目创建失败"
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

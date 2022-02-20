<template>
    <div class="fillcontain">
        <head-top></head-top>
        <el-row style="margin-top: 20px;">
  			<el-col :span="12" :offset="4">
				<p class="explain_text">个人属性添加</p><br>
		        <el-form :model="formData" :rules="rules" ref="formData" label-width="110px" class="demo-formData">
					<el-form-item label="个人属性名称" prop="name">
						<el-input v-model="formData.name"></el-input>
					</el-form-item>
					<el-form-item label="个人属性描述" prop="desc">
						<el-input v-model="formData.desc"></el-input>
					</el-form-item>
					<el-form-item class="button_submit">
						<el-button type="primary" @click="submitForm('formData')">立即创建</el-button>
					</el-form-item>
				</el-form>
  			</el-col>
  		</el-row>
        <p class="explain_text">合约名称: {{contract_name}}</p>
        <p class="explain_text">合约地址: {{contract_addr}}</p>
        <p class="explain_text">项目名称: {{project_name}}</p>
        <p class="explain_text">项目描述: {{project_desc}}</p>
        <p class="explain_text">项目ID: {{project_id}}</p>
        <p class="explain_text">计划筹集资金: {{project_money}}</p>
        <p class="explain_text">项目发起者: {{user_name}}</p>
        <p class="explain_text">项目开始时间: {{project_start}}</p>
        <p class="explain_text">项目截止日期: {{project_ddl}}</p>
    </div>
</template>

<script>
    import headTop from '@/components/headTop'
    import {addGoodsAttribute} from '@/api/getData'
    export default {
    	data(){
    		return {
    			formData: {
					name: '', 
					desc: '',
		        },
		        rules: {
					name: [
						{ required: true, message: '请输入属性名称', trigger: 'blur' },
					],
					desc: [
						{ required: true, message: '请输属性描述', trigger: 'blur' }
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
							let result = await addGoodsAttribute(this.formData);
							if (result.rspCode == '000000') {
								this.$message({
					            	type: 'success',
					            	message: '添加成功'
					          	});
					          	this.formData = {
									name: '',
									desc: '',
						        };
							}else{
								this.$message({
					            	type: 'error',
					            	message: result.rspMsg
					          	});
							}
						}catch(err){
							console.log(err)
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

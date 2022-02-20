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
    import {query_project_info} from '@/api/getData'
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
                contract_name:"________",
                contract_addr:"________",
                project_name:"________",
                project_desc:"________",
                project_id:"________",
                project_money:"________",
                user_name:"________",
                project_start:"________",
                project_ddl:"________",
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
							let result = await query_project_info(this.formData.id);
							if (result.rtCode == '0') {
                                this.contract_name = result.data.program.contractName;
                                this.contract_addr = result.data.program.contractAddr;
                                this.project_name = result.data.program.name;
                                this.project_desc = result.data.program.desc;
                                this.project_id = result.data.program.id;
                                this.project_money = result.data.program.requiredMoney;
                                this.user_name = result.data.program.userId;
                                this.project_start = result.data.program.start;
                                this.project_ddl = result.data.program.ddl;
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
					            	message: result.msg
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

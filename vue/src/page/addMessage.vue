<template>
    <div>
        <head-top></head-top>
        <el-row style="margin-top: 20px;">
  			<el-col :span="12" :offset="4">
		        <el-form :model="formData" :rules="rules" ref="formData" label-width="110px" class="demo-formData">
					<el-form-item label="发送人" prop="name">
						<el-input v-model="formData.name"></el-input>
					</el-form-item>
					<el-form-item label="消息内容" prop="desc">
						<el-input v-model="formData.message"></el-input>
					</el-form-item>
					<el-form-item label="评分">
						<el-select v-model="formData.rating" placeholder="请选择">
						    <el-option
						      	v-for="item in ratingList"
						      	:key="item.value"
						      	:label="item.label"
						      	:value="item.value">
						    </el-option>
					 	</el-select>
					</el-form-item>
					<el-form-item class="button_submit">
						<el-button type="primary" @click="submitForm('formData')">发送</el-button>
					</el-form-item>
				</el-form>
  			</el-col>
  		</el-row>
    </div>
</template>

<script>
    import headTop from '@/components/headTop'
    import {addGuestMessage} from '@/api/getData'
    export default {
    	data(){
    		return {
    			formData: {
					name: '', 
					message: '',
		        },
		        rules: {
					name: [
						{ required: true, message: '请输入发送人', trigger: 'blur' },
					],
					message: [
						{ required: true, message: '请输消息内容', trigger: 'blur' }
					],
				},
				ratingList: [{
		          	value: '1',
		          	label: '1'
		        }, {
		          	value: '2',
		          	label: '2'
		        },{
		          	value: '3',
		          	label: '3'
		        },{
		          	value: '4',
		          	label: '4'
		        },{
		          	value: '5',
		          	label: '5'
		        }]
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
							let result = await addGuestMessage(this.formData);
							if (result.rspCode == '000000') {
								this.$message({
					            	type: 'success',
					            	message: '添加成功'
					          	});
					          	this.formData = {
									name: '',
									message: '',
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
</style>

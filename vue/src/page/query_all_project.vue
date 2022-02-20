<template>
    <div class="fillcontain">
        <head-top></head-top>
        <div class="table_container">
            <el-table
                :data="tableData"
                highlight-current-row
                style="width: 100%">
                <template slot="empty">
                  <p>{{dataText}}</p>
                </template>
                <el-table-column
                  property="id"
                  label="ID">
                </el-table-column>
                <el-table-column
                  property="name"
                  label="项目名称">
                </el-table-column>
                <el-table-column
                  property="userId"
                  label="所有者ID">
                </el-table-column>
                <el-table-column
                  property="desc"
                  label="项目描述">
                </el-table-column>
                <el-table-column
                  property="requiredMoney"
                  label="筹集金额">
                </el-table-column>
                <el-table-column
                  property="start"
                  label="开始时间">
                </el-table-column>
                <el-table-column
                  property="ddl"
                  label="截止时间">
                </el-table-column>
                <el-table-column
                  property="contractName"
                  label="合约名称">
                </el-table-column>
                <el-table-column
                  property="contractAddr"
                  label="合约地址">
                </el-table-column>
            </el-table>
            <div class="Pagination" style="text-align: left;margin-top: 10px;">
                <el-pagination
                  @size-change="handleSizeChange"
                  @current-change="handleCurrentChange"
                  :current-page="currentPage"
                  :page-size="50"
                  layout="total, prev, pager, next"
                  :total="count">
                </el-pagination>
            </div>
        </div>
    </div>
</template>

<script>
    import headTop from '../components/headTop'
    import {queryAllProject} from '@/api/getData'
    export default {
        data(){
            return {
                dataText: "",
                tableData: [],
                currentRow: null,
                offset: 0,
                limit: 50,
                count: 0,
                currentPage: 1,
            }
        },
    	components: {
    		headTop,
    	},
        created(){
            this.initData();
        },
        methods: {
            async initData(){
                try{
                    this.getAttributes();
                }catch(err){
                    this.$message({
                        type: 'error',
                        message: '获取银行账户流水数据失败'
                    });
                    console.log('获取数据失败', err);
                }
            },
            handleSizeChange(val) {
                console.log(`每页 ${val} 条`);
            },
            handleCurrentChange(val) {
                this.currentPage = val;
                this.offset = (val - 1)*this.limit;
                this.getAttributes();
            },
            async getAttributes(){
                this.dataText = "正在加载蚂蚁链数据,请稍后...";
                const res = await queryAllProject();
                console.log(res);
                if (res.rtCode == '0') {
                  var attributeList = res.data.program_list ;
                  this.count = attributeList.length;
                  this.tableData = [];
                  this.dataText = '';
                  attributeList.forEach(item => {
                    const tableData = {};
                    tableData.id = item.id;
                    tableData.name = item.name;
                    tableData.userId = item.userId;
                    tableData.desc = item.desc;
                    tableData.requiredMoney = item.requiredMoney;
                    tableData.start = item.start;
                    tableData.ddl = item.ddl;
                    tableData.contractName = item.contractName;
                    tableData.contractAddr = item.contractAddr;
                    this.tableData.push(tableData);
                  })

                  if (this.tableData.length === 0) {
                    this.dataText = "暂无数据";
                  }
                } else {
                  this.dataText = "数据加载异常";
                }
            }
        },
    }
</script>

<style lang="less">
	@import '../style/mixin';
    .table_container{
        padding: 20px;
    }
    

</style>

<template>
    <div class="fillcontain">
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
                  label="ID" width="80">
                </el-table-column>
                <el-table-column
                  property="from"
                  label="捐赠者">
                </el-table-column>
                <el-table-column
                  property="to"
                  label="接收者/项目">
                </el-table-column>
                <el-table-column
                  property="amount"
                  label="资金流水">
                </el-table-column>
                <el-table-column
                  property="time"
                  label="转账时间">
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
    import {chainQueryIn} from '@/api/getData'
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
                var id = localStorage.getItem('Program_id') ? localStorage.getItem('Program_id') : '0';
                console.log("display(program_id):" + id);
                const res = await chainQueryIn(id);
                console.log(res);
                if (res.rtCode == '0') {
                  var attributeList = res.data.transfer_in ;
                  this.count = attributeList.length;
                  this.tableData = [];
                  this.dataText = '';
                  attributeList.forEach(item => {
                    const tableData = {};
                    tableData.id = item.id;
                    tableData.from = item.from;
                    tableData.to = item.to;
                    tableData.amount = item.amount;
                    tableData.time = item.time;
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



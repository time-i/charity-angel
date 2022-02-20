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
                  type="index"
                  width="100">
                </el-table-column>
                <el-table-column
                  property="name"
                  label="个人属性名称"
                  width="220">
                </el-table-column>
                <el-table-column
                  property="desc"
                  label="个人属性描述">
                </el-table-column>
                <el-table-column
                  property="date"
                  label="创建时间">
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
    import {getAttributeList} from '@/api/getData'
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
                const res = await getAttributeList();
                if (res.rspCode == '000000') {
                  var attributeList = res.rspData.attributeList ;
                  this.count = attributeList.length;
                  this.tableData = [];
                  attributeList.forEach(item => {
                    const tableData = {};
                    tableData.id = item.id;
                    tableData.name = item.name;
                    tableData.date = item.date;
                    tableData.desc = item.desc;
                    this.tableData.push(tableData);
                  })

                  if (this.tableData.length === 0) {
                    this.dataText = "暂无数据";
                  }
                } else {
                  this.dataText = "数据加载异常" + res.rspMsg;
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

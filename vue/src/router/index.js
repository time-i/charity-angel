import Vue from 'vue'
import Router from 'vue-router'
import {getStore,setStore} from '@/config/mUtils'

Vue.use(Router)

const login = r => require.ensure([], () => r(require('@/page/login')), 'login');
const user_register = r => require.ensure([], () => r(require('@/page/user_register')), 'user_register');
const dashboard = r => require.ensure([], () => r(require('@/page/dashboard')), 'dashboard');
const home = r => require.ensure([], () => r(require('@/page/home')), 'home');
//const default_jpg = r => require.ensure([], () => r(require('@/img/default.jpg')), 'default_jpg');


const addAdmin = r => require.ensure([], () => r(require('@/page/addAdmin')), 'addAdmin');

const issuebank = r => require.ensure([], () => r(require('@/page/issuebank')), 'issuebank');
const bankquerybalance = r => require.ensure([], () => r(require('@/page/bankquerybalance')), 'bankquerybalance');
const bankTransferInfo = r => require.ensure([], () => r(require('@/page/bankTransferInfo')), 'bankTransferInfo');

const query_all_project = r => require.ensure([], () => r(require('@/page/query_all_project')), 'query_all_project');
const transfer = r => require.ensure([], () => r(require('@/page/transfer')), 'transfer');
const init_project = r => require.ensure([], () => r(require('@/page/init_project')), 'init_project');
const queryProjectInfo = r => require.ensure([], () => r(require('@/page/queryProjectInfo')), 'queryProjectInfo');
const queryProjectMoney = r => require.ensure([], () => r(require('@/page/queryProjectMoney')), 'queryProjectMoney');
const queryProjectDonator = r => require.ensure([], () => r(require('@/page/queryProjectDonator')), 'queryProjectDonator');
const queryProjectDonatorDisplay = r => require.ensure([], () => r(require('@/page/queryProjectDonatorDisplay')), 'queryProjectDonatorDisplay');


const chain_query_in = r => require.ensure([], () => r(require('@/page/chain_query_in')), 'chain_query_in');
const chain_query_in_display = r => require.ensure([], () => r(require('@/page/chain_query_in_display')), 'chain_query_in_display');
const chain_query_out = r => require.ensure([], () => r(require('@/page/chain_query_out')), 'chain_query_out');
const chain_query_out_display = r => require.ensure([], () => r(require('@/page/chain_query_out_display')), 'chain_query_out_display');

const explain = r => require.ensure([], () => r(require('@/page/explain')), 'explain');

const routes = [
	{
		path: '/',
		component: login
	},
	{
		path: '/home',
		component: home,
		name: '',
		children: [{
			path: '',
			component: dashboard,
			meta: [],
		},{
			path: '/addAdmin',
			component: addAdmin,
			meta: ['????????????', '?????????????????????'],
		},{
			path: '/issuebank',
			component: issuebank,
			meta: ['????????????', '??????'],
		},{
			path: '/bankquerybalance',
			component: bankquerybalance,
			meta: ['????????????', '????????????'],
		},{
			path: '/bankTransferInfo',
			component: bankTransferInfo,
			meta: ['????????????', '????????????'],
		},{
			path: '/query_all_project',
			component: query_all_project,
			meta: ['????????????', '??????????????????'],
		},{
			path: '/queryProjectInfo',
			component: queryProjectInfo,
			meta: ['????????????', '??????????????????'],
		},{
			path: '/transfer',
			component: transfer,
			meta: ['????????????', '???????????????'],
		},{
			path: '/init_Project',
			component: init_project,
			meta: ['????????????', '????????????'],
		},{
			path: '/queryProjectMoney',
			component: queryProjectMoney,
			meta: ['????????????', '??????????????????'],
		},{
			path: '/queryProjectDonator',
			component: queryProjectDonator,
			meta: ['????????????', '?????????????????????'],
		},{
			path: '/queryProjectDonatorDisplay',
			component: queryProjectDonatorDisplay,
			meta: ['????????????', '?????????????????????'],
		},{
			path: '/chain_query_in',
			component: chain_query_in,
			meta: ['????????????', '??????????????????'],
		},{
			path: '/chain_query_in_display',
			component: chain_query_in_display,
			meta: ['????????????', '??????????????????'],
		},{
			path: '/chain_query_out',
			component: chain_query_out,
			meta: ['????????????', '??????????????????'],
		},{
			path: '/chain_query_out_display',
			component: chain_query_out_display,
			meta: ['????????????', '??????????????????'],
		},{
			path: '/explain',
			component: explain,
			meta: ['??????', '??????'],
		}]
	},
	{
		path: '/user_register',
		component: user_register
	}
]


const router = new Router({
  routes,
  strict: process.env.NODE_ENV !== 'production',
});


router.beforeEach((to, from, next) => {
  if (to.path === '/user_register') {
	next();
  } else if (to.path === '/' ) {
    next();
  }  else {
    let token = localStorage.getItem('Authorization');
    if (token === 'null' || token === '') {
      next('/');
    } else {
      next();
    }
  }

}); 


export default router;
import Vue from 'vue'
import Vuex from 'vuex'
//import {getAdminInfo} from '@/api/getData'

Vue.use(Vuex)

const state = {
	Authorization: localStorage.getItem('Authorization') ? localStorage.getItem('Authorization') : '',
	Program_id: localStorage.getItem('Program_id') ? localStorage.getItem('Program_id') : '0',
	Name: localStorage.getItem('Name') ? localStorage.getItem('Name') : '',
	
	adminInfo: {
		avatar: 'default.jpg'
	},
	
}

const mutations = {
	changeLogin (state,user) {
		console.log("user_token:"+user.Authorization);
		console.log("state_token:"+state.Authorization);
      	localStorage.setItem('Authorization', user.Authorization);
      	state.Authorization = user.Authorization;
    }/*,
	saveAdminInfo(state, adminInfo){
		state.adminInfo = adminInfo;
	}*/
}
/*
const actions = {
	async getAdminData({commit}){
		try{
			const res = await getAdminInfo()
			if (res.status == 1) {
				commit('saveAdminInfo', res.data);
			}else{
				throw new Error(res.type)
			}
		}catch(err){
			// console.log(err.message)
		}
	}
}
*/
export default new Vuex.Store({
	state,
	//actions,
	mutations,
})

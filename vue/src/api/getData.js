import { baseAxios } from '@/utils/axios'

/**
 * 登陆
 */
export const login = (data) => {
	return baseAxios.post('/login', data).then(result => {
		return result.data
	})
}

/**
 * 普通用户注册
 */
 export const register_user = (data) => {
	return baseAxios.post('/api/user/register', data).then(result => {
		return result.data
	})
}

/**
 * 管理员注册
 */
 export const register_admin = (data) => {
	return baseAxios.post('/api/user/admin/register', data).then(result => {
		return result.data
	})
}

/**
 * 发起转账
 */
export const issueTransfer = (data) => {
	return baseAxios.post('/api/bank/user/transfer', data).then(result => {
	  return result.data
	})
}

/**
 * 创建项目
 */
 export const initial_project = (data) => {
	return baseAxios.post('/api/program/create', data).then(result => {
	  return result.data
	})
}

/**
 * 根据account获取项目信息
 */

 export const query_projects_information = () => {
	return baseAxios.get('/api/bank/query').then(result => {
		return result.data
	})
}



/**
 * 登录后在自己的银行账户中存款
 */
 export const issue_bank = (data) => {
	return baseAxios.post('/api/bankaccount/issue', data).then(result => {
		return result.data
	})
}

/**
 * 获取user信息
 */
 export const getUser = () => {
	return baseAxios.get('/api/user/get').then(result => {
		return result.data
	})
}

/**
 * 获取银行账户信息
 */
 export const bank_query_balance = () => {
	return baseAxios.get('/api/bankaccount/query_balance').then(result => {
		return result.data
	})
}

/**
 * 获取账户流水信息
 */
 export const bank_query = () => {
	return baseAxios.get('/api/bank/query').then(result => {
		return result.data
	})
}

/**
 * 获取所有项目信息
 */
 export const queryAllProject = () => {
	return baseAxios.get('/api/program/get_all').then(result => {
		return result.data
	})
}

/**
 * 获取项目信息
 */
 export const query_project_info = (data) => {
	var str = '/api/program/get_info_by_id?id=' + data;
	console.log(str);
	return baseAxios.get(str).then(result => {
		return result.data
	})
}


/**
 * 获取特定项目的账户余额
 */
 export const query_project_money = (id) => {
	 var str = '/api/program/get_program_money_by_id?id=' + id; 
	return baseAxios.get(str).then(result => {
		return result.data
	})
}

/**
 * 获取特定项目的账户余额
 */
 export const query_project_donator = (name) => {
	var str = '/api/bank/project_fund?name=' + name; 
   return baseAxios.get(str).then(result => {
	   return result.data
   })
}

/**
 * 获取链上资金转入信息
 */
 export const chainQueryIn = (data) => {
	var str = '/api/program/chain/query_in?program_id=' + data;
	console.log(str);
	return baseAxios.get(str).then(result => {
		return result.data
	})
}

/**
 * 获取链上资金转出信息
 */
 export const chainQueryOut = (data) => {
	var str = '/api/program/chain/query_out?program_id=' + data;
	console.log(str);
	return baseAxios.get(str).then(result => {
		return result.data
	})
}





/**
 * 朔源物品统计
 */

export const getGoodsStatis = () => {
	return baseAxios.get('/api/goods/statis').then(result => {
		return result.data
	})
}

/**
 * 获取朔源物品信息
 */

export const getGoodsInfo = () => {
	return baseAxios.get('/api/goods/info').then(result => {
		return result.data
	})
}


/**
 * 获取属性列表
 */
export const getAttributeList = () => {
	return baseAxios.get('/api/goods/attributelist').then(result => {
		return result.data
	})
}



/**
 * 添加属性
 */

export const addGoodsAttribute = (data) => {
  return baseAxios.put('/api/goods/attribute/add', data).then(result => {
    return result.data
  })
}

/**
 * 获取交易环节列表
 */
/*
export const getLogisticList = () => {
	return baseAxios.get('/api/goods/logistics').then(result => {
		return result.data
	})
}
*/


/**
 * 添加交易环节
 */
/*
export const addLogistic = (data) => {
  return baseAxios.put('/api/goods/logistic/add', data).then(result => {
    return result.data
  })
}
*/

/**
 * 获取客户留言列表
 */

export const getGuestMessageList = () => {
	return baseAxios.get('/api/goods/messagelist').then(result => {
		return result.data
	})
}


/**
 * 添加留言
 */

export const addGuestMessage = (data) => {
  return baseAxios.put('/api/goods/message/add', data).then(result => {
    return result.data
  })
}


/**
 * 获取用户信息
 */
/*
export const getAdminInfo = () => {
	return baseAxios.get('/admin/info').then(result => {
		return result.data
	})
}
*/

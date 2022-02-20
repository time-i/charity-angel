import axios from "axios"
import { baseUrl } from '../config/env'

const headerToken = function(){
  let token = ''
  token = localStorage.getItem('Authorization') || "";
  return token
}


const baseAxios = axios.create({
  baseURL: baseUrl,
  timeout: 60*1000,
  headers: {
    Accept: "application/json; charset=utf-8",
    "Content-Type": "application/json; charset=utf-8",
    "Authorization": headerToken(),
  }
})

baseAxios.interceptors.request.use(
  config => {
    config.headers["Content-Type"] = "application/json; charset=utf-8"
    return config
  },
  error => {
    return Promise.reject(error)
  }
)
baseAxios.interceptors.response.use(
  resp => {
    const newToken = resp.headers["renew-token"]
    if (newToken) {
      store.commit("SET_TOKEN", newToken)
    }
    if (resp.data.rspMsg === "401" || resp.data.rspCode === "999999") {
      router.push({path:'/login'})
      return resp
    }
    return resp
  },
  error => {
    // 判断请求异常信息中是否含有超时timeout字符串
    if(error.message && error.message.includes('timeout')){
      return Promise.reject({
        message: `请求超时`
      })
    }
    return Promise.reject({
      message: `请求失败`
    })
  }
)

export { axios, baseAxios}
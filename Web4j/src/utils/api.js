import axios from "axios";
import { Message } from 'element-ui';
import router  from "@/router";

// 响应拦截器
axios.interceptors.response.use(success=>{
    if(success.status&&success.status==200){
        if(success.data.code==500||success.data.code==401||success.data.code==403){
            Message.error({message:success.data.message});
            return;
        }
        if(success.data.message){
            Message.success({message:success.data.message});
        }
    return success.data;
}}, error => {
        if (error.response.code == 504 || error.response.code == 404) {
            Message.error({message: "服务器走丢了`(*>﹏<*)′"});
        } else if (error.response.code == 403) {
            Message.error({message: "权限不足`(*>﹏<*)′"});
        } else if (error.response.code == 401) {
            Message.error({message: "请先登录`(*>﹏<*)′"});
            router.replace("/")
        }else {
            if(error.response.data.message){
                Message.error({message:error.response.data.message});
            }else{
                Message.error({message:"未知错误`(*>﹏<*)′"});
            }
        }
        return;
    }
);

let base = "";

// 传入json格式的post请求
export const postRequest = (url,params) =>{
    return axios({
        method:"post",
        url:`${base}${url}`,
        data:params,
        headers:{
            "Content-Type":"application/json"
        }
    })
}

// 传入json格式的get请求
export const getRequest = (url,params) =>{
    return axios({
        method:"get",
        url:`${base}${url}`+'/'+params,
    })
}

export const getRequestWithParams = (url,params) =>{
    return axios({
        method:"get",
        params:{
            username : params
        },
        url:`${base}${url}`,
    })
}

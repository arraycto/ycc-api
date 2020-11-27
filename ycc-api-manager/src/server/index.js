import Axios from 'axios'

const axios = Axios.create({
    baseURL:"/ycc-api-admin",
    timeout:10000

})

const api = {

    user:{
        login(data){
            return axios.post("/user/login",data);
        }
    }

}

export default api;
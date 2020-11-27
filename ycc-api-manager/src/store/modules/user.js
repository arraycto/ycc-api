module.exports={
    state:{
        loginUser:{}
    },
    getters:{
        getLoginUser(state){
            return state.loginUser;
        }
    },
    mutations:{
        setLoginUser(state,loginUser){
            state.loginUser=loginUser
        }
    }
}
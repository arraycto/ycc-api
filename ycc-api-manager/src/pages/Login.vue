<template>
    <div class="login">
            <div class="login-box">
                <div class="">
                    <!-- <img src="@/assets/img/20201125215808.png" alt=""
                     class="text-not-select"> -->
                    <h3 class="text-not-select">YCC接口文档管理平台只为便利而生</h3>

                </div>
                <Form  style="margin-top:50px" :disabled="loginBtnDisabled">
                    <FormItem>
                        <Input prefix="ios-contact" 
                        size="large"
                         style="width:100%" 
                         v-model="loginEntity.username"
                          placeholder="请输入帐号"/>
                    </FormItem>

                    <FormItem>
                    <Input prefix="md-lock"
                     size="large"
                    type="password"
                        style="width:100%" 
                        v-model="loginEntity.password"
                        placeholder="请输入密码"/>
                    </FormItem>
                    <FormItem style="text-align:left">
                      <Checkbox size="large" class="text-not-select" >下次自动登录</Checkbox>

                    <a href="javaScript:" style="float:right;font-size:14px">忘记密码?</a>
                    </FormItem>
                    <FormItem>
                    
                     <Button type="primary"  long size="large"  @click="login">登录</Button>
                    </FormItem>
                </Form>
            </div>
        
    </div>
</template>
<script>
export default {
    name:"login",
    data(){
        return {
            loginEntity:{},
            loginBtnDisabled:false
        }
    },
    methods: {
        login(){

            this.loginBtnDisabled=true;
            const closeCB = this.$Message.loading({
                content: '正在登陆请稍后...',
                duration: 0
            });
            this.$server.user.login(loginEntity).then(res=>{
                var data = res.data;
                if(data.code == 200){
                 closeCB();
                 this.$router.push({name:"home"})

                }else{
                    this.$Message.warning({
                        content:data.msg,
                        duration:3
                    })
                }
            })
      
        }
    },
}
</script>
<style lang="scss" scoped>
.login{

    background-color: #F7F7F7;
    width: inherit;
    height: inherit;
    min-width: 700px;

    display: flex;
    flex-direction: column;
    overflow: auto;
    align-items: center;
    justify-content: center;


    .login-box{
        padding: 80px 0;
        flex:1;
      width:400px;
      text-align: center;
    }
  
 
}
</style>
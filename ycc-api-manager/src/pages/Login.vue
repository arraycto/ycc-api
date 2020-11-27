<template>
  <div class="login">
    <div class="login-box">
      <div class="">
        <!-- <img src="@/assets/img/20201125215808.png" alt=""
                     class="text-not-select"> -->
        <h3 class="text-not-select">YCC接口文档管理平台只为便利而生</h3>
      </div>
      <Form
        ref="login-form"
        style="margin-top: 50px"
        :model="loginEntity"
        :rules="ruleValidate"
      >
        <FormItem prop="username">
          <Input
            prefix="ios-contact"
            size="large"
            style="width: 100%"
            v-model="loginEntity.username"
            placeholder="请输入帐号"
          />
        </FormItem>

        <FormItem prop="password">
          <Input
            prefix="md-lock"
            size="large"
            type="password"
            style="width: 100%"
            v-model="loginEntity.password"
            placeholder="请输入密码"
          />
        </FormItem>
        <FormItem style="text-align: left">
          <Checkbox size="large" class="text-not-select" v-model="autoLogin"
            >下次自动登录</Checkbox
          >

          <a href="javaScript:" style="float: right; font-size: 14px"
            >忘记密码?</a
          >
        </FormItem>
        <FormItem>
          <Button type="primary" long size="large" :loading="sendLoginReq" @click="submitd">登录</Button>
        </FormItem>
      </Form>
    </div>
  </div>
</template>
<script>
export default {
  name: "login",
  data() {
    return {
      sendLoginReq:false,
      autoLogin: false,
      loginEntity: {},
      ruleValidate: {
        username: { required: true, message: "登录名必须填写" },
        password: { required: true, message: "密码必须填写" },
      },
    };
  },
  methods: {
    login() {
        
        this.sendLoginReq=true;
      this.$server.user.login(this.loginEntity).then((res) => {
        var data = res.data;

            this.sendLoginReq=false;
        if (data.code == 200) {
          this.$router.push({ name: "home" });
        } else {
   

          this.$Message.warning({
            content: data.msg,
            duration: 3,
          });
        }
      });
    },
    submitd() {
      var $t = this;
      this.$refs["login-form"].validate((valid) => {
        if (valid) {
          $t.login();
        } else {
          _this.$Message.warning({
            content: "请先补充完整相关数据在重试",
            duration: 3,
          });
        }
      });
    },
  },
};
</script>
<style lang="scss" scoped>
.login {
  background-color: #f7f7f7;
  width: inherit;
  height: inherit;
  min-width: 700px;

  display: flex;
  flex-direction: column;
  overflow: auto;
  align-items: center;
  justify-content: center;

  .login-box {
    padding: 80px 0;
    flex: 1;
    width: 400px;
    text-align: center;
  }
}
</style>
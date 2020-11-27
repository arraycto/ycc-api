<template>
    <div class="index">
         <Header height="50px" bgColor="#3C8DBC" >
             <div class="logo-box text-not-select">
          
                    <span style="cursor: pointer;" @click="toHomePage">
                        API管理平台
                    </span>
                    
              </div>

        
            <div class="login-user-box text-not-select">
                 <Dropdown 
                    @on-click="downMenuClick"
                    trigger="click"
                 >
                     zhangsan
                    <Icon type="ios-arrow-down"></Icon>
                    <DropdownMenu slot="list" >
                        <DropdownItem disabled>个人信息</DropdownItem>
                        <DropdownItem >修改密码</DropdownItem>
                        <DropdownItem name="logout" divided>退出</DropdownItem>
                    </DropdownMenu>
                </Dropdown>
            </div>

            <div class="contact-box text-not-select">
                <Icon type="md-mail" />
                联系管理员
            </div>
         </Header>
         <div class="main-box">
              <Menu 
                ref="menu"
                 width="230px"
                    theme="dark" 
                    :active-name="menuActiveName"
                    class="my-menu text-not-select"
                    style=" height:inherit;"
                    @on-select="menuSelect"
                >
                    <Menu-group title="文档管理">
                        <Menu-item name="1">
                        <Icon type="md-radio-button-off" class="text-weight" color="#00C0EF" />
                            项目管理
                        </Menu-item>
                        <Menu-item name="2">
                            <Icon type="md-radio-button-off" class="text-weight" color="#F39C12" />
                            视图管理
                        </Menu-item>
                          <Menu-item name="3">
                            <Icon type="md-radio-button-off" class="text-weight" color="#00A65A" />
                           数据类型管理
                        </Menu-item>
                    </Menu-group>

                    <Menu-group title="人员管理">
                        
                        <Menu-item name="/menu-permission">
                            <Icon type="md-radio-button-off" class="text-weight" color="#DD4B39" />
                            菜单权限
                        </Menu-item>
                        
                        <Menu-item name="7">
                            <Icon type="md-radio-button-off" class="text-weight" color="#F18894" />
                            角色配置
                        </Menu-item>
                        <Menu-item name="5">
                            <Icon type="md-radio-button-off" class="text-weight" color="#605CA4" />
                            人员分配
                        </Menu-item>
                      
                    </Menu-group>

                    <Menu-group title="系统管理">
                      
                          <Menu-item name="6">
                             <Icon type="md-radio-button-off" class="text-weight" color="#2D8CF0" />
                            邮件设置
                        </Menu-item>
                         <Menu-item name="9">
                             <Icon type="md-radio-button-off" class="text-weight" color="#D2D6DE" />
                            日志管理
                        </Menu-item>
                    </Menu-group>
                </Menu>

                <div class="main-right">
                    <router-view></router-view>
                    
                </div>
         </div>
       
    </div>
</template>

<script>

import Header from '@/components/Header'
export default {
    name:"index",
    components:{
        Header
    },
    data(){
        return {
            menuActiveName:""
        }
    },
    created(){
        this.menuActiveName = this.$route.path;
    },
    watch: {
        '$route.path':function(newVal,oldVal){
            this.$nextTick(()=>{
                this.menuActiveName=newVal;
                 this.$refs.menu.updateActiveName();
            })
            
        }
    },
    methods:{
        // 菜单被选择
        menuSelect(name){
            if(this.$route.path != name){
                this.$router.push({path:name})
            }
        },
        // 下拉菜单被单机
        downMenuClick(name){
            console.log(name)
            switch(name){
                case "logout":
                    this.logout();
                    break;
                default:
            }
           
        },
        // 用户退出
        logout(){
             this.$router.push({
                path:"/login"
            })
        },
        // 点击logo跳转至欢迎页
        toHomePage(){
            if(this.$route.path != '/home'){
                 this.$router.push({path:'/home'})
            }
          
        }
    }
}
</script>

<style lang="scss" scoped>


   .index{
       
      height:inherit;
      .my-menu{
         background-color:  $menu-bg-color;
         color:#B8C7CE;
         .ivu-menu-item-active{
             color:#FFFFFF;
         }
      }
    

      .logo-box{
          float: left;
          width: $def-menu-width;
          background-color:#367FA9;
          padding-left: 50px;
          line-height:50px;
          height: inherit;
          font-size: 20px;
          color:rgb(226, 226, 221);
          font-family: 'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif;
          text-shadow: 10 10 2px #000;
      }

      .login-user-box{
          float:right;
          height: inherit;
          line-height: 50px;
          padding:0px 25px;
          font-size: 13px;
          color:lavender;

          &:hover{
               cursor: pointer;
               background-color:#367FA9;
          }
      }

      .contact-box{
  
        @extend .login-user-box;
      }

      .main-box{
          height: calc(100vh - 50px);
          display: flex;
          flex-direction: row;
          justify-content: flex-start;

          .main-right{
              position: relative;
              width: calc(100% - #{$def-menu-width}) ;
          }
      }
      
      
   }

   .ivu-icon-md-radio-button-off{
       transform: translateY(-2px);
   }
</style>
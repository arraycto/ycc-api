import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter);

import Login from '@/pages/Login'
import Index from '@/pages/index/Index'
    import Home from '@/pages/index/subPages/Home'
    import MenuPermission from '@/pages/index/subPages/MenuPermisson'


const vueRouter = new VueRouter({
    mode:"hash",
    routes:[
        {
            path:"/",
            redirect:"/index"
           
        },
        {
            name:"index",
            path:"/index",
            component:Index,
            meta:{
                title:"首页"
            },
            children:[
                {
                    name:"home",
                    path:"/home",
                    component:Home,
                    meta:{
                        title:"欢迎页"
                    }
                },
                {
                    name:"menu-permission",
                    path:"/menu-permission",
                    component:MenuPermission,
                    meta:{
                        title:"菜单权限"
                    }
                }
            ]
        },
        {
            name:"login",
            path:"/login",
            component:Login,
            meta:{
                title:"登录页"
            }
        }
    ]
});

export default vueRouter;
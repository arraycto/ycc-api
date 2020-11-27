import Vue from 'vue'
import App from './App.vue'
import router from '@/router'
import store from '@/store'
import server from '@/server'
import ViewUI from 'view-design';
import 'view-design/dist/styles/iview.css';


Vue.use(ViewUI)

Vue.prototype.$server=server;
Vue.prototype.$bus=new Vue();
Vue.config.productionTip = false


router.beforeEach((to, from, next) => {
  document.title = to.meta.title
  next()
})

new Vue({
  store,
  router,
  render: h => h(App),
}).$mount('#app')

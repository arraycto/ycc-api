import Vuex from 'vuex'
import Vue from 'vue'
Vue.use(Vuex)

import user from './modules/user'

const store = new Vuex.Store({
    modules:{
        user
    }
});

export default store;
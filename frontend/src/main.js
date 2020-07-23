import Vue from 'vue'
import App from './App.vue'
import vuetify from './plugins/vuetify'
import axios from 'axios'
import VueAxios from 'vue-axios'
import VueRouter from 'vue-router'
import Instagram from './components/Instagram.vue'
import Login from './components/Login.vue'

Vue.use(VueAxios, axios)
Vue.use(VueRouter)
Vue.config.productionTip = false;



// Register a global custom directive called `v-blur` that prevents focus
Vue.directive('blur', {
  inserted: function (el) {
    el.onfocus = (ev) => ev.target.blur()
  }
});

new Vue({
  vuetify,
  render: h => h(App)
}).$mount('#app');


export default new VueRouter({
  mode: 'history',
  routes: [
      {
          path: '/',
          redirect: {
              name: "Login"
          }
      },
      {
          path: "login",
          name: "Login",
          component: Login
      },
      {
          path: "home",
          name: "Instagram",
          component: Instagram
      }
  ]
})
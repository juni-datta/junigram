import Vue from 'vue'
import VueRouter from 'vue-router'
import Instagram from '../components/Instagram.vue'
import Login from "../components/Login.vue";

Vue.use(VueRouter)

const routes = [
  {
    path: "/login",
    name: "login",
    component: Login,
  },
  {
    path: "/",
    name: "home",
    component: Instagram
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router

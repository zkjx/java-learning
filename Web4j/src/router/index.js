import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../views/Login.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/vote',
    name: 'Vote',
    component: () => import('../views/Vote.vue')
  },
  {
    path: '/sale',
    name: 'Sale',
    component: () => import('../views/Sale.vue')
  },
  {
    path: '/',
    name: 'Hello',
    component: () => import('../views/Hello.vue')
  },
]

const router = new VueRouter({
  routes
})

export default router

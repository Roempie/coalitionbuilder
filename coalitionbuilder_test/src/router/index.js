import { createRouter, createWebHashHistory } from 'vue-router'
import Login from '../components/Login.vue'
import Register from '../components/Register.vue'
import Home from '../components/Home.vue'
import Dashboard from '../components/Dashboard.vue'
import ParliamentSeats from '../components/ParliamentSeats.vue'

const routes = [
  {
    path: '/',
    name: 'homepage',
    component: Home
  },
  {
    path: '/home',
    redirect: { name: 'homepage'}
  },
  {
    path: '/login',
    name: 'login',
    component: Login
  },
  {
    path: '/register',
    name: 'register',
    component: Register
  },
  {
    path: '/dashboard',
    name: 'dasbhoard',
    component: Dashboard
  },
  {
    path: '/parliament',
    name: 'parliament',
    component: ParliamentSeats
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router

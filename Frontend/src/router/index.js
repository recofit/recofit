import { createRouter, createWebHistory } from 'vue-router'
import MainPage from '../views/MainPage.vue'
import SignPage from '../views/SignPage.vue'
import MyPage from '../views/MyPage.vue'

const routes = [
  {
    path: '/',
    name: 'main',
    component: MainPage
  },
  {
    path: '/sign',
    name: 'sign',
    component: SignPage
  },
  {
    path: '/mypage',
    name: 'mypage',
    component: MyPage
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router

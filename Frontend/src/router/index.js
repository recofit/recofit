import { createRouter, createWebHistory } from 'vue-router'
import MainPage from '../views/MainPage.vue'
import SignPage from '../views/SignPage.vue'
import SearchPage from '../views/SearchPage.vue'
import DetailPage from '../views/DetailPage.vue'
import MyPage from '../views/MyPage.vue'
import CalendarPage from '../views/CalendarPage.vue'



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
    path: '/search',
    name: 'search',
    component: SearchPage
  },
  {
    path: '/detail',
    name: 'detail',
    component: DetailPage
  },
  {
    path: '/mypage',
    name: 'mypage',
    component: MyPage
  },
  {
    path: '/calendar',
    name: 'calendar',
    component: CalendarPage
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router

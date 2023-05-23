import { createApp } from 'vue'
import App from './App.vue'
import store from './store'
import router from './router'

import 'bootstrap'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap-icons/font/bootstrap-icons.css'
import VCalendar from 'v-calendar';
import 'v-calendar/style.css';

createApp(App).use(router).use(store).use(VCalendar, {}).mount('#app')
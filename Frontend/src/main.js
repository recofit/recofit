import { createApp } from 'vue'
import App from './App.vue'
import store from './store'
import router from './router'

import 'bootstrap'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap-icons/font/bootstrap-icons.css'


createApp(App).use(router).use(store).mount('#app')
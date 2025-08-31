import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../components/Home.vue'
import WikiView from '../components/Wiki.vue'
import InstallView from '../components/Install.vue'
import BugsView from '../components/Bugs.vue'
import ConfigView from '../components/Config.vue'

const routes = [
  {
    path: '/',
    name: 'Home',
    component: HomeView,
  },
  {
    path: '/wiki',
    name: 'Wiki',
    component: WikiView,
  },
  {
    path: '/wiki/install',
    name: 'Install',
    component: InstallView,
  },
  {
    path: '/wiki/bugs',
    name: 'Bugs',
    component: BugsView,
  },
  {
    path: '/wiki/config',
    name: 'Config',
    component: ConfigView,
  },
]

const router = createRouter({
  history: createWebHistory('/No-More-Popups/'),
  routes,
})

export default router

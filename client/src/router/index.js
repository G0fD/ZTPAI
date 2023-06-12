import {createRouter, createWebHashHistory} from 'vue-router'
import WelcomeView from '../views/WelcomeView.vue'
import LoginView from '../views/LoginView.vue'
import RegisterView from '../views/RegisterView.vue'
import SiteView from '../views/SiteView.vue'
import ProfileView from '../views/ProfileView.vue'
import AddSongView from "@/views/AddSongView.vue";
import LogoutView from "@/views/LogoutView.vue";
import MatchesView from "@/views/MatchesView.vue";

const router = createRouter({
  history: createWebHashHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'welcome',
      component: WelcomeView
    },
    {
      path: '/login',
      name: 'login',
      component: LoginView
    },
    {
      path: '/register',
      name: 'register',
      component: RegisterView
    },
    {
      path: '/site',
      name: 'site',
      component: SiteView
    },
    {
      path: '/profile',
      component: ProfileView,
      children: [
        {
          path: '',
          name: 'profile',
          component: LogoutView
        },
        {
          path: 'addSong',
          name: 'addSong',
          component: AddSongView
        },
        {
          path: 'myMatches',
          name: 'myMatches',
          component: MatchesView
        }
      ]
    },
  ]
})

export default router

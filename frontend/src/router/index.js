/**
 * router/index.ts
 *
 * Automatic routes for `./src/pages/*.vue`
 */

import { useAuthStore } from '@/store/auth' 
import { createRouter, createWebHistory } from 'vue-router/auto'

// COmentado para facilitar a declaracao das rodas com a propriedade requiresAuth
// import { routes } from 'vue-router/auto-routes'

import Home from '@/pages/index.vue'
import Logado from '@/pages/logado.vue'
import Login from '@/pages/login.vue'

import Banco from '@/pages/banco/index.vue'
import NewBanco from '@/pages/banco/new.vue'
import EditBanco from '@/pages/banco/edit.vue'
import ViewBanco from '@/pages/banco/view.vue'


const routes = [  
  { path: '/', component: Home },
  { path: '/login', component: Login },
  { path: '/logado', component: Logado, meta: { requiresAuth: true } },
  { path: '/banco', component: Banco,  meta: { requiresAuth: true } },
  { path: '/newbanco', component: NewBanco, meta: { requiresAuth: true } },
  { path: '/viewbanco/:id', component: ViewBanco, meta: { requiresAuth: true } },
  { path: '/editbanco/:id', component: EditBanco, meta: { requiresAuth: true } },
]

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes,
})

// PROTEÇÃO DAS ROTAS
router.beforeEach((to, from, next) => {
  const auth = useAuthStore()
  console.log('auth', auth)
  console.log('to', to, to.meta.requiresAuth)
  console.log('isAuthenticated', auth.isAuthenticated)

  // As rotas que precisam de login devem ter o meta: { requiresAuth: true }
  if (to.meta.requiresAuth && !auth.isAuthenticated) {
    alert("Voce precisa logar no sistema!")
    next('/') // Redireciona para Home ou para página de login
  } else {
    next()
  }
})

// Workaround for https://github.com/vitejs/vite/issues/11804
router.onError((err, to) => {
  if (err?.message?.includes?.('Failed to fetch dynamically imported module')) {
    if (localStorage.getItem('vuetify:dynamic-reload')) {
      console.error('Dynamic import error, reloading page did not fix it', err)
    } else {
      console.log('Reloading page to fix dynamic import error')
      localStorage.setItem('vuetify:dynamic-reload', 'true')
      location.assign(to.fullPath)
    }
  } else {
    console.error(err)
  }
})

router.isReady().then(() => {
  localStorage.removeItem('vuetify:dynamic-reload')
})

export default router

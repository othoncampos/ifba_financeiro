import { defineStore } from 'pinia'
import { usuario_login } from '@/services/usuario_services'

export const useAuthStore = defineStore('auth', {
  state: () => ({
    isAuthenticated: false,
  }),
  actions: {
    async login(login, password) {
        console.log('Attempting login with', login, password)
        const response = await usuario_login(login, password)
        if (response.status === 200) {
            this.isAuthenticated = true
            console.log('Login successful') 
            return true
        }
        console.log('Login failed')
        this.isAuthenticated = false
        return false
    },
    logout() {
      this.isAuthenticated = false
    },
  },
  persist: true,
})

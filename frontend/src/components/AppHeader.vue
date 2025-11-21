<template>
    <v-app-bar app dark >
        <v-app-bar-title>Calendário</v-app-bar-title>
        <template v-slot:append>
            <button-change-theme></button-change-theme>

            <v-btn prepend-icon="mdi-login-variant" v-if="!auth.isAuthenticated" color="secondary" @click="handleLogin">
                Login
            </v-btn>
            <v-btn v-else append-icon="mdi-login-variant" color="secondary" @click="handleLogout">
                Logout
            </v-btn>
        </template>
    </v-app-bar>
</template>

<script setup>
    import { useRouter } from 'vue-router'
    import { useAuthStore } from '@/store/auth.js'
   
    const auth = useAuthStore()
    const router = useRouter()

    function handleLogin() {
        router.push('/login')
    }

    function handleLogout() {
        auth.logout()
        router.push('/')
    }
</script>

<style scoped lang="sass">
</style>

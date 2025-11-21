<template>
  <v-app>
    <v-main>
      <router-view />
    </v-main>
  </v-app>
</template>

<script setup>
    import { useThemeStore } from '@/store/theme'
    const themeStore = useThemeStore()
    themeStore.initTheme()
</script>

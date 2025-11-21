<script setup>
    import { useThemeStore } from '@/store/theme'
    const themeStore = useThemeStore()
</script>

<template>
    <v-btn @click="themeStore.toggleTheme" icon>
        <v-icon>{{ themeStore.isDark() ? 'mdi-weather-sunny' : 'mdi-weather-night' }}</v-icon>
    </v-btn>
</template>
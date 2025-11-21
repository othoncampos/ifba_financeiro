// src/stores/theme.js
import { defineStore } from 'pinia'
import { useTheme } from 'vuetify'

const THEME_KEY = 'app_theme'

export const useThemeStore = defineStore('theme', () => {
  const theme = useTheme()

  const setTheme = (newTheme) => {
    theme.global.name.value = newTheme
    localStorage.setItem(THEME_KEY, newTheme)
  }

  const toggleTheme = () => {
    const newTheme = theme.global.current.value.dark ? 'light' : 'dark'
    setTheme(newTheme)
  }

  const initTheme = () => {
    const savedTheme = localStorage.getItem(THEME_KEY)
    if (savedTheme) {
      setTheme(savedTheme)
    } else {
      // Se não tiver tema salvo, opcional: colocar claro por padrão
      setTheme('light')
    }
  }

  const isDark = () => {
    return theme.global.current.value.dark
  }

  const currentTheme = () => {
    return theme.global.name.value
  }

  return {
    toggleTheme,
    initTheme,
    isDark,
    currentTheme,
  }
})

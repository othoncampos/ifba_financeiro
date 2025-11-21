<template>
  <v-app>
    <!-- Cabeçalho -->
    <AppHeader/>

    <!-- Conteúdo principal -->
    <v-main>
      <v-container class="fill-height" fluid>
        <slot />
      </v-container>
    </v-main>

    <!-- Rodapé -->
    <AppFooter/>
  </v-app>
</template>

<script setup>
</script>

<style scoped>
</style>

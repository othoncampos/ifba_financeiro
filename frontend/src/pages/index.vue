<template>
  <v-app>
    <!-- Cabeçalho -->
    <AppHeader/>

    <!-- Corpo -->
    <v-main>
      <v-container class="fill-height" fluid>
        <v-row align="center" justify="center">
          <v-col cols="12" md="8" class="text-center">
            <h1>Bem-vindo ao meu aplicativo!</h1>
            <p>Este é o conteúdo principal da página.</p>
          </v-col>
        </v-row>
      </v-container>
    </v-main>

    <!-- Rodapé -->
    <AppFooter/>
  </v-app>
</template>

<script setup>

</script>

<style scoped>
  h1 {
    margin-bottom: 16px;
  }
</style>

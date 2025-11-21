<template>
  <v-app>
    <AppHeader/>
    <AppNavigation/>
    <v-main>
      <v-breadcrumbs :items="['Foo', 'Bar', 'Fizz']"></v-breadcrumbs>
      <v-container fluid>
        <slot />
      </v-container>
    </v-main>
    <AppFooter/>
  </v-app>
</template>

<script setup>
</script>

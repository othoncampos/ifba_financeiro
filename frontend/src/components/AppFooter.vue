<template>
  <v-footer app height="40">
    <a
      v-for="item in items"
      :key="item.title"
      class="d-inline-block mx-2 social-link"
      :href="item.href"
      rel="noopener noreferrer"
      target="_blank"
      :title="item.title"
    >
      <v-icon
        :icon="item.icon"
        :size="item.icon === '$vuetify' ? 24 : 16"
      />
    </a>

    <div class="text-caption text-disabled" style="position: absolute; right: 16px;">
      &copy; 2025-<span class="d-none d-sm-inline-block">IFBA-Paulo Afonso</span>
    </div>
  </v-footer>
</template>

<script setup>
  const items = [
    {
      title: 'IFBA Paulo Afonso - @ifba.pauloafonso',
      icon: 'mdi-instagram',
      href: 'https://www.instagram.com/ifba.pauloafonso/',
    },
    {
      title: 'IFBA Paulo Afonso - Site',
      icon: `mdi-web`,
      href: 'https://portal.ifba.edu.br/paulo-afonso',
    },
  ]
</script>
<style scoped lang="sass">
  .social-link :deep(.v-icon)
    color: rgba(var(--v-theme-on-background), var(--v-disabled-opacity))
    text-decoration: none
    transition: .2s ease-in-out

    &:hover
      color: rgba(25, 118, 210, 1)
</style>

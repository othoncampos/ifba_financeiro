<template>
      <v-navigation-drawer expand-on-hover rail >
        <v-list>
          <v-list-item
            prepend-avatar="https://randomuser.me/api/portraits/women/85.jpg"
            subtitle="sandra_a88@gmailcom"
            title="Sandra Adams"
          ></v-list-item>
        </v-list>

        <v-divider></v-divider>
        
        <v-list density="compact" nav>
          <v-list-item
            v-for="item in items"
            :key="item.value"
            :prepend-icon="item.icon"
            :title="item.title"
            :disabled="item.disabled"
            @click="!item.disabled && itemSelecionado(item.value)"
          ></v-list-item>
        </v-list>
      </v-navigation-drawer>

      
</template>

<script setup>
  import { useRouter } from 'vue-router'

  const router = useRouter()

  const items = [
    { icon: 'mdi-view-dashboard-variant-outline', title: 'Inicio', value: 'logado' },
    { icon: 'mdi-bank-outline', title: 'Bancos', value: 'banco' },
    // { icon: 'mdi-home-group', title: 'Estados', value: 'uf' },
    // { icon: 'mdi-home', title: 'Cidades', value: 'cidade' },
    // { icon: 'mdi-library-outline', title: 'Campi', value: 'campi'},
    // { icon: 'mdi-google-classroom', title: 'Cursos', value: 'curso', disabled: false},
    // { icon: 'mdi-human-male-female', title: 'Turmas', value: 'turma', disabled: false},
    // { icon: 'mdi-door', title: 'Salas', value: 'sala'},
    // { icon: 'mdi-library', title: 'Disciplina', value: 'disciplina'},
    // { icon: 'mdi-cast-education', title: 'Formas de Ensino', value: 'forma_ensino', disabled: false},
    // { icon: 'mdi-calendar-plus', title: 'Calendários', value: 'calendario', disabled: false},
    // { icon: 'mdi-account-tie', title: 'Professor', value: 'professor'},
    // { icon: 'mdi-school', title: 'A', value: 'a', disabled: true},
    // { icon: 'mdi-star', title: 'Starred', value: 'starred', disabled: true},
    // { icon: 'mdi-account-box-plus-outline', title: 'Tipo de Evento', value: 'tipo_evento', disabled: false},

  ]
  
  function itemSelecionado(item) {
    router.push(`/${item}`)
  }
</script>
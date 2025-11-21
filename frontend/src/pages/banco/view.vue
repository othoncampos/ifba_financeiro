<template>
    <AdminLayout>
        <v-card flat style="width: 98%;">  
            <v-card-title class="d-flex align-center pe-2">
                <v-icon icon="mdi-city"></v-icon> &nbsp; Visualizar Estado&nbsp;&nbsp;
                <v-spacer></v-spacer>
            </v-card-title>
            <v-divider></v-divider>
            <v-card-text>
                <v-list dense>
                    <v-list-item>
                        <v-list-item-title><strong>Codigo:</strong> {{ codigo }}</v-list-item-title>
                    </v-list-item>
                    <v-list-item>
                        <v-list-item-title><strong>Nome:</strong> {{ nome }}</v-list-item-title>
                    </v-list-item>
                    <v-list-item>
                        <v-list-item-title><strong>URL:</strong> {{ url }}</v-list-item-title>
                    </v-list-item>
                </v-list>
                <v-btn flat color="teal" variant="outlined" class="me-4" @click="handlGoBack">Voltar</v-btn>    
            </v-card-text>
        </v-card>
    </AdminLayout>
</template>

<script setup>
    import { ref, onMounted } from 'vue'
    import { useRouter, useRoute } from 'vue-router'
    import { banco_find_by_id } from '@/services/banco_services'

    const router = useRouter()
    const route = useRoute()

    const id = ref('')
    const codigo = ref('')
    const nome = ref('')
    const url = ref('')


     onMounted(async () => {
        const id_param = route.params.id    
        const response = await banco_find_by_id(id_param)
        console.log('banco.view.onMounted', response)
        id.value = response.data.id_banco
        codigo.value = response.data.codigo
        nome.value = response.data.nome
        url.value = response.data.url
    })
    
    function handlGoBack() {
        router.push('/banco')
    }
</script>

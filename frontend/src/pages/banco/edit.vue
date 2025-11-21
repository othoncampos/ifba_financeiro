<template>
    <AdminLayout>
        <v-card flat style="width: 98%;">  
            <v-card-title class="d-flex align-center pe-2">
                <v-icon icon="mdi-city"></v-icon> &nbsp; Editar Estado&nbsp;&nbsp;
                <v-spacer></v-spacer>
            </v-card-title>
            <v-divider></v-divider>
            <v-card-text>
                <form @submit.prevent="submit">
                    <!-- <v-text-field v-model="id" label="ID" disabled="true" ></v-text-field> -->
                    <v-text-field v-model="codigo" :rules="numeroRules" label="Codigo"></v-text-field>
                    <v-text-field v-model="nome" :rules="nomeRules" label="Nome"></v-text-field>
                    <v-text-field v-model="url" :rules="urlRules" label="URL"></v-text-field>

                    <v-btn flat color="teal" class="me-4" type="submit">Salvar</v-btn>
                    <v-btn flat color="teal" variant="outlined" class="me-4" @click="handlGoBack">Voltar</v-btn>
                </form>
            </v-card-text>
        </v-card>
    </AdminLayout>
</template>

<script setup>
    import { ref, onMounted } from 'vue'
    import { useRouter, useRoute } from 'vue-router'
    import { banco_find_by_id, banco_update } from '@/services/banco_services'


    const router = useRouter()
    const route = useRoute()

    const id = ref('')
    const codigo = ref('')
    const nome = ref('')
    const url = ref('')

    onMounted(async () => {
        const id_param = route.params.id    
        const response = await banco_find_by_id(id_param)
        console.log('banco.edit.onMounted', response)
        id.value = response.data.id_banco
        codigo.value = response.data.codigo
        nome.value = response.data.nome
        url.value = response.data.url
    })
    
    const numeroRules = [
        v => (!!v && /^[0-9]+$/.test(v)) || 'O valor deve conter apenas números.'
    ];

    const nomeRules = [
        v => (!!v && v.length >= 3) || 'Nome deve ter pelo menos 3 caracteres.'
    ]

    const urlRules = [
        v =>
            (!!v && /^(https?:\/\/)([\w.-]+)(:[0-9]+)?(\/.*)?$/i.test(v)) ||
            'Informe uma URL válida, começando com http:// ou https://.'
    ];


    function submit() {
        let formData = {
            id_banco: id.value,
            codigo: codigo.value,
            nome: nome.value,
            url: url.value,
        } 
        banco_update(formData)
        .then( resp => {
            router.push('/banco')
        });
    }

    function handlGoBack() {
        router.push('/banco')
    }
</script>

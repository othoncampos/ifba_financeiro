<template>
    <AdminLayout>
        <v-card flat style="width: 98%;">  
            <v-card-title class="d-flex align-center pe-2">
                <v-icon icon="mdi-bank-outline"></v-icon> &nbsp; Cadastro de Banco&nbsp;&nbsp;
                <v-spacer></v-spacer>
            </v-card-title>
            <v-divider></v-divider>
            <v-card-text>
                <form @submit.prevent="submit">
                    <!-- <v-text-field v-model="id" :rules="idRules" label="ID"></v-text-field> -->
                    <v-text-field v-model="codigo" :rules="codigoRules" label="Codigo"></v-text-field>
                    <v-text-field v-model="nome" :rules="nomeRules" label="Nome"></v-text-field>
                    <v-text-field v-model="url" :rules="urlRules" label="URL"></v-text-field>

                    <v-btn flat color="teal" class="me-4" type="submit">Salvar</v-btn>
                    <v-btn variant="outlined" class="me-4" @click="handleReset">clear</v-btn>
                    <v-btn flat color="teal" variant="outlined" class="me-4" @click="handlGoBack">Voltar</v-btn>
                </form>
            </v-card-text>
        </v-card>
    </AdminLayout>
</template>

<script setup>
    
    import { ref } from 'vue'
    import { useRouter } from 'vue-router'
    import { banco_add } from '@/services/banco_services';

    const router = useRouter()

    const codigo = ref('')
    const nome = ref('')
    const url = ref('')

  
    const codigoRules = [
        v => (!!v && /^\d+$/.test(v)) || 'ID deve conter apenas números.',
        v => (!!v && v.length >= 2) || 'ID deve ter pelo menos 2 dígitos.'
    ]

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
            codigo: codigo.value,
            nome: nome.value,
            url: url.value,
        } 
        banco_add(formData)
        .then( resp => {
            router.push('/banco')
        })
    }

    function handleReset() {
        codigo.value = ''
        url.value = ''
        nome.value = ''
    }

    function handlGoBack() {
        router.push('/banco')
    }
</script>
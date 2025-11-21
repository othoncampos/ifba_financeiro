<template>
    <AdminLayout>
        <v-card flat style="width: 95%;">


        <v-alert
            v-if="alertaErro"
            type="error"
            class="ma-4"
            border="start"
            variant="tonal"
            closable
            @click:close="alertaErro = false"
          >
            Não foi possível deletar o uf. Verifique se ele está vinculado a outros registros.
        </v-alert>


            <v-card-title class="d-flex align-center pe-2">
                <v-icon icon="mdi-city"></v-icon> &nbsp; Bancos&nbsp;&nbsp;
                <v-spacer></v-spacer>
                <v-btn variant="flat" color="success" @click="handleNew">Novo</v-btn>&nbsp;
                &nbsp;&nbsp;
                <v-text-field
                    v-model="search"
                    density="compact"
                    label="Search"
                    prepend-inner-icon="mdi-magnify"
                    variant="solo-filled"
                    flat
                    hide-details
                    single-line
                    clearable
                ></v-text-field>
            </v-card-title>


            <v-divider></v-divider>
            <v-data-table :headers="headers" :items="filtered">
                <template v-slot:item.actions="{ item }">
                    <v-btn size="small" icon color="info" @click="viewItem(item)">
                        <v-icon>mdi-eye</v-icon> <!-- Visualizar -->
                        <v-tooltip activator="parent" location="top">Visualizar: {{item.nome}}</v-tooltip>
                    </v-btn>
                    &nbsp;
                    <v-btn size="small" icon color="primary" @click="editItem(item)">
                        <v-icon>mdi-pencil</v-icon>
                        <v-tooltip activator="parent" location="top">Editar: {{item.nome}}</v-tooltip>
                    </v-btn>
                    &nbsp;
                    <v-btn size="small" icon color="error" @click="deleteItem(item)">
                        <v-icon>mdi-delete</v-icon> <!-- Deletar -->
                        <v-tooltip activator="parent" location="top">Deletar: {{item.nome}}</v-tooltip>
                    </v-btn>
                </template>
            </v-data-table>
        </v-card>
    </AdminLayout>
</template>


<script setup>
    import { ref, computed, onMounted } from 'vue'
    import { useRouter } from 'vue-router'


    import { banco_lista_all, banco_delete } from '@/services/banco_services';

    const router = useRouter()
    const alertaErro = ref(false)


    onMounted(() => {
        atualizar()
    })


    const itens = ref([])
    const search = ref('')


    const headers = [
        { align: 'start', key: 'codigo', title: 'Código', sortable: true},
        { key: 'nome', title: 'Nome', sortable: true },
        { key: 'url', title: 'URL', sortable: true },
        { key: 'actions', title: 'Ações', sortable: false }
    ]
   
    const filtered = computed(() => {
        if (!search.value) return itens.value
       
        const searchLower = search.value.toLowerCase()
        return itens.value.filter(item =>
            item.nome.toLowerCase().includes(searchLower)
        )
    })


    function atualizar() {
        banco_lista_all()
        .then( resp => {
            console.log('banco.atualizar_bancos', resp)
            itens.value = resp.data
        })
        .catch(err => {
            console.error('Erro ao atualizar bancos:', err)
        });
    }


    function editItem(item) {
        // alert('Editar Item: ' + item.id_banco)
        router.push(`/editbanco/${item.id_banco}`)
    }


    async function deleteItem(item) {
        if (confirm(`Tem certeza que deseja deletar o banco: ${item.nome}?`)) {
            alertaErro.value = false


            try {
                const sucesso = await banco_delete(item.id_banco);
                if (sucesso) {
                    atualizar();
                } else {
                    alertaErro.value = true
                    setTimeout(() => {
                    alertaErro.value = false
                    }, 4000)
                }
            } catch (err) {
                console.error('Erro ao deletar:', err)
                alertaErro.value = true
                setTimeout(() => {
                    alertaErro.value = false
                }, 4000)
            }
        }
    }


    function viewItem(item) {
        alert('Visualizar Item: ' + item.id_banco)
        router.push(`/viewbanco/${item.id_banco}`)
    //   console.log('Visualizar', item)


    }


    function handleNew() {
        router.push('/newbanco')
    }
</script>
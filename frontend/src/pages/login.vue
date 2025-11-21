<template>
  <v-container fluid class="d-flex align-center justify-center fill-height">
  
    <v-card class="pa-0 d-flex flex-row rounded-xl overflow-hidden" style="width: 70%;">
      <!-- Left Side -->
      <v-col cols="6" class="pa-8 d-flex flex-column justify-center">
        <v-img
          src="/team.png"
          alt="Login Illustration"
          contain
          height="150"
          class="mb-6"
        ></v-img>
        <h2 class="text-h5 text-center font-weight-bold mb-6">Login</h2>
        <v-form @submit.prevent="handleSubmit">
          <v-text-field
            v-model="email"
            label="e-mail"
            type="email"
            required
            outlined
            dense
            class="mb-4"
          ></v-text-field>
          <v-text-field
            v-model="password"
            label="Senha"
            type="password"
            required
            outlined
            dense
            class="mb-4"
          ></v-text-field>
          <v-btn
            type="submit"
            color="blue darken-1"
            class="white--text font-weight-bold"
            size="x-large"
            variant="flat"
            block
          >Login
          </v-btn>
        </v-form>
        
        <v-btn class="white--text mt-4"
            size="x-large"
            variant="flat"
            block
          >Esqueceu a senha?
          </v-btn>
      </v-col>
      <!-- Right Side -->
      <v-col cols="6" 
        class="d-flex flex-column justify-center align-center pa-8 text-center"
        style="background: linear-gradient(to right, #42a5f5, #1e88e5); color: white;"
      >
        <h2 class="text-h4 font-weight-bold mb-4">Bem vindo!</h2>
        <p class="mb-6">Ainda não possui conta? <br />Faça seu cadastro aqui.</p>
        <v-btn color="white" class="blue--text text--darken-2 font-weight-bold rounded-pill" outlined>
          SIGN UP
        </v-btn>
      </v-col>
      
    </v-card>
  </v-container>
</template>

<script setup>
    import { ref } from 'vue'
    import { useRouter } from 'vue-router'
    import { useAuthStore } from '@/store/auth.js'
    const auth = useAuthStore()
    const router = useRouter()

    const email = ref('')
    const password = ref('')

    const handleSubmit = async () => {
        const sucess = await auth.login(email.value, password.value)
        if (!sucess) {
            alert('Login falhou. Verifique suas credenciais.')
            return
        }
        router.push('/logado')
    }
</script>

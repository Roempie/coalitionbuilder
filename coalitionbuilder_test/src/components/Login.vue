<template>
  <div class="text-h3 text-center mb-6" width="300px">Login</div>
  <v-sheet width="300" class="mx-auto">
    <v-form validate-on="submit lazy" @submit.prevent="submit">
      <v-text-field
        v-model="user.email"
        label="Email"
        type="email"
        variant="underlined"
        required
      ></v-text-field>

      <v-text-field
        v-model="user.password"
        label="Password"
        type="password"
        variant="underlined"
        required
      ></v-text-field>
      <span v-if="failedLogin" class="text-caption text-red-darken-3"><b>Email or password is incorrect.</b></span>      

      <v-btn :disabled="isButtonDisabled" :loading="loading" type="submit" block class="mt-2">Login</v-btn>
    </v-form>
    <router-link style="text-decoration: none; color: inherit;" to="/register"><div class="text-caption text-right mt-10">Sign up</div> </router-link>
    
  </v-sheet>
   
  
</template>
 
 <script>
 import UserService from '../services/UserService';
 
 export default {
   name: "Login",
   data() {
    return {
        user: {
          email: '',
          password: ''
        },
        loading: false,
        timeout: null,
        failedLogin: false
    }
   },
   methods: {
     async submit() {
      try {
        this.loading = true;
        let response = await UserService.authenticate(this.user);
        this.loading = false;
        let token = response.data.token;

        localStorage.clear();
        localStorage.setItem("token", token);
        localStorage.setItem("user_email", this.user.email);
        // navigate to a protected resource 
        this.$router.push("/dashboard");
      } catch (err) {
        this.failedLogin = true;
        this.loading = false;
        console.log(err.response);
      }
     }
   },
   computed: {
    isButtonDisabled() {
        this.failedLogin = false;
        return this.user.email.trim().length === 0 || this.user.password.trim().length === 0
    }
   }
 };
 </script>
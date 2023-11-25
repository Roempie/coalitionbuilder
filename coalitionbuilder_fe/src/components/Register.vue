<template>
  <div class="text-h3 text-center mb-6" width="300px">Sign up</div>
  <v-sheet width="300" class="mx-auto">
    <v-form ref="form" validate-on="submit lazy" @submit.prevent="submit">
      <v-text-field
        v-model="user.email"
        label="Email"
        type="email"
        variant="underlined"
        :rules="emailRules"
        required
      ></v-text-field>

      <v-text-field
        v-model="user.firstname"
        label="Firstname"
        variant="underlined"
        required
      ></v-text-field>

      <v-text-field
        v-model="user.lastname"
        label="Lastname"
        variant="underlined"
        required
      ></v-text-field>

      <v-text-field
        v-model="user.password"
        label="Password"
        type="password"
        variant="underlined"
        :rules="passwordRules"
        required
      ></v-text-field>

      <v-text-field
        v-model="user.repeatedPassword"
        label="Repeat password"
        type="password"
        variant="underlined"
        :rules="repeatedPasswordRules"
        required
      ></v-text-field>


      <v-btn :disabled="isButtonDisabled" :loading="loading" type="submit" block class="mt-2">Register</v-btn>
    </v-form>
  </v-sheet>
   
  
</template>
 
 <script>
 import UserService from '../services/UserService';

 export default {
   name: "Register",
   data() {
    return {
        isFormValid: false,
        loading: false,
        timeout: null,
        user: {
          email: '',
          firstname: '',
          lastname: '',
          password: '',
          repeatedPassword: ''
        },
        emailRules: [
          value => {
            if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(value)) return true
            return 'Email is not valid.'
          }
        ],
        passwordRules: [
          value => {
            if(/^(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{6,16}$/.test(value)) return true
            return 'At least 1 number and 1 special character.'
          }
        ],
        repeatedPasswordRules: [
          value => {
            console.log(value)
            console.log(this.user.password)
            if(value == this.user.password) return true;

            return 'Passwords are not the same.';
          }
        ]
    }
   },
   methods: {
     async submit() {
      
      const result = await this.$refs.form.validate();
      if (!result.valid) {
        return;
      }
    
      try {
        this.loading = true;
        let response = await UserService.register(this.user);
        this.loading = false;
        console.log(response.data)
        // navigate to a protected resource 
        this.$router.push("/login");
      } catch (err) {
        console.log(err)
        console.log(err.response);
      }

     }
   },
   computed: {
    isButtonDisabled() {
        return this.user.email.trim().length === 0 || this.user.password.trim().length === 0 || this.user.firstname.trim().length === 0 || this.user.lastname.trim().length === 0
    }
   }
 };
 </script>
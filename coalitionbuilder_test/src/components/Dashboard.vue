<template>
    <h1>Dashboard</h1>
    <div v-if="user">{{ user }}</div>
</template>


<script>
import UserService from '../services/UserService';
import { onMounted } from 'vue';

import { ref } from 'vue';

export default {
 name: "Dashboard",
 setup() {
   const user = ref(null);

   onMounted(async () => {
     let response = await UserService.info(localStorage.getItem("user_email"), localStorage.getItem("token"))
     user.value = response.data;
   });

   return { user };
 },
};

</script>
<template>
  <v-app>
    <v-navigation-drawer app></v-navigation-drawer>
    <v-app-bar app title="Coalitiekwartet"></v-app-bar>
      <v-main>
        <v-container class="ma-6">
          <router-view/>
        </v-container>
      </v-main>
    <v-footer app></v-footer>
  </v-app>
</template>

<script>
import ParliamentSeats from './components/ParliamentSeats.vue'
import Login from './components/Login.vue';

export default {
  name: 'App',

  components: {
    ParliamentSeats,
    Login
  },
  data: () => ({
    //
  }),
}
</script>

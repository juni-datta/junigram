<template>
  <v-app>
        <v-app-bar app>
            <v-toolbar-title class="headline text-uppercase">
                <span>Juni</span>
                <span class="font-weight-light">gram</span>
            </v-toolbar-title>
            <v-spacer></v-spacer>
        </v-app-bar>
        <v-content>
            <router-view />
        </v-content>
    </v-app>

    
</template>

// <script>
    export default {
        name: 'App',
        data() {
            return {
                currentUser: ""
                }
            }
        }
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
}
html,
body {
  margin: 0;
}
.fab-button {
  background: linear-gradient(
    45deg,
    #405de6,
    #5851db,
    #833ab4,
    #c13584,
    #e1306c,
    #fd1d1d
  );
}
</style>

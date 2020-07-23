<template>
    <v-app>
        <v-app-bar app>
            <v-toolbar-title class="headline text-uppercase">
                <span>Assignment 2</span>
                <span class="font-weight-light"> Instagram</span>
            </v-toolbar-title>
            <v-spacer></v-spacer>
        </v-app-bar>
        <v-content>
            <Instagram/>
        </v-content>
    </v-app>
</template>

<script>
    export default {
        name: 'App',
        data() {
            return {
                authenticated: false,
                mockAccount: {
                    username: "nraboy",
                    password: "password"
                }
            }
        },
        mounted() {
            if(!this.authenticated) {
                this.$router.replace({ name: "login" });
            }
        },
        methods: {
            setAuthenticated(status) {
                this.authenticated = status;
            },
            logout() {
                this.authenticated = false;
            }
        }
    }
</script>

<style scoped>
    .fab-button {
        background:linear-gradient(45deg, #405de6, #5851db, #833ab4, #c13584, #e1306c, #fd1d1d);
    }
</style>

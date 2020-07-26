<template>
    <div id="login">
        <h1>Login</h1>
        <input type="text" name="username" v-model="input.login.username" placeholder="Username" />
        <input type="password" name="password" v-model="input.login.password" placeholder="Password" />
        <button type="button" v-on:click="login">Login</button>
        <p id="login_message"></p>

        <h1>Create new user</h1>
        <input type="text" name="username" v-model="input.register.username" placeholder="Username" />
        <input type="password" name="password" v-model="input.register.password" placeholder="Password" />
        <button type="button" v-on:click="create_user()">Login</button>
        <p id="register_message"></p>
    </div>

    
</template>


<script>
    export default {
        name: 'Login',
        data() {
            return {
                input: {
                    login: 
                    {
                    username: "",
                    password: ""
                    },
                    register:
                    {
                      username: "",
                      password: ""
                    }
                },
                info: ""
            }
        },
        methods: {
            login() {    //add username and password as parameters here later
                window.console.log(this.input.login.username)
                window.console.log(this.input.login.password)
                this.axios.post('http://localhost:9090/login/login', {
                username: this.input.login.username,
                password: this.input.login.password
                },{})
                .then(res => {
                  window.console.log(res)
                  if(res.data == "success"){
                    this.$parent.currentUser = this.input.login.username
                    this.$router.push('/')
                  }
                  else{
                    document.getElementById("login_message").innerHTML = "Incorrect username and password combination"
                    }
                  }
                  )
                .catch(function (error) {window.console.log(error)});
            },
            create_user(){
                this.axios.post('http://localhost:9090/user/create_user', {
                username: this.input.register.username,
                password: this.input.register.password
                },{})
                .then(res => {
                  if(res.data == "success"){
                  this.$router.push('/');
                  }
                  else if(res.data == "user_exists"){
                    document.getElementById("register_message").innerHTML = "User already exists"
                  }
                  else{
                    document.getElementById("register_message").innerHTML = "Failed to create new user"
                  }

                  })
                .catch(function (error) {window.console.log(error)});



                


            }
        }
    }
</script>

<style scoped>
    #login {
        width: 500px;
        border: 1px solid #CCCCCC;
        background-color: #FFFFFF;
        margin: auto;
        margin-top: 200px;
        padding: 20px;
    }
</style>
<template>
  <div class="container-v">
    <div id="login">
        <h1>Welcome {{this.$parent.currentUser}}</h1>
    </div>
    <v-container>
      <v-card class="card-form">
        <v-card-text>
          <v-form class="px-3">
            <v-text-field
              label="Caption"
              v-model="caption"
            ></v-text-field>
            <v-text-field
              label="Image Source"
              v-model="src"
              required
            ></v-text-field>
            <v-btn class="mr-4" @click="submit">submit</v-btn>
          </v-form>
        </v-card-text>
      </v-card>
      <v-row v-for="card in cards" :key="card.id">
        <v-card class="mx-auto">
          <h1> {{card.user}} </h1>
          <v-img :src="card.src" class="image">
          </v-img>
          <v-card-text class="card-caption"><b>{{ card.caption }}</b>
            <v-card-actions>
              <div class="flex-grow-1"></div>
              <label> {{card.like_string}} </label>
              <v-btn v-blur v-if=temp icon color="pink" @click="likeImage(card)">
                <v-icon>mdi-heart</v-icon>
              </v-btn>
              <v-btn v-blur v-else icon @click="likeImage(card)">
                <v-icon>mdi-heart</v-icon>
              </v-btn>
              <v-btn icon @click="destroyCard(card)">
                <v-icon>mdi-delete</v-icon>
              </v-btn>
            </v-card-actions>
          </v-card-text>
          <ul class="comment-list">
            <li v-for="com in card.comments" :key="com.comment">
              <div class="view">
                <label><b>{{com.user}}:</b> {{ com.comment }}</label>
                <v-btn class="destroy" icon @click="destroyComment(card, com)">
                  <v-icon>mdi-close</v-icon>
                </v-btn>
              </div>
            </li>
          </ul>
          <label>
            <input
              class="new-comment"
              v-model="cards[cards.indexOf(card)].comment"
              placeholder="Comment here.."
              autofocus
              @keyup.enter="addComment(card)">
          </label>
        </v-card>
      </v-row>
    </v-container>
  </div>
</template>

<script>
  export default {
    name: "Instagram",
    data() {
      return {
        temp: true,
        info: '',
        caption: '',
        src: '',
        cards: [],
      }
    },
    mounted() {

      if (this.$parent.currentUser == null){
        this.$router.push('/login');
        window.console.log("should redirect to login")
      }
      else{
        window.console.log("currentUser: " + this.$parent.currentUser)
        this.axios.get('http://localhost:9090/post/get_posts')
                .then(res => {
                  window.console.log(res.data[0])
                  this.cards = res.data
                  }
                  )
                .catch(function (error) {window.console.log(error)});

        }

      
    },
    methods: {
      submit() {
        this.axios.post('http://localhost:9090/post/create_post', {
          user: this.$parent.currentUser,
          text: this.caption,
          src: this.src
          },{})
         .then(res => {
          window.console.log(res.data)
          this.axios.get('http://localhost:9090/post/get_posts')
          .then(res => {
          window.console.log(res.data[0])
          this.cards = res.data
          }
          )
        .catch(function (error) {window.console.log(error)});
          });

        this.caption = ''
        this.src = ''


      },
      destroyCard(card) {

        //remove from back end
        this.axios.post('http://localhost:9090/post/delete_post', {
          id: card.id,
          username: this.$parent.currentUser
        })
        .then(res => {
          window.console.log(res.data)
          this.axios.get('http://localhost:9090/post/get_posts')
          .then(res => {
          window.console.log(res.data[0])
          this.cards = res.data
          }
          )
        .catch(function (error) {window.console.log(error)});
          });

      },
      likeImage(card) {
        this.axios.post('http://localhost:9090/post/like_post', {
        username: this.$parent.currentUser,
        id: card.id,
        })
        .then(res => {
          window.console.log(res.data)
          this.axios.get('http://localhost:9090/post/get_posts')
          .then(res => {
          window.console.log(res.data[0])
          this.cards = res.data
          }
          )
        .catch(function (error) {window.console.log(error)});
          });

      },
      destroyComment(card, com) {
        window.console.log(com.id)
        this.axios.post('http://localhost:9090/post/delete_comment', {
          id: com.id,
          username: this.$parent.currentUser
        })
        .then(res => {
          window.console.log(res.data)
          this.axios.get('http://localhost:9090/post/get_posts')
          .then(res => {
          window.console.log(res.data[0])
          this.cards = res.data
          }
          )
        .catch(function (error) {window.console.log(error)});
          });

          
        


    
      },
      addComment(card) {
        window.console.log(card.id)
        this.axios.post('http://localhost:9090/post/comment_post', {
        username: this.$parent.currentUser,
        id: card.id,
        comment: this.cards[this.cards.indexOf(card)].comment
        })
        .then(res => {
          window.console.log(res.data)
          this.axios.get('http://localhost:9090/post/get_posts')
          .then(res => {
          window.console.log(res.data[0])
          this.cards = res.data
          }
          )
        .catch(function (error) {window.console.log(error)});
          });

      
        this.cards[this.cards.indexOf(card)].comment = '';
      }
    }
  }
</script>

<style scoped>
  .card-form {
    width: 100%;
  }
  .mx-auto {
    margin-top: 10px;
    max-width: 614px;
    width: 100%;
  }
  .card-caption {
    font-size: 15pt;
  }
  .comment-list {
    margin: 0;
    padding: 0;
    list-style: none;
  }
  .comment-list li {
    font-size: 12pt;
    position: relative;
    border-bottom: 1px solid #ededed;
  }
  .comment-list li label {
    padding: 15px 40px;
    display: block;
    line-height: 1.2;
  }
  .comment-list li .destroy {
    position: absolute;
    top: 0;
    right: 10px;
    bottom: 0;
    width: 40px;
    height: 50px;
    font-size: 30px;
  }
  .new-comment {
    position: relative;
    margin: 0;
    width: 100%;
    font-size: 12pt;
    font-family: inherit;
    font-weight: inherit;
    line-height: 1.4em;
    color: inherit;
    padding: 6px;
    box-shadow: inset 0 -1px 5px 0 rgba(0, 0, 0, 0.2);
    box-sizing: border-box;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
  }
</style>
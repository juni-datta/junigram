<template>
  <div class="container-v">
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
          <v-img :src="card.src" class="image">
          </v-img>
          <v-card-text class="card-caption">{{ card.caption }}
            <v-card-actions>
              <div class="flex-grow-1"></div>
              <v-btn v-blur v-if=card.liked icon color="pink" @click="likeImage(card)">
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
                <label>{{ com.comment }}</label>
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
        caption: '',
        src: '',
        cards: [
          {
            comment: '',
            caption: 'Pre-fab homes',
            liked: true,
            src: 'https://thumbs-prod.si-cdn.com/pJr3bbcye9mBiE910wovzd0Yakk=/800x600/filters:no_upscale()/https://public-media.si-cdn.com/filer/96/ea/96ea3332-4453-4baf-9e8a-b187cdbe3eaf/penguin_42-55455098.jpg',
            comments: [
              { comment: 'Penguin is a friendly, confident student who is not afraid to participate in class or engage one-on-one with the teacher. He is attentive and well behaved in class and follows instructions well. Penguin has exhibited a vital understanding of the Penguin material we have covered in class. He speaks in clear sentences. His reading skills are very good for his level. His writing skills are excellent for his level. I enjoy having Penguin in my class.' },
              { comment: 'When he is confident about the answer.' },
              { comment: 'He loves participating in class.' },
              { comment: 'He is usually quite cheerful in class.' }
            ]
          },
          {
            comment: '',
            caption: 'Favorite road trips',
            liked: false,
            src: 'http://wfiles.brothersoft.com/t/three-penguin_116275-800x600.jpg',
            comments: [
              { comment: 'Penguin is a friendly, confident student who is not afraid to participate in class or engage one-on-one with the teacher. He is attentive and well behaved in class and follows instructions well. Penguin has exhibited a vital understanding of the Penguin material we have covered in class. He speaks in clear sentences. His reading skills are very good for his level. His writing skills are excellent for his level. I enjoy having Penguin in my class.' },
              { comment: 'He is usually quite cheerful in class.' }
            ]
          },
          {
            comment: '',
            caption: 'Best airlines',
            liked: false,
            src: 'https://thumbs-prod.si-cdn.com/6IcdfzLNu2NH5QwSZjNRvhrHXZk=/800x600/filters:no_upscale()/https://public-media.si-cdn.com/filer/7b/80/7b80472f-3383-4946-a38c-c01b15a087b2/42-67137516.jpg',
            comments: [
              { comment: 'Penguin is a friendly, confident student who is not afraid to participate in class or engage one-on-one with the teacher. He is attentive and well behaved in class and follows instructions well. Penguin has exhibited a vital understanding of the Penguin material we have covered in class. He speaks in clear sentences. His reading skills are very good for his level. His writing skills are excellent for his level. I enjoy having Penguin in my class.' },
              { comment: 'When he is confident about the answer.' },
              { comment: 'He loves participating in class.' }
            ]
          },
        ],
      }
    },
    mounted() {
      // Load local storage.
      const data = JSON.parse(localStorage.getItem('cards'));
      if (data) {
        this.cards = data;
      }
    },
    methods: {
      submit() {
        // this.cards.push({
        //   caption: this.caption,
        //   src: this.src,
        //   liked: false,
        //   comments: []
        // });
        // this.caption = '';
        // this.src = '';
        // localStorage.setItem('cards', JSON.stringify(this.cards))

        this.axios.get("http://localhost:9090/test/string").then((response) => {
            window.console.log(response)
          })
      },
      destroyCard(card) {
        const index = this.cards.indexOf(card);
        this.cards.splice(index,1);
      },
      likeImage(card) {
        this.cards[this.cards.indexOf(card)].liked = this.cards[this.cards.indexOf(card)].liked === false;
        localStorage.setItem('cards', JSON.stringify(this.cards))
      },
      destroyComment(card, comment) {
        const index = this.cards[this.cards.indexOf(card)].comments.indexOf(comment);
        this.cards[this.cards.indexOf(card)].comments.splice(index,1)
      },
      addComment(card) {
        this.cards[this.cards.indexOf(card)].comments.push({
          comment: this.cards[this.cards.indexOf(card)].comment
        });
        this.cards[this.cards.indexOf(card)].comment = '';
        localStorage.setItem('cards', JSON.stringify(this.cards))
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
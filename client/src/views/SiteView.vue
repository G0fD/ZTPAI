<script setup>
import {API_URL} from "@/common/constant";
import {useRouter} from "vue-router";
import {useAuthenticated} from "@/composables/authenticated";
import {onMounted, ref, watch} from "vue";

const router = useRouter()
const search = ref();
const rating = ref(-1);
const song = ref();
const songs = ref([]);
const songR = ref({});
const image = ref(null);

watch(rating, () => {
  if (rating.value !== -1) {
    rateSong(rating.value)
  }
})

watch(search, () => {
  if (!search.value) {
    songs.value = []
    return
  }
  getSongs(search.value)
})

onMounted(async () => {
  await getSongs("H")
  song.value = songs.value[0]
  songs.value = []
  await getSongRating()
})

useAuthenticated()

async function getSongs(searchString) {
  const response = await fetch(API_URL + "/api/song/search", {
    method: "POST",
    headers: {
      'Authorization': `Bearer ${sessionStorage.getItem('token')}`,
      'Content-Type': 'application/json',
    },
    body: JSON.stringify({
      searchString
    })
  })
  const data = await response.json();
  songs.value = data;
}

async function getSongRating() {
  songR.value.songId = song.value.id
  const response = await fetch(API_URL + "/api/song/rate/get", {
    method: "POST",
    headers: {
      'Authorization': `Bearer ${sessionStorage.getItem('token')}`,
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(songR.value)
  })
  const data = await response.json();
  rating.value = data.integer;
}

async function rateSong(rated) {
  songR.value.songId = song.value.id
  songR.value.songRating = rated
  const response = await fetch(API_URL + "/api/song/rate", {
    method: "PUT",
    headers: {
      'Authorization': `Bearer ${sessionStorage.getItem('token')}`,
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(songR.value)
  })
  await response;
}

function onSongClick(selectedSong) {
  song.value = selectedSong
  search.value = ""
  getSongRating()
  rating.value = -1
}

function goToProfile() {
  router.push({
    name: "profile"
  });
}

</script>

<template>
  <div class="base-container">
    <main>
      <section class="left">
        <div class="title">
          <p>{{ song?.author }}: {{ song?.title }}</p>
        </div>
        <div class="album">
          <p>from {{ song?.album }} album</p>
        </div>
        <div class="photo">
          <img :src="`${API_URL}/api/auth/getSongImage/${song?.id}`">
        </div>
        <div class="player">
          <a :href="`https://www.youtube.com/results?search_query=${song?.author}+${song?.title}`" target="_blank"
             rel="noopener noreferrer">Check on YouTube</a>
        </div>
      </section>

      <section class="right">
        <div class="genres">
          <p>Main genres:</p>
          <ul>
            <li v-for="genre in song?.genres" :key="genre">
              {{ genre }}
            </li>
          </ul>
        </div>
        <div class="where">
          <p>Available on:</p>
          <ul>
            <li v-for="provider in song?.providers" :key="provider">
              {{ provider }}
            </li>
          </ul>

        </div>
        <div class="rating">
          <p>Did you like it?</p>
          <form action="rate" method="POST">

            <select v-model="rating" name="ratingselect" id="myselect">
              <option value="1">I hate it</option>
              <option value="2">I don't like it</option>
              <option value="3">Not bad, not good</option>
              <option value="4">I like it</option>
              <option value="5">I love it</option>
            </select>
          </form>
        </div>
      </section>
    </main>
    <footer>
      <div class="small-logo">
        <img src="/src/assets/shipify.svg">
      </div>
      <div class="search-bar">
        <input v-model="search" id='inpt' type="text" name="search" placeholder="search for your fav music"
               autocomplete="off">
        <section class="hide-n-seek" v-if="songs.length !== 0">
          <select class="search-result" multiple>
            <option v-for="song in songs" :key="song.id" @click.prevent.stop="onSongClick(song)">{{ song.author }}:
              {{ song.title }}
            </option>
          </select>
        </section>
      </div>
      <div class="profile-link">
        <p @click="goToProfile">Profile</p>
        <img src="/src/assets/undraw_pic_profile_re_lxn6.svg" @click="goToProfile">
      </div>
    </footer>
  </div>
</template>

<style scoped>
.title {
  color: #E5F0FF;
  font-size: 2em;
  margin-left: 3vh;
  height: 7vh;
}

.album {
  color: #E5F0FF;
  font-size: 1em;
  margin-left: 3vh;
  height: 10vh;
}

.photo {
  height: 43vh;
  display: flex;
  justify-content: center;
}

.player {
  height: 20vh;
  width: 100%;
  display: flex;
  justify-content: center;
  text-align: center;
}

.photo > img {
  height: 100%;
  max-width: 85%;
}

.player > a {
  background: #E5F0FF;
  border: 1px solid #000000;
  box-shadow: 0 4px 4px rgba(0, 0, 0, 0.25);
  border-radius: 20px;
  width: 70%;

  margin: 2em 1em 1em;
  height: 6vh;
  padding-top: 1em;
  color: #064789;
  font-family: 'Libre Baskerville', serif;
  font-style: normal;
  font-weight: 400;
  font-size: 3vh;
  text-decoration: none;
}

.player > a:hover {
  color: rgba(51, 75, 73, 0.96);
}

.right > * > p {
  text-align: center;
  color: #E5F0FF;
  font-size: 2em;
}

.genres {
  height: 28vh;
}

.genres > p {
  margin-top: 9vh;
}

.where {
  height: 27vh;
}

.rating {
  height: 24vh;
}

.rating > form {
  display: flex;
  justify-content: center;
}

.right > * > ul {
  color: #E5F0FF;
  font-size: 1.2em;
  margin: 0 4vh;
  padding: 0;
  list-style: none;
}

select {
  margin-top: 1em;
  font-family: 'Libre Baskerville', serif;
  font-style: normal;
  font-weight: 400;
  font-size: 1.2em;
  text-align: center;
  width: 80%;
  height: 3.5vh;
  background-color: #E5F0FF;
  color: #064789;
  border: 1px solid #000000;
  box-shadow: 0 4px 4px rgba(0, 0, 0, 0.25);
  scrollbar-width: none;
}

.search-bar {
  width: 60%;
}

.search-bar > input {
  width: 60vw;
  margin: 0;
  padding: 0.6em 0;
  text-align: center;
  font-size: 2vh;
}

.hide-n-seek {
  margin: 0;
  padding: 0;
  position: absolute;
  bottom: 10vh;
  width: 60vw;
  height: 30vh;
  max-height: 80vh;
  z-index: 1;
  background-color: #E5F0FF;
  border: 1px solid #000000;
  box-shadow: 0 4px 4px rgba(0, 0, 0, 0.25);
  border-radius: 20px 20px 20px 20px;
  overflow: hidden;
  display: inline-block;
}

.search-result {
  margin: 0;
  height: 30vh;
  width: 100%;
}

select::-webkit-scrollbar {
  display: none;
}

.search-result > p {
  width: 100%;
  height: 2vh;
}

.search-result > p:hover {
  background-color: #BCD5F5;
}

@media only screen and (max-device-width: 440px) {
  html {
    max-height: 100vh;
  }

  * {
    font-size: 2vh;
  }

  main {
    display: initial;
    flex-direction: column;
    overflow: scroll;
  }

  .left {
    height: 80vh;
    width: 90vw;
  }

  .right {
    height: 80vh;
    width: 90vw;
  }

  .title {
    margin: 0 2vw;
  }

  .title > p {
    width: 85vw;
    font-size: 0.6em;
  }

  .album {
    margin: 0 2vw;
  }

  .album > p {
    font-size: 1em;
    margin: 0;
  }

  ul > li {
    font-size: 1.3em;
  }

  .small-logo > img {
    width: 95%;
    margin-right: 5%;
    text-align: center;
  }

  .profile-link > p {
    display: none;
  }

  .profile-link > img {
    width: 100%;
    height: 100%;
  }
}
</style>
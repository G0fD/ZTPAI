<script setup>
import {onMounted, ref} from "vue";
import {useAuthenticated} from "@/composables/authenticated";
import {API_URL} from "@/common/constant";
import {useRouter} from "vue-router";

const file = ref()
const form = ref({})
const router = useRouter();

useAuthenticated()

onMounted(() => {
  fetch(API_URL + "/api/auth/validate/isAdmin", {
    headers: {
      'Authorization': `Bearer ${sessionStorage.getItem('token')}`
    }
  })
      .then(response => response.text())
      .then(data => {
        if (data !== "administrator") {
          router.push({
            name: "profile"
          });
        }
      })
      .catch(error => {
        console.error(error)
      });
})

function addSong(event) {
  const formData = new FormData();

  formData.append('file', file.value);
  formData.append('title', form.value.title)
  formData.append('author', form.value.author)
  formData.append('album', form.value.album)
  formData.append('providers', form.value.providers)
  formData.append('genres', form.value.genres)
  // formData.append('data', new Blob([JSON.stringify(form)], { type: 'application/json' }));


  fetch(API_URL + "/api/auth/addSong", {
    method: 'POST',
    body: formData,
    headers: {
      'Authorization': `Bearer ${sessionStorage.getItem('token')}`,
      //'Content-Type': 'multipart/form-data',
    }
  })

  router.push({
    name: "addSong"
  });

}

function onUpload(event) {
  file.value = event.target.files[0];
}

</script>

<template>
  <h1>UPLOAD</h1>
  <form @submit.prevent.stop="addSong">
    <section class="insert">
      <input v-model="form.title" type="text" name="title" placeholder="Song title">
      <input v-model="form.author" type="text" name="author" placeholder="Author name">
      <input v-model="form.album" type="text" name="album" placeholder="Album name">
      <input type="file" @change="onUpload" name="file" accept=".jpg, .png, .pdf">
    </section>
    <section class="select">
      <select v-model="form.genres" name="genres" id="genres" required multiple>
        <option value="1">Rap</option>
        <option value="2">Rock</option>
        <option value="3">Hip-hop</option>
      </select>
      <select v-model="form.providers" name="providers" required multiple>
        <option value="1">Spotify</option>
        <option value="2">Tidal</option>
        <option value="3">YouTube</option>
        <option value="4">SoundCloud</option>
      </select>
    </section>
    <button type="submit">SEND</button>
  </form>
</template>

<style scoped>
form {
  height: 75vh;
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
}

form > .insert {
  color: #334B49;
  width: 100%;
  height: 40vh;
  display: flex;
  flex-direction: column;
  align-items: center;
}

form > .select {
  width: 100%;
  height: 25vh;
  display: flex;
  flex-direction: row;
  justify-content: space-evenly;

}

.select > select {
  height: 20vh;
  margin-top: 1em;
  font-family: 'Libre Baskerville', serif;
  font-style: normal;
  font-weight: 400;
  font-size: 1.2em;
  text-align: center;
  width: 45%;
  background-color: #E5F0FF;
  color: #064789;
  border: 1px solid #000000;
  box-shadow: 0 4px 4px rgba(0, 0, 0, 0.25);
  scrollbar-width: none;
}

.select > select::-webkit-scrollbar {
  display: none;
}

.insert > input {
  width: 80%;
}

</style>
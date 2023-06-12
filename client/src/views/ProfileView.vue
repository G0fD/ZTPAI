<script setup>
import {useRouter} from "vue-router";
import {useAuthenticated} from "@/composables/authenticated";
import {onMounted, ref} from "vue";
import {API_URL} from "@/common/constant";

const router = useRouter()
const userType = ref();

useAuthenticated()

function onAddSongClick() {
  router.push({
    name: "addSong"
  });
}

function onLogoutClick() {
  router.push({
    name: "profile"
  });
}

function onMatchesClick() {
  router.push({
    name: "myMatches"
  })
}

function onLogoClick() {
  router.push({
    name: "site"
  })
}

onMounted(() => {
  fetch(API_URL + "/api/auth/validate/isAdmin", {
    headers: {
      'Authorization': `Bearer ${sessionStorage.getItem('token')}`
    }
  })
      .then(response => response.text())
      .then(data => {
        userType.value = data
      })
      .catch(error => {
        console.error(error)
      });
})

</script>

<template>
  <div class="container">
    <nav>
      <img class="small-logo" src="/src/assets/shipify.svg" alt="shipify" @click.prevent="onLogoClick">
      <ul>
        <li>
          <a href="#" class="button" @click.prevent="onLogoutClick">Settings</a>
        </li>
        <li>
          <a href="#" class="button" @click.prevent="onMatchesClick">My matches</a>
        </li>
        <li>
          <a v-if="userType==='administrator'" href="#" class="button" @click.prevent="onAddSongClick">Add song</a>
        </li>
        <li>
          <p>SHIPify made by Jakub Jajkowicz</p>
          <p>All rights reserved etc.</p>
        </li>
      </ul>
    </nav>
    <main>
      <router-view></router-view>
    </main>
  </div>
</template>

<style scoped>

.container {
  flex-direction: row;
  justify-content: flex-start;
  align-items: center;
  height: 100vh;
}

nav {
  width: 28vw;
  height: 100%;
  background: rgba(51, 75, 73, 0.96);

  display: flex;
  flex-direction: column;
  justify-content: flex-start;
}

.small-logo {
  width: 40%;
  margin: 2.5em;
}

.button {
  margin: 0;
  border: 1px solid #E5F0FF;
  background: #3E5E67;
  border-radius: 1em;
  padding: 0.2em;
  width: 90%;
  font-family: 'Libre Baskerville', serif;
  font-style: normal;
  font-weight: 400;
  color: #E5F0FF;
  font-size: 1.6em;
}

a {
  text-decoration: none;
  display: inline-block;
  line-height: 2em;
  color: #E5F0FF;
  padding: 3em 0;
}

ul {
  padding: 0;
  list-style: none;
  letter-spacing: 0.72px;
  height: 100%;
  position: relative;
}

li {
  text-align: center;
  margin-bottom: 0.5em;
}

li:last-child {
  position: absolute;
  bottom: 0;
  width: 100%;
  color: #E5F0FF;
  font-size: 1em;
}

main {
  height: 90vh;
  width: 68vw;
  background: rgba(51, 75, 73, 0.96);
  margin-left: 2vw;
  color: #BCD5F5;
  text-align: center;
  flex-direction: column;
  justify-content: center;
}

main > button {
  align-self: center;
}

main > h1 {
  height: 5vh;
}

.matches {
  height: 100%;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  padding: 5vh 10vw;
  text-align: center;
  overflow: auto;
}

.matches > p {
  font-size: 2vw;
  margin: 1vh 1vw;
  width: 100%;
}

.matches > button {
  align-self: center;
  position: absolute;
  bottom: 8%;
}

@media only screen and (max-device-width: 440px) {
  body {
    height: 100%;
  }

  .container {
    height: 100vh;
    width: 100vw;
    justify-content: space-between;
    align-items: end;
    display: flex;
    flex-direction: column-reverse;
  }

  main {
    width: 90vw;
    height: 80vh;
    margin: 10vh 5vw 5vh;
  }

  nav {
    width: 100vw;
    height: 15vh;
    flex-direction: column;
  }

  .matches {
    overflow: unset;
  }

  .matches > p {
    font-size: 1.5em;
  }

  .matches > button {
    position: inherit;
    width: 50%;
  }

  .small-logo {
    position: absolute;
    top: 0;
    width: 30%;
    margin-top: 1em;
  }

  ul {
    max-height: 15vh;
    display: flex;
    justify-content: space-evenly;
  }

  li {
    width: 20%;
    align-self: center;
  }

  .button {
    font-size: 1em;
    min-height: 5vh;
    text-align: center;
  }

  li:last-child {
    display: none;
  }

  a {
    padding-top: 1vh;
  }
}
</style>
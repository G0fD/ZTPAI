<script setup>
import {useRouter} from "vue-router";

const router = useRouter()

function onLoginClick() {
  router.push("login")
}

function onRegisterClick() {
  router.push("register")
}
</script>

<template>
  <div class="base-container">
    <div class="logo">
      <img src="../assets/logo.svg" alt="logo">
    </div>
    <div class="aboutus">
      <p>
        Welcome to SHIPify! Here we connect people based on their favorite music. Find your match today and meet someone
        who shares your love for music. Dont wait any longer, sign up and find your musical soulmate!
      </p>
    </div>
    <main>
      <h1>Log in or join now!</h1>
      <nav>
        <button @click="onLoginClick">Log in</button>
        <button @click="onRegisterClick">Sign up</button>
      </nav>
    </main>
  </div>
</template>

<style scoped>
.base-container {
  align-items: flex-start;
}

.logo {
  height: 20vh;
  padding-top: 5vh;
  text-align: left;
}

.logo > img {
  padding-left: 3vw;
  width: 50vw;
}

.aboutus {
  margin-left: 5vw;
  padding-top: 5vh;
  height: 20vh;
  width: 60%;
  font-family: 'Libre Baskerville';
  font-style: normal;
  font-weight: 400;
  color: #064789;
  font-size: 2.2em;
}

main {
  height: 50vh;
  width: 100%;
  margin: 0;
  padding: 0;
  margin-top: 5vh;
  background: #334B49;
  border-radius: 25px 25px 0 0;

  flex-direction: column;
  justify-content: center;
  align-items: center;
}

h1 {
  height: 15vh;
  margin: 0;
  padding: 0;
  font-size: 3em;
  color: #BCD5F5;
}

nav {
  margin: 0;
  padding: 0;
  height: 5vh;
  display: flex;
  justify-content: center;
  align-items: center;
}

nav > button {
  height: auto;
  width: 10vw;
}

@media only screen and (max-device-width: 440px) {

  * {
    font-size: 1.2vh;
  }

  .logo {
    margin: 0;
    text-align: center;
  }

  .logo > img {
    height: 20vh;
    width: 80vw;
    padding: 0;
  }

  .aboutus {
    width: 100%;
    margin: 0;
  }

  .aboutus > p {
    width: 90%;
    padding: 0;
    margin: 0 5%;
    font-size: 2vh;
  }

  main > h1 {
    text-align: center;
    width: 100%;
    height: 8vh;
  }

  nav {
    height: 20vh;
    width: 100%;
  }

  nav > button {
    width: 50%;
  }
}
</style>
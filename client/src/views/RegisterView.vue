<script setup>
import {ref} from "vue";
import {API_URL} from "@/common/constant";
import {useRouter} from "vue-router";

const form = ref({})
const router = useRouter()
let lookingFor;

async function onSubmit() {
  form.value.role = "2"
  form.value.interested = lookingFor.join(" ")
  const response = await fetch(API_URL + "/api/auth/register", {
    method: "POST",
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(form.value)
  })
  const data = await response.json();
  if (response.ok) {
    sessionStorage.setItem("token", data.token)
    await router.push({
      name: "site"
    })
  }
}

</script>

<template>
  <div class="container">
    <div class="logo">
      <img src="/src/assets/logo.svg" alt="logo">
    </div>
    <div class="signup-container">
      <form id="register-form" @submit.prevent.stop="onSubmit">
        <section class="title">
          <p>We're so happy you wanna join us!</p></section>
        <section class="basic">
          <p>Name:</p>
          <input v-model="form.name" type="text" name="name" placeholder="Tony" required>
          <p>Surname: </p>
          <input v-model="form.surname" type="text" name="surname" placeholder="Stark" required>
          <p>Email address: </p>
          <input v-model="form.email" type="email" name="email" placeholder="imthebest@example.com" required>
          <p>Password: </p>
          <input v-model="form.password" type="password" name="password" placeholder="********" required>
        </section>
        <section class="details">
          <p>My gender:</p>
          <select v-model="form.gender" name="gender" id="genderSelect" required>
            <option value="1">man</option>
            <option value="2">woman</option>
            <option value="3">other</option>
          </select>
          <p>Looking for:</p>
          <select v-model="lookingFor" name="lookingFor" id="lookingforselect" required multiple>
            <option value="1">man</option>
            <option value="2">woman</option>
            <option value="3">other</option>
          </select>
          <p>Username: </p>
          <input v-model="form.username" type="text" name="username" placeholder="imthebest" required>
          <p>That's it!</p>
          <button type="submit">Sign up</button>
        </section>
      </form>
    </div>
  </div>
</template>

<style scoped>
p {
  margin: 0;
  padding-top: 4vh;
  font-size: 2em;
  color: #E5F0FF;
}

.container {
  justify-content: flex-start;
}

.logo {
  height: 10vh;
  margin: 0;
  text-align: left;
}

.logo > img {
  width: 20%;
}

.signup-container {
  width: 100%;
  display: flex;
  flex-direction: column;
}

.signup-container > form {
  margin: 5vh 10vw;
  height: 80vh;
  width: 80vw;
  background: rgba(51, 75, 73, 0.96);
  border-radius: 25px;

  font-family: 'Libre Baskerville', serif;
  font-style: normal;
  font-weight: 400;

  display: flex;
  align-items: center;
  justify-content: center;
  flex-wrap: wrap;
}

.title {
  flex-basis: 100%;
  width: 80vw;
  height: 5vh;
  text-align: center;
}

.title > p {
  padding-top: 1em;
}

.basic {
  height: 75vh;
  width: 49vw;
  display: grid;
  grid-template-rows: repeat(9, 1fr);
  grid-gap: 0;
  overflow: auto;
  margin-left: 5vw;
  align-items: center;
}

.basic > input {
  width: 45vw;
  margin: 0;
  padding: 1.5vh 0.5vw;
  height: 2em;
  font-size: 1.3em;
}

.details {
  height: 75vh;
  width: 21vw;
  display: grid;
  grid-template-rows: repeat(9, 1fr);
  grid-gap: 0;
  overflow: auto;
  margin-right: 2vw;
  align-items: center;
  text-align: center;
}

.details > input {
  width: 19.5vw;
  margin: 0;
  padding: 1.5vh 0.5vw;
  height: 2em;
  font-size: 1.3em;
}

select {
  height: 3em;
  margin-top: 0;
  font-family: 'Libre Baskerville', serif;
  font-style: normal;
  font-weight: 400;
  text-align: center;
  background-color: #E5F0FF;
  color: #064789;
  border: 1px solid #000000;
  box-shadow: 0 4px 4px rgba(0, 0, 0, 0.25);
  align-self: center;
  font-size: 1.2em;
  width: 18vw;
}

button {
  margin: 0;
  padding: 0;
  width: 70%;
  justify-self: center;
  line-height: 2.5em;
  font-size: 2.5vh;
}

.no-valid {
  border: 3px solid red;
  border-radius: 1em;
  box-shadow: 0 0 0.2em red;
  outline: none;
}


@media only screen and (max-device-width: 440px) {

  * {
    font-size: 1.2vh;
  }

  .logo {
    width: 100vw;
    text-align: center;
    height: 20vh;
  }

  .logo > img {
    padding-top: 5vw;
    width: 60vw;
  }

  .signup-container > form {
    align-self: center;
    width: 90vw;
    flex-direction: row;
    overflow: scroll;
    justify-content: center;
    align-items: center;
    text-align: center;
  }

  .signup-container > form > * > input {
    width: 80%;
    padding-left: 5%;
    padding-right: 5%;
    margin-left: 5%;
    margin-right: 5%;
  }

  .title {
    margin: 0;
    height: 10vh;
    width: 80vw;
  }

  .basic {
    margin: 0;
    height: 80vh;
    width: 80vw;
  }

  .details {
    margin: 0;
    height: 80vh;
    width: 80vw;
  }

}
</style>
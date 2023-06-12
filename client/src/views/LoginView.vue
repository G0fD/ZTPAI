<script setup>
import {ref} from "vue";
import {API_URL} from "@/common/constant";
import {useRouter} from "vue-router";

const form = ref({})
const router = useRouter()

async function onSubmit() {
  const response = await fetch(API_URL + "/api/auth/authenticate", {
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
    <div class="login-container">
      <form class="login" @submit.prevent.stop="onSubmit">
        <p name="authFailed" style="text-align: center"></p>
        <p>username: </p>
        <input v-model="form.username" type="text" name="login" placeholder="username">
        <p>password: </p>
        <input v-model="form.password" type="password" name="password" placeholder="password">
        <button type="submit">Log in</button>
      </form>
    </div>

  </div>
</template>

<style scoped>

</style>
<script setup>
import {useAuthenticated} from "@/composables/authenticated";
import router from "@/router";

useAuthenticated()

function onLogoutClick() {
  sessionStorage.removeItem("token")
  router.push({
    name: "login"
  })
}

</script>

<template>
  <button id="logout" @click="onLogoutClick">Log out</button>
</template>
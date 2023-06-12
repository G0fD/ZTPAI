<script setup>

import {onMounted, ref} from "vue";
import {API_URL} from "@/common/constant";
import {useAuthenticated} from "@/composables/authenticated";

const matches = ref([])

onMounted(async () => {
  await fetch(API_URL + "/api/auth/match", {
    headers: {
      'Authorization': `Bearer ${sessionStorage.getItem('token')}`
    }
  })

  const response = await fetch(API_URL + "/api/auth/myProfile/matches", {
    headers: {
      'Authorization': `Bearer ${sessionStorage.getItem('token')}`
    }
  })
  const data = await response.json()
  matches.value = data
})

useAuthenticated()

</script>

<template>
  <div class="matches">
    <ul>
      <li v-for="match in matches">
        <p><b>{{ match.name }} {{ match.surname }}</b></p>
        <p>Gender: {{ match.gender }}</p>
        <p><i>Contact here: {{ match.email }}</i></p>
      </li>
    </ul>
  </div>
</template>

<style scoped>

ul {
  font-size: 1.2em;
  margin: 0 4vh;
  padding: 0;
  list-style: none;
  width: 80%;
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

p {
  font-size: 2vw;
  margin: 1vh 1vw;
  width: 100%;
}

</style>
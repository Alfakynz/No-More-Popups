<script setup>
import WikiNav from './WikiNav.vue'
import { onMounted } from 'vue'

onMounted(() => {
  const nav = document.querySelector('nav')
  const main = document.querySelector('main')
  if (nav && main) {
    main.style.paddingTop = nav.offsetHeight + 'px'
  }
})
</script>

<template>
  <main>
    <WikiNav />
    <section class="wiki-content">
      <h1>Configuration</h1>
      <h2>Versions</h2>
      <p>No More Pop-ups supports Minecraft 1.20-1.21.8</p>
      <p>
        Make sure you are using the latest version of Fabric Loader (0.16.4 or higher) and Fabric
        API.
      </p>
      <h2>Config File</h2>
      <p>
        The mod creates a configuration file named <code>nomorepopups.json</code> in the
        <code>config</code> folder of your Minecraft directory. You can edit this file to customize
        the mod's behavior.
      </p>
      <p>
        If you're using <a href="https://modrinth.com/project/mOgUt4GM" target="_blank">ModMenu</a>,
        you can easily configure this file into the mod settings
      </p>
    </section>
  </main>
</template>

<style scoped>
main {
  display: flex;
  flex-direction: row;
  align-items: flex-start;
  width: 100vw;
  max-width: 100vw;
  box-sizing: border-box;
}

@media (max-width: 1024px) {
  main {
    flex-direction: column;
  }
}
</style>
